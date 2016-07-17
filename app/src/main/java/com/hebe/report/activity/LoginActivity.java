package com.hebe.report.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;

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

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
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
                timer.start();
                get_code.setClickable(false);
            }
        });

    }

    private void getCode(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
        }
    }
}
