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
 * 手机实名制
 * Created by Hebe on 7-7-007.
 */

public class PhoneVerifyActivity extends BaseActivity {

    @ViewInject(R.id.lay1_layout1_img)
    ImageView lay1Layout1Img;
    @ViewInject(R.id.lay1_layout1)
    LinearLayout lay1Layout1;
    @ViewInject(R.id.lay1_layout2_img)
    ImageView lay1Layout2Img;
    @ViewInject(R.id.lay1_layout2)
    LinearLayout lay1Layout2;
    @ViewInject(R.id.shitidian_weigui)
    LinearLayout shitidianWeigui;
    @ViewInject(R.id.et_shitidian_name)
    EditText etShitidianName;
    @ViewInject(R.id.et_shitidian_hao)
    EditText etShitidianHao;
    @ViewInject(R.id.shitidian_selecttime)
    LinearLayout shitidianSelecttime;
    @ViewInject(R.id.shitidian_yunyingshang)
    LinearLayout shitidianYunyingshang;
    @ViewInject(R.id.shitidian_address)
    LinearLayout shitidianAddress;
    @ViewInject(R.id.et_shitidian_dizhixiangxi)
    EditText etShitidianDizhixiangxi;
    @ViewInject(R.id.shitidian_photo)
    LinearLayout shitidianPhoto;
    @ViewInject(R.id.shitidian_chikaphoto)
    LinearLayout shitidianChikaphoto;
    @ViewInject(R.id.et_shitidian_miaoshu)
    EditText etShitidianMiaoshu;
    @ViewInject(R.id.shitidian_layout)
    LinearLayout shitidianLayout;
    @ViewInject(R.id.wangdian_weigui)
    LinearLayout wangdianWeigui;
    @ViewInject(R.id.et_wagndian_name)
    EditText etWagndianName;
    @ViewInject(R.id.et_wangdian_hao)
    EditText etWangdianHao;
    @ViewInject(R.id.wangdian_selecttime)
    LinearLayout wangdianSelecttime;
    @ViewInject(R.id.wangdian_yunyingshang)
    LinearLayout wangdianYunyingshang;
    @ViewInject(R.id.et_wangdian_wangzhi)
    EditText etWangdianWangzhi;
    @ViewInject(R.id.et_wangdian_xiaoshoudizhi)
    EditText etWangdianXiaoshoudizhi;
    @ViewInject(R.id.wangdian_photo)
    LinearLayout wangdianPhoto;
    @ViewInject(R.id.wangdian_chikaphoto)
    LinearLayout wangdianChikaphoto;
    @ViewInject(R.id.et_wangdian_miaoshu)
    EditText etWangdianMiaoshu;
    @ViewInject(R.id.wangdian_layout)
    LinearLayout wangdianLayout;
    @ViewInject(R.id.sim_commit)
    Button simCommit;
    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;

    private int lay1check = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phoneverify_activity);
        x.view().inject(this);

        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navi_title.setText("手机实名制举报");
        lay1Layout1Img.setImageResource(R.drawable.but_checked);
    }

    @Event({R.id.lay1_layout1, R.id.lay1_layout2, R.id.shitidian_weigui, R.id.shitidian_selecttime, R.id.shitidian_yunyingshang, R.id.shitidian_address, R.id.shitidian_photo, R.id.shitidian_chikaphoto, R.id.wangdian_weigui, R.id.wangdian_selecttime, R.id.wangdian_yunyingshang, R.id.wangdian_photo, R.id.wangdian_chikaphoto, R.id.sim_commit})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.lay1_layout1:
                lay1Layout1Img.setImageResource(R.drawable.but_checked);
                lay1Layout2Img.setImageResource(R.drawable.but_uncheck);
                lay1check = 1;
                shitidianLayout.setVisibility(View.VISIBLE);
                wangdianLayout.setVisibility(View.GONE);
                break;
            case R.id.lay1_layout2:
                lay1Layout2Img.setImageResource(R.drawable.but_checked);
                lay1Layout1Img.setImageResource(R.drawable.but_uncheck);
                lay1check = 2;
                shitidianLayout.setVisibility(View.GONE);
                wangdianLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.shitidian_weigui:
                break;
            case R.id.shitidian_selecttime:
                break;
            case R.id.shitidian_yunyingshang:
                break;
            case R.id.shitidian_address:
                break;
            case R.id.shitidian_photo:
                break;
            case R.id.shitidian_chikaphoto:
                break;
            case R.id.wangdian_weigui:
                break;
            case R.id.wangdian_selecttime:
                break;
            case R.id.wangdian_yunyingshang:
                break;
            case R.id.wangdian_photo:
                break;
            case R.id.wangdian_chikaphoto:
                break;
            case R.id.sim_commit:
                showToast(lay1check+"");
                break;
        }
    }
}
