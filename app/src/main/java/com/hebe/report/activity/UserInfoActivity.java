package com.hebe.report.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;
import com.hebe.report.bean.CommonResultBean;
import com.hebe.report.bean.UserInfoBean;
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
    @ViewInject(R.id.address_et)
    EditText address_et;
    @ViewInject(R.id.navi_rigint)
    TextView navi_rigint;

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
                    if (TextUtils.isEmpty(address_et.getText().toString().trim())){
                        showToast("请填写地址");
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
                    address_et.setText(bean.getData().getAddress());
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
        params.addBodyParameter("address", address_et.getText().toString().trim());
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
                type = 0;
                navi_rigint.setText("编辑");
                setInfoEnable();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                type = 0;
                navi_rigint.setText("编辑");
                setInfoEnable();
            }
        });
    }

    public void setInfoEnable(){
        if (type == 0){
            name_et.setEnabled(false);
            age_et.setEnabled(false);
            sex_tv.setEnabled(false);
            address_et.setEnabled(false);
        }else {
            name_et.setEnabled(true);
            age_et.setEnabled(true);
            sex_tv.setEnabled(true);
            address_et.setEnabled(true);
            if (TextUtils.isEmpty(name_et.getText().toString().trim())){
                name_et.setHint("请填写姓名");
            }
            name_et.requestFocus();
            InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(name_et, 0);
            if (TextUtils.isEmpty(age_et.getText().toString().trim())){
                age_et.setHint("请填写年龄");
            }
            if (TextUtils.isEmpty(address_et.getText().toString().trim())){
                address_et.setHint("请填写地址");
            }
        }
    }
}
