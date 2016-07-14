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

import butterknife.OnClick;

/**
 * 不良网站
 * Created by Hebe on 7-7-007.
 */

public class BadNetActivity extends BaseActivity {

    @ViewInject(R.id.et_badnet)
    EditText etBadnet;
    @ViewInject(R.id.lay1_layout1_img)
    ImageView lay1Layout1Img;
    @ViewInject(R.id.lay1_layout1)
    LinearLayout lay1Layout1;
    @ViewInject(R.id.lay1_layout2_img)
    ImageView lay1Layout2Img;
    @ViewInject(R.id.lay1_layout2)
    LinearLayout lay1Layout2;
    @ViewInject(R.id.lay1_layout3_img)
    ImageView lay1Layout3Img;
    @ViewInject(R.id.lay1_layout3)
    LinearLayout lay1Layout3;
    @ViewInject(R.id.lay1_layout4_img)
    ImageView lay1Layout4Img;
    @ViewInject(R.id.lay1_layout4)
    LinearLayout lay1Layout4;
    @ViewInject(R.id.badnet_content)
    EditText badnetContent;
    @ViewInject(R.id.badnet_commit)
    Button badnetCommit;
    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;

    private int lay1check = -1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badnet_activity);
        x.view().inject(this);

        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navi_title.setText("不良网站");
    }

    public void resetLay1(){
        lay1Layout1Img.setImageResource(R.drawable.but_uncheck);
        lay1Layout2Img.setImageResource(R.drawable.but_uncheck);
        lay1Layout3Img.setImageResource(R.drawable.but_uncheck);
        lay1Layout4Img.setImageResource(R.drawable.but_uncheck);
    }

    @Event({R.id.lay1_layout1, R.id.lay1_layout2, R.id.lay1_layout3, R.id.lay1_layout4, R.id.badnet_commit})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.lay1_layout1:
                resetLay1();
                lay1Layout1Img.setImageResource(R.drawable.but_checked);
                lay1check = 1;
                break;
            case R.id.lay1_layout2:
                resetLay1();
                lay1Layout2Img.setImageResource(R.drawable.but_checked);
                lay1check = 2;
                break;
            case R.id.lay1_layout3:
                resetLay1();
                lay1Layout3Img.setImageResource(R.drawable.but_checked);
                lay1check = 3;
                break;
            case R.id.lay1_layout4:
                resetLay1();
                lay1Layout4Img.setImageResource(R.drawable.but_checked);
                lay1check = 4;
                break;
            case R.id.badnet_commit:
                showToast(lay1check+" ");
                break;
        }
    }
}
