package com.hebe.report.activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hebe.report.Constant.Constant;
import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;
import com.hebe.report.bean.DeviceTokenBean;
import com.hebe.report.bean.UserToken;
import com.hebe.report.bean.VerifyCodeBean;
import com.hebe.report.utils.ToolsSp;
import com.hebe.report.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 登录界面
 * Created by Hebe on 7-4-004.
 */

public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.login_bt)
    private Button login_bt;
    @ViewInject(R.id.et_login_phone)
    private EditText login_phone;
    @ViewInject(R.id.et_verify_code)
    private EditText verify_code;
    @ViewInject(R.id.get_code)
    private TextView get_code;

    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        x.view().inject(this);
        if (Utils.getUserToken(this) != null){
            startActivity(new Intent(this,MainActivity.class));
            finish();
            return;
        }
        timer = new CountDownTimer(60*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                get_code.setText(millisUntilFinished/1000 + "S");
            }

            @Override
            public void onFinish() {
                get_code.setText("获取验证码");
                get_code.setClickable(true);
            }
        };
        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });
        get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPhoneNumber(login_phone.getText().toString().trim())){
                    showToast("请输入正确的手机号码");
                    return;
                }
                if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(LoginActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE},110);
                }else {
                    get_code.setClickable(false);
                    getDeviceToken();
                    showProgressDialog("正在获取验证码");
                }

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 110){
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                get_code.setClickable(false);
                getDeviceToken();
                showProgressDialog("正在获取验证码");
            }else {
                showToast("请同意权限后获取验证码");
            }
        }
    }

    private void getDeviceToken(){
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        RequestParams params = Utils.getDefaultParams("App/getToken");
        params.addBodyParameter("app_version", "1.0");
        params.addBodyParameter("server_version", "android");
        params.addBodyParameter("appid", tm.getDeviceId());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                DeviceTokenBean bean = Utils.jsonParase(result,DeviceTokenBean.class);
                if (bean != null && !TextUtils.isEmpty(bean.getToken())){
                    ToolsSp.saveOrUpdate(LoginActivity.this,Constant.SP_NAME,"dtoken",bean.getToken());
                    getVeriToken(login_phone.getText().toString().trim());
                }else {
                    closeProgressDialog();
                    get_code.setEnabled(true);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                closeProgressDialog();
                showToast("获取失败");
                get_code.setEnabled(true);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void getVeriToken(String mobile){
        RequestParams params = Utils.getDefaultParams("App/getCode");
        params.addBodyParameter("mobile", mobile);
        params.addBodyParameter("token", ToolsSp.get(this,Constant.SP_NAME,"dtoken"));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                closeProgressDialog();
                VerifyCodeBean bean = Utils.jsonParase(result,VerifyCodeBean.class);
                if (bean != null && !TextUtils.isEmpty(bean.getCode()) && !result.contains("data")){
                    showToast("获取成功，请查收短信");
                    timer.start();
                }else {
                    showToast("请重试");
                    get_code.setClickable(true);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                closeProgressDialog();
                get_code.setClickable(true);
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void login(){
        if (isPhoneNumber(login_phone.getText().toString().trim()) && verify_code.getText().toString().trim().length() == 4){
            showProgressDialog("正在登录");
            final String phone = login_phone.getText().toString().trim();
            RequestParams params = Utils.getDefaultParams("App/getUserToken");
            params.addBodyParameter("mobile",phone);
            params.addBodyParameter("token",ToolsSp.get(this,Constant.SP_NAME,"dtoken"));
            params.addBodyParameter("code",verify_code.getText().toString().trim());
            x.http().post(params,new Callback.CommonCallback<String>(){

                @Override
                public void onSuccess(String result) {
                    closeProgressDialog();
                    UserToken utoken = Utils.jsonParase(result,UserToken.class);
                    if (utoken != null && !TextUtils.isEmpty(utoken.getUser_token())){
                        ToolsSp.saveOrUpdate(LoginActivity.this,Constant.SP_NAME,"utoken",utoken.getUser_token());
                        ToolsSp.saveOrUpdate(LoginActivity.this,Constant.SP_NAME,"phone",phone);
                        if (!TextUtils.isEmpty(utoken.getRegister()) && utoken.getRegister().equals("0")){
                            showToast("注册成功");
                        }else {
                            showToast("登录成功");
                        }
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                    }else {
                        showToast("登录失败");
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    closeProgressDialog();
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }else {
            showToast("输入有误");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
        }
    }
}
