package com.hebe.report.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        x.view().inject(this);
        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}
