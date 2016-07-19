package com.hebe.report.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        x.view().inject(this);
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
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });
        get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPhoneNumber(login_phone.getText().toString().trim())){
                    showToast("请输入正确的手机号码");
                    return;
                }
                timer.start();
                get_code.setClickable(false);
                getDeviceToken();
                showProgressDialog("正在获取验证码");
            }
        });

    }

    private void getDeviceToken(){
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        RequestParams params = new RequestParams(Constant.base_url+"App/getToken");
        params.addBodyParameter("app_version", "1.0");
        params.addBodyParameter("server_version", "android");
        params.addBodyParameter("appid", tm.getDeviceId());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                DeviceTokenBean bean = Utils.jsonParase(result,DeviceTokenBean.class);
                if (bean != null && !TextUtils.isEmpty(bean.getToken())){
                    showToast(bean.getToken()+"");
                    token = bean.getToken();
                    ToolsSp.saveOrUpdate(LoginActivity.this,Constant.SP_NAME,"dtoken",bean.getToken());
                    getVeriToken(login_phone.getText().toString().trim());
                }else {
                    closeProgressDialog();
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
    }

    public void getVeriToken(String mobile){
        RequestParams params = new RequestParams(Constant.base_url+"App/getCode");
        params.addBodyParameter("mobile", mobile);
        params.addBodyParameter("token", token);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                showToast(result);
                closeProgressDialog();
                VerifyCodeBean bean = Utils.jsonParase(result,VerifyCodeBean.class);
                if (bean != null && bean.getCode() == 4001){
                    getDeviceToken();
                }else if (bean != null && bean.getCode() == 200){
                    showToast(bean.getCode()+"");
                }else {
                    showToast(bean.getCode()+"");
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
        }
    }
}
