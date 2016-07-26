package com.hebe.report.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;
import com.hebe.report.bean.MyReport;
import com.hebe.report.bean.NewsDetailBean;
import com.hebe.report.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Hebe on 16/7/24.
 */

public class NewsDetailActivity extends BaseActivity{
    private String news_id;
    private String title;
    private String from;
    private String time;
    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;
    @ViewInject(R.id.news_detail_main)
    private LinearLayout main;
    @ViewInject(R.id.news_title)
    private TextView news_title;
    @ViewInject(R.id.news_from)
    private TextView news_from;
    @ViewInject(R.id.news_time)
    private TextView news_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail_activity);
        x.view().inject(this);
        news_id = getIntent().getStringExtra("news_id");
        title = getIntent().getStringExtra("title");
        from = getIntent().getStringExtra("from");
        time = getIntent().getStringExtra("time");
        navi_title.setText("新闻详情");
        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        news_title.setText(title);
        news_time.setText(time);
        news_from.setText("来自: "+from);
        getNewsDetail();
    }

    public void getNewsDetail(){
        final RequestParams params = Utils.getDefaultParams("App/getNewsDetails");
        params.addBodyParameter("user_token", Utils.getUserToken(this));
        params.addBodyParameter("news_id",news_id);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                NewsDetailBean bean = Utils.jsonParase(result,NewsDetailBean.class);
                if (bean != null && bean.getCode() == 200){
                    setView(bean);
                }else {
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

    public void setView(NewsDetailBean bean){
        if (bean != null){
            for (NewsDetailBean.DataBean data:bean.getData()) {
                if (data.getType() == 1){
                    TextView textview = new TextView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(px2dip(this,16),0,px2dip(this,16),0);
                    textview.setLayoutParams(params);

                    textview.setTextColor(getResources().getColor(R.color.text_black));
                    textview.setTextSize(16.0f);
                    textview.setText(data.getText());
                    main.addView(textview);
                }else if (data.getType() == 2){
                    ImageView imageview = new ImageView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(data.getWidth(), data.getHeight());
                    imageview.setLayoutParams(params);
                    main.addView(imageview);
                    x.image().bind(imageview,data.getUrl());
                }
            }
        }
    }
}
