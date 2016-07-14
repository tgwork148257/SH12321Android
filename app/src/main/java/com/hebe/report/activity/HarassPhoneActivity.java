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
 * 骚扰电话
 * Created by Hebe on 7-7-007.
 */

public class HarassPhoneActivity extends BaseActivity {

    @ViewInject(R.id.et_phone_1)
    EditText etPhone1;
    @ViewInject(R.id.et_phone_2)
    EditText etPhone2;
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
    @ViewInject(R.id.lay2_layout1_img)
    ImageView lay2Layout1Img;
    @ViewInject(R.id.lay2_layout1)
    LinearLayout lay2Layout1;
    @ViewInject(R.id.lay2_layout2_img)
    ImageView lay2Layout2Img;
    @ViewInject(R.id.lay2_layout2)
    LinearLayout lay2Layout2;
    @ViewInject(R.id.lay2_layout3_img)
    ImageView lay2Layout3Img;
    @ViewInject(R.id.lay2_layout3)
    LinearLayout lay2Layout3;
    @ViewInject(R.id.lay2_layout4_img)
    ImageView lay2Layout4Img;
    @ViewInject(R.id.lay2_layout4)
    LinearLayout lay2Layout4;
    @ViewInject(R.id.lay2_layout5_img)
    ImageView lay2Layout5Img;
    @ViewInject(R.id.lay2_layout5)
    LinearLayout lay2Layout5;
    @ViewInject(R.id.lay2_layout6_img)
    ImageView lay2Layout6Img;
    @ViewInject(R.id.lay2_layout6)
    LinearLayout lay2Layout6;
    @ViewInject(R.id.lay3_layout1_img)
    ImageView lay3Layout1Img;
    @ViewInject(R.id.lay3_layout1)
    LinearLayout lay3Layout1;
    @ViewInject(R.id.lay3_layout2_img)
    ImageView lay3Layout2Img;
    @ViewInject(R.id.lay3_layout2)
    LinearLayout lay3Layout2;
    @ViewInject(R.id.lay3_layout3_img)
    ImageView lay3Layout3Img;
    @ViewInject(R.id.lay3_layout3)
    LinearLayout lay3Layout3;
    @ViewInject(R.id.lay3_layout4_img)
    ImageView lay3Layout4Img;
    @ViewInject(R.id.lay3_layout4)
    LinearLayout lay3Layout4;
    @ViewInject(R.id.select_time)
    LinearLayout selectTime;
    @ViewInject(R.id.harass_content)
    EditText harassContent;
    @ViewInject(R.id.harass_commit)
    Button harassCommit;
    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;

    private int lay1check = -1;
    private int lay2check = -1;
    private int lay3check = -1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.harassphone_activity);
        x.view().inject(this);

        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navi_title.setText("骚扰电话");
    }

    private void resetLay1(){
        lay1Layout1Img.setImageResource(R.drawable.but_uncheck);
        lay1Layout2Img.setImageResource(R.drawable.but_uncheck);
        lay1Layout3Img.setImageResource(R.drawable.but_uncheck);
    }
    private void resetLay2(){
        lay2Layout1Img.setImageResource(R.drawable.but_uncheck);
        lay2Layout2Img.setImageResource(R.drawable.but_uncheck);
        lay2Layout3Img.setImageResource(R.drawable.but_uncheck);
        lay2Layout4Img.setImageResource(R.drawable.but_uncheck);
        lay2Layout5Img.setImageResource(R.drawable.but_uncheck);
        lay2Layout6Img.setImageResource(R.drawable.but_uncheck);
    }
    private void resetLay3(){
        lay3Layout1Img.setImageResource(R.drawable.but_uncheck);
        lay3Layout2Img.setImageResource(R.drawable.but_uncheck);
        lay3Layout3Img.setImageResource(R.drawable.but_uncheck);
        lay3Layout4Img.setImageResource(R.drawable.but_uncheck);
    }

    @Event({R.id.lay1_layout1, R.id.lay1_layout2, R.id.lay1_layout3, R.id.lay2_layout1, R.id.lay2_layout2, R.id.lay2_layout3, R.id.lay2_layout4, R.id.lay2_layout5, R.id.lay2_layout6, R.id.lay3_layout1, R.id.lay3_layout2, R.id.lay3_layout3, R.id.lay3_layout4, R.id.select_time, R.id.harass_commit})
    private void click(View view) {
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
            case R.id.lay2_layout1:
                resetLay2();
                lay2Layout1Img.setImageResource(R.drawable.but_checked);
                lay2check = 1;
                break;
            case R.id.lay2_layout2:
                resetLay2();
                lay2Layout2Img.setImageResource(R.drawable.but_checked);
                lay2check = 2;
                break;
            case R.id.lay2_layout3:
                resetLay2();
                lay2Layout3Img.setImageResource(R.drawable.but_checked);
                lay2check = 3;
                break;
            case R.id.lay2_layout4:
                resetLay2();
                lay2Layout4Img.setImageResource(R.drawable.but_checked);
                lay2check = 4;
                break;
            case R.id.lay2_layout5:
                resetLay2();
                lay2Layout5Img.setImageResource(R.drawable.but_checked);
                lay2check = 5;
                break;
            case R.id.lay2_layout6:
                resetLay2();
                lay2Layout6Img.setImageResource(R.drawable.but_checked);
                lay2check = 6;
                break;
            case R.id.lay3_layout1:
                resetLay3();
                lay3Layout1Img.setImageResource(R.drawable.but_checked);
                lay3check = 1;
                break;
            case R.id.lay3_layout2:
                resetLay3();
                lay3Layout2Img.setImageResource(R.drawable.but_checked);
                lay3check = 2;
                break;
            case R.id.lay3_layout3:
                resetLay3();
                lay3Layout3Img.setImageResource(R.drawable.but_checked);
                lay3check = 3;
                break;
            case R.id.lay3_layout4:
                resetLay3();
                lay3Layout4Img.setImageResource(R.drawable.but_checked);
                lay3check = 4;
                break;
            case R.id.select_time:
                break;
            case R.id.harass_commit:
                showToast(lay1check + "  " + lay2check + "  " + lay3check);
                break;
        }
    }
}
