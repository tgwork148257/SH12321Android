package com.hebe.report.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * 不良wifi
 * Created by Hebe on 7-7-007.
 */

public class BadWifiActivity extends BaseActivity {

    @ViewInject(R.id.et_badwifi_name)
    EditText etBadwifiName;
    @ViewInject(R.id.badwifi_address)
    LinearLayout badwifiAddress;
    @ViewInject(R.id.et_badwifi_detail)
    EditText etBadwifiDetail;
    @ViewInject(R.id.select_time)
    LinearLayout selectTime;
    @ViewInject(R.id.badwifi_commit)
    Button badwifiCommit;
    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badwifi_activity);
        x.view().inject(this);

        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navi_title.setText("不良WIFI");
    }

    @Event({R.id.badwifi_address, R.id.select_time, R.id.badwifi_commit})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.badwifi_address:
                break;
            case R.id.select_time:
                break;
            case R.id.badwifi_commit:
                showToast("提交");
                break;
        }
    }
}
