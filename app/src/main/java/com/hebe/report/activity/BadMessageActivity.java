package com.hebe.report.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 不良短信
 * Created by Hebe on 7-7-007.
 */

public class BadMessageActivity extends BaseActivity {

    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;

    @ViewInject(R.id.message_phone_receiver)
    private EditText message_phone_receiver;

    @ViewInject(R.id.message_phone_send)
    private EditText message_phone_send;
    @ViewInject(R.id.message_phone_content)
    private EditText message_phone_content;
    @ViewInject(R.id.message_submmit)
    private Button message_submmit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badmessage_activity);
        x.view().inject(this);

        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navi_title.setText("不良短信");

        message_submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPhoneNumber(message_phone_receiver.getText().toString().trim())&&isPhoneNumber(message_phone_receiver.getText().toString().trim())&&TextUtils.isEmpty(message_phone_content.getText().toString().trim())){
                    showToast("提交");
                }else {
                    showToast("输入信息有误");
                }
            }
        });
    }
}
