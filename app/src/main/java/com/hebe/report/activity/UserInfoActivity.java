package com.hebe.report.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebe.report.Constant.Constant;
import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;
import com.hebe.report.bean.CommonResultBean;
import com.hebe.report.bean.UserInfoBean;
import com.hebe.report.utils.ToolsSp;
import com.hebe.report.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Hebe on 16/8/13.
 */

public class UserInfoActivity extends BaseActivity {

    @ViewInject(R.id.phone_tv)
    TextView phone_tv;
    @ViewInject(R.id.name_et)
    EditText name_et;
    @ViewInject(R.id.age_et)
    EditText age_et;
    @ViewInject(R.id.sex_layout)
    LinearLayout sex_layout;
    @ViewInject(R.id.sex_tv)
    TextView sex_tv;
    @ViewInject(R.id.navi_rigint)
    TextView navi_rigint;
    @ViewInject(R.id.address_layout)
    LinearLayout address_layout;
    @ViewInject(R.id.address_tv)
    TextView address_tv;
    @ViewInject(R.id.et_address_detail)
    EditText et_address_detail;
    @ViewInject(R.id.login_out)
    private TextView login_out;
    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;

    private int type = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        x.view().inject(this);
        getUserInfo();
        navi_rigint.setText("编辑");
        navi_title.setText("个人信息");
        navi_back.setVisibility(View.VISIBLE);
        setInfoEnable();
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        navi_rigint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 0){
                    type = 1;
                    navi_rigint.setText("保存");
                    setInfoEnable();
                }else {
                    if (TextUtils.isEmpty(name_et.getText().toString().trim())){
                        showToast("请填写姓名");
                        return;
                    }
                    if (TextUtils.isEmpty(age_et.getText().toString().trim())){
                        showToast("请填写年龄");
                        return;
                    }
                    if (TextUtils.isEmpty(sex_tv.getText().toString().trim())){
                        showToast("请选择性别");
                        return;
                    }
                    if (TextUtils.isEmpty(address_tv.getText().toString().trim())){
                        showToast("请选择地址");
                        return;
                    }
                    if (TextUtils.isEmpty(et_address_detail.getText().toString().trim())){
                        showToast("请填写详细地址");
                        return;
                    }
                    showProgressDialog("正在保存");
                    saveUserInfo();
                }
            }
        });

        sex_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserInfoActivity.this);
                builder.setItems(new String[]{"男", "女"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            sex_tv.setText("男");
                        }else {
                            sex_tv.setText("女");
                        }
                    }
                });
                builder.create().show();
            }
        });

        address_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this,CommonListActivity.class);
                intent.putExtra("title","选择区县");
                intent.putStringArrayListExtra("items", new Constant().getQuxian());
                startActivityForResult(intent,100);
            }
        });
        login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolsSp.delete(UserInfoActivity.this, Constant.SP_NAME,"utoken");
                ToolsSp.delete(UserInfoActivity.this,Constant.SP_NAME,"phone");
                startActivity(new Intent(UserInfoActivity.this, LoginActivity.class));
                finish();
                showToast("退出成功");
            }
        });
    }

    public void getUserInfo(){
        RequestParams params = Utils.getDefaultParams("App/getUserInfo");
        params.addBodyParameter("user_token", Utils.getUserToken(this));

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                UserInfoBean bean = Utils.jsonParase(result,UserInfoBean.class);
                if (bean != null && bean.getCode() == 200){
                    phone_tv.setText(bean.getData().getMobile());
                    name_et.setText(bean.getData().getName());
                    age_et.setText(bean.getData().getAge());
                    sex_tv.setText(bean.getData().getSex());
                    int index = bean.getData().getAddress().indexOf("区");
                    if (index != -1){
                        address_tv.setText(bean.getData().getAddress().substring(0,index+1));
                        et_address_detail.setText(bean.getData().getAddress().substring(index+1,bean.getData().getAddress().length()));
                    }
                }else {
                    showToast("获取失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                showToast("获取失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void saveUserInfo(){
        RequestParams params = Utils.getDefaultParams("App/alterUserInfo");
        params.addBodyParameter("user_token", Utils.getUserToken(this));
        params.addBodyParameter("name", name_et.getText().toString().trim());
        params.addBodyParameter("age", age_et.getText().toString().trim());
        params.addBodyParameter("address", address_tv.getText().toString().trim()+et_address_detail.getText().toString().trim());
        params.addBodyParameter("sex", sex_tv.getText().toString().trim());

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                closeProgressDialog();
                CommonResultBean bean = Utils.jsonParase(result,CommonResultBean.class);
                if (bean != null && bean.getCode() == 200){
                    showToast("保存成功");
                    type = 0;
                    navi_rigint.setText("编辑");
                    setInfoEnable();
                }else {
                    showToast("修改失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                closeProgressDialog();
                showToast("修改失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });
    }

    public void setInfoEnable(){
        if (type == 0){
            name_et.setEnabled(false);
            age_et.setEnabled(false);
            sex_tv.setEnabled(false);
            sex_layout.setEnabled(false);
            address_layout.setEnabled(false);
            et_address_detail.setEnabled(false);
        }else {
            name_et.setEnabled(true);
            age_et.setEnabled(true);
            sex_tv.setEnabled(true);
            sex_layout.setEnabled(true);
            address_layout.setEnabled(true);
            et_address_detail.setEnabled(true);
            if (TextUtils.isEmpty(name_et.getText().toString().trim())){
                name_et.setHint("请填写姓名");
            }
            name_et.requestFocus();
            InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(name_et, 0);
            if (TextUtils.isEmpty(age_et.getText().toString().trim())){
                age_et.setHint("请填写年龄");
            }
            if (TextUtils.isEmpty(et_address_detail.getText().toString().trim())){
                et_address_detail.setHint("请填写地址");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100&&data!=null){
            address_tv.setText(data.getStringExtra("address"));
        }
    }
}
