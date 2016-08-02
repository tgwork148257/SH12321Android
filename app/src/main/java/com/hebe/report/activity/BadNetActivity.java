package com.hebe.report.activity;

import android.os.Bundle;
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
import com.hebe.report.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

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
                if (!TextUtils.isEmpty(etBadnet.getText().toString().trim()) && lay1check != -1&&!TextUtils.isEmpty(badnetContent.getText().toString().trim())){
                    showProgressDialog("正在举报");
                    RequestParams params = Utils.getDefaultParams("App/reportWebSite");
                    params.addBodyParameter("user_token", Utils.getUserToken(BadNetActivity.this));
                    params.addBodyParameter("report_www",etBadnet.getText().toString().trim());
                    params.addBodyParameter("content",badnetContent.getText().toString().trim());
                    String lay1String="";
                    switch (lay1check){
                        case 1:
                            lay1String= "淫秽色情";
                            break;
                        case 2:
                            lay1String = "钓鱼诈骗";
                            break;
                        case 3:
                            lay1String = "反动及政治敏感";
                            break;
                        case 4:
                            lay1String = "其他";
                            break;
                    }
                    params.addBodyParameter("report_type",lay1String);

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
                    if (TextUtils.isEmpty(etBadnet.getText().toString().trim())){
                        showToast("请填写不良网站网址");
                        return;
                    }
                    if (lay1check == -1){
                        showToast("请选择不良类型");
                        return;
                    }
                    if (TextUtils.isEmpty(badnetContent.getText().toString().trim())){
                        showToast("请填写不良内容");
                        return;
                    }
                }
                break;
        }
    }
}
