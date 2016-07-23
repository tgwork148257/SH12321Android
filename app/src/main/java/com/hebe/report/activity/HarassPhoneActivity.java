package com.hebe.report.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;
import com.hebe.report.bean.CommonResultBean;
import com.hebe.report.utils.DateTimeDialog;
import com.hebe.report.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
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
    @ViewInject(R.id.select_time_tv)
    private TextView select_time_tv;
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
                DateTimeDialog dialog = new DateTimeDialog(HarassPhoneActivity.this,null);
                dialog.dateTimePicKDialog(select_time_tv);
                break;
            case R.id.harass_commit:
                if (isPhoneNumber(etPhone1.getText().toString().trim())&&isPhoneNumber(etPhone2.getText().toString().trim())&&lay1check !=-1&&lay2check!=-1&& lay3check != -1&& !TextUtils.isEmpty(select_time_tv.getText().toString().trim())&&!TextUtils.isEmpty(harassContent.getText().toString().trim())){
                    showProgressDialog("正在举报");
                    RequestParams params = Utils.getDefaultParams("App/harassMobile");
                    params.addBodyParameter("user_token", Utils.getUserToken(HarassPhoneActivity.this));
                    params.addBodyParameter("accept_mobile",etPhone2.getText().toString().trim());
                    params.addBodyParameter("report_mobile",etPhone1.getText().toString().trim());
                    params.addBodyParameter("content",harassContent.getText().toString().trim());
                    String lay1String="";
                    switch (lay1check){
                        case 1:
                            lay1String= "响一声就挂";
                            break;
                        case 2:
                            lay1String = "自动语音骚扰";
                            break;
                        case 3:
                            lay1String = "人工骚扰";
                            break;
                    }
                    params.addBodyParameter("harass_type",lay1String);
                    String lay2String="";
                    switch (lay2check){
                        case 1:
                            lay2String= "色情";
                            break;
                        case 2:
                            lay2String = "发票";
                            break;
                        case 3:
                            lay2String = "违禁品";
                            break;
                        case 4:
                            lay2String = "高利贷";
                            break;
                        case 5:
                            lay2String = "反动";
                            break;
                        case 6:
                            lay2String = "广告骚扰";
                            break;
                    }
                    params.addBodyParameter("report_type",lay2String);
                    String lay3String="";
                    switch (lay3check){
                        case 1:
                            lay3String= "三分钟以下";
                            break;
                        case 2:
                            lay3String = "3-5分钟";
                            break;
                        case 3:
                            lay3String = "5-10分钟";
                            break;
                        case 4:
                            lay3String = "10分钟以上";
                            break;
                    }
                    params.addBodyParameter("talk_time",lay3String);
                    params.addBodyParameter("call_time",select_time_tv.getText().toString().trim());

                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            closeProgressDialog();
                            CommonResultBean bean = Utils.jsonParase(result,CommonResultBean.class);
                            if (bean != null && bean.getCode() == 200){
                                showToast("举报成功");
                                finish();
                            }else {
                                showToast("举报失败");
                            }
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            closeProgressDialog();
                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });
                }else {
                    showToast("请填写完整信息");
                }
                break;
        }
    }
}
