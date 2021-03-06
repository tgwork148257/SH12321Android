package com.hebe.report.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;
import com.hebe.report.bean.CommonResultBean;
import com.hebe.report.bean.TowerResultBean;
import com.hebe.report.bean.WifiResultBean;
import com.hebe.report.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 不良wifi处理结果
 * Created by Hebe on 7-7-007.
 */

public class BadWifiResultActivity extends BaseActivity {

    @ViewInject(R.id.textview1)
    TextView textview1;
    @ViewInject(R.id.textview2)
    TextView textview2;
    @ViewInject(R.id.textview3)
    TextView textview3;
    @ViewInject(R.id.textview4)
    TextView textview4;
    @ViewInject(R.id.check1)
    ImageView check1;
    @ViewInject(R.id.check2)
    ImageView check2;
    @ViewInject(R.id.summit)
    Button summit;
    @ViewInject(R.id.starbar)
    RatingBar starbar;
    @ViewInject(R.id.grade_tv)
    TextView grade_tv;
    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;

    private String jwid;
    private int check = -1;
    private int isget = -1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badwifi_result_activity);
        x.view().inject(this);

        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navi_title.setText("处理结果");
        jwid = getIntent().getStringExtra("jwid");
        summit.setVisibility(View.GONE);
        getinfo();
        showProgressDialog("正在加载");
        starbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == 0){
                    starbar.setRating(0.5f);
                    grade_tv.setText("1");
                }else {
                    grade_tv.setText((int)(rating*2)+"");
                }
            }
        });
    }

    public void getinfo() {
        RequestParams params = Utils.getDefaultParams("App/getReportDetails");
        params.addBodyParameter("user_token", Utils.getUserToken(this));
        params.addBodyParameter("jw_id", jwid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                closeProgressDialog();
                WifiResultBean bean = Utils.jsonParase(result, WifiResultBean.class);
                if (bean != null && bean.getCode() == 200) {
                    textview1.setText(bean.getData().getOperator_rs());
                    textview2.setText(bean.getData().getName());
                    textview3.setText(bean.getData().getCall_time());
                    textview4.setText(bean.getData().getReport_address());
                    isget = 1;
                    if (bean.getData().getFeedback() != null && bean.getData().getFeedback().equals("1")){
                        starbar.setIsIndicator(true);
                        check1.setEnabled(false);
                        check2.setEnabled(false);
                        if (bean.getData().getFeedback_result() != null && bean.getData().getFeedback_result().equals("已解决")){
                            check = 1;
                            check1.setImageResource(R.drawable.but_checked);
                            check2.setImageResource(R.drawable.but_uncheck);
                        }else if (bean.getData().getFeedback_result() != null && bean.getData().getFeedback_result().equals("未解决")){
                            check = 2;
                            check2.setImageResource(R.drawable.but_checked);
                            check1.setImageResource(R.drawable.but_uncheck);
                        }
                        if (bean.getData().getFeedback_score() != null){
                            try {
                                float rating = Float.parseFloat(bean.getData().getFeedback_score());
                                starbar.setRating(rating/2);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                starbar.setRating(0f);
                            }
                        }
                        summit.setEnabled(false);
                        summit.setVisibility(View.GONE);
                    }else {
                        summit.setEnabled(true);
                        summit.setVisibility(View.VISIBLE);
                    }
                } else {
                    showToast("请重试");
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
    }

    public void submit(){
        RequestParams params = Utils.getDefaultParams("App/reportFeedback");
        params.addBodyParameter("user_token", Utils.getUserToken(this));
        params.addBodyParameter("jw_id", jwid);
        params.addBodyParameter("feedback", check+"");
        params.addBodyParameter("grade",grade_tv.getText().toString().trim());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                closeProgressDialog();
                CommonResultBean bean = Utils.jsonParase(result, CommonResultBean.class);
                if (bean != null && bean.getCode() == 200) {
                    showToast("反馈成功");
                    finish();
                } else {
                    showToast("请重试");
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
    }

    @Event({R.id.check1, R.id.check2, R.id.summit})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.check1:
                check = 1;
                check1.setImageResource(R.drawable.but_checked);
                check2.setImageResource(R.drawable.but_uncheck);
                break;
            case R.id.check2:
                check = 2;
                check2.setImageResource(R.drawable.but_checked);
                check1.setImageResource(R.drawable.but_uncheck);
                break;
            case R.id.summit:
                if (isget != 1){
                    showToast("请重试");
                    return;
                }
                if (check == -1){
                    showToast("请选择是否解决");
                    return;
                }
                showProgressDialog("正在提交");
                submit();
                break;
        }
    }
}
