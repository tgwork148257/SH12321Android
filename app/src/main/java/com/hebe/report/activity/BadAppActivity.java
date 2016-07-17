package com.hebe.report.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * 不良APP
 * Created by Hebe on 7-7-007.
 */

public class BadAppActivity extends BaseActivity {

    @ViewInject(R.id.et_badapp_name)
    EditText etBadappName;
    @ViewInject(R.id.et_badapp_from)
    EditText etBadappFrom;
    @ViewInject(R.id.et_badapp_content)
    EditText etBadappContent;
    @ViewInject(R.id.badapp_commit)
    Button badappCommit;
    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badapp_activity);
        x.view().inject(this);

        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navi_title.setText("不良APP");
    }

    @Event({R.id.badapp_commit})
    private void onClick(View view) {
        String name = etBadappName.getText().toString().trim();
        String from = etBadappFrom.getText().toString().trim();
        String content = etBadappContent.getText().toString().trim();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(from) || TextUtils.isEmpty(content)){
            showToast("请填写完整信息");
            return;
        }
        showToast(name+"  "+from + "  "+content);

    }
}
