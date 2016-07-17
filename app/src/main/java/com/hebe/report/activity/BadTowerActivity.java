package com.hebe.report.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebe.report.Constant.Constant;
import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;
import com.hebe.report.utils.DateTimeDialog;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 伪基站
 * Created by Hebe on 7-7-007.
 */

public class BadTowerActivity extends BaseActivity {

    @ViewInject(R.id.lay1_layout1_img)
    ImageView lay1Layout1Img;
    @ViewInject(R.id.lay1_layout1)
    LinearLayout lay1Layout1;
    @ViewInject(R.id.lay1_layout2_img)
    ImageView lay1Layout2Img;
    @ViewInject(R.id.lay1_layout2)
    LinearLayout lay1Layout2;
    @ViewInject(R.id.adress_layout)
    LinearLayout adressLayout;
    @ViewInject(R.id.address_detail)
    EditText addressDetail;
    @ViewInject(R.id.select_time)
    LinearLayout selectTime;
    @ViewInject(R.id.tower_content)
    EditText towerContent;
    @ViewInject(R.id.tower_commit)
    Button towerCommit;
    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;
    @ViewInject(R.id.address_tv)
    private TextView address_tv;
    @ViewInject(R.id.select_time_tv)
    private TextView select_time_tv;

    private int lay1check = -1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badtower_activity);
        x.view().inject(this);

        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navi_title.setText("伪基站");
    }

    @Event({R.id.lay1_layout1, R.id.lay1_layout2, R.id.adress_layout, R.id.select_time, R.id.tower_commit})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.lay1_layout1:
                lay1Layout1Img.setImageResource(R.drawable.but_checked);
                lay1Layout2Img.setImageResource(R.drawable.but_uncheck);
                lay1check = 1;
                break;
            case R.id.lay1_layout2:
                lay1Layout2Img.setImageResource(R.drawable.but_checked);
                lay1Layout1Img.setImageResource(R.drawable.but_uncheck);
                lay1check = 2;
                break;
            case R.id.adress_layout:
                Intent intent = new Intent(BadTowerActivity.this,CommonListActivity.class);
                intent.putExtra("title","选择区县");
                intent.putStringArrayListExtra("items", new Constant().getQuxian());
                startActivityForResult(intent,1);
                break;
            case R.id.select_time:
                DateTimeDialog dialog = new DateTimeDialog(BadTowerActivity.this,null);
                dialog.dateTimePicKDialog(select_time_tv);
                break;
            case R.id.tower_commit:
                showToast(lay1check+"");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101){
            address_tv.setText(data.getStringExtra("address"));
        }
    }
}
