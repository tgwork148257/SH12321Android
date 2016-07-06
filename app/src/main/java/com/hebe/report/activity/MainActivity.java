package com.hebe.report.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;
import com.hebe.report.fragment.MeFragment;
import com.hebe.report.fragment.NewsFragment;
import com.hebe.report.fragment.ReportFragment;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.tab1)
    private LinearLayout tab1;
    @ViewInject(R.id.tab2)
    private LinearLayout tab2;
    @ViewInject(R.id.tab3)
    private LinearLayout tab3;

    @ViewInject(R.id.tab1_img)
    private ImageView tab1_img;
    @ViewInject(R.id.tab1_tv)
    private TextView tab1_tv;

    @ViewInject(R.id.tab2_img)
    private ImageView tab2_img;
    @ViewInject(R.id.tab2_tv)
    private TextView tab2_tv;

    @ViewInject(R.id.tab3_img)
    private ImageView tab3_img;
    @ViewInject(R.id.tab3_tv)
    private TextView tab3_tv;

    @ViewInject(R.id.navi_title)
    private TextView navi_title;

    private ReportFragment reportFragment;
    private NewsFragment newsFragment;
    private MeFragment meFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        tab1_img.setImageDrawable(getResources().getDrawable(R.drawable.report_select));
        tab1_tv.setTextColor(getResources().getColor(R.color.green));
        navi_title.setText("举报");
        if (reportFragment == null){
            reportFragment = new ReportFragment();
        }
        showFragment(reportFragment,R.id.layout_main,"report",true);
    }



    @Event({R.id.tab1,R.id.tab2,R.id.tab3})
    private void onTabClick(View view){
        resetBut();
        switch (view.getId()){
            case R.id.tab1:
                if (reportFragment == null){
                    reportFragment = new ReportFragment();
                }
                showFragment(reportFragment,R.id.layout_main,"report",true);
                tab1_img.setImageDrawable(getResources().getDrawable(R.drawable.report_select));
                tab1_tv.setTextColor(getResources().getColor(R.color.green));
                navi_title.setText("举报");
                break;
            case R.id.tab2:
                if (newsFragment == null){
                    newsFragment = new NewsFragment();
                }
                showFragment(newsFragment,R.id.layout_main,"news",true);
                tab2_img.setImageDrawable(getResources().getDrawable(R.drawable.news_select));
                tab2_tv.setTextColor(getResources().getColor(R.color.green));
                navi_title.setText("资讯");
                break;
            case R.id.tab3:
                if (meFragment == null){
                    meFragment = new MeFragment();
                }
                showFragment(meFragment,R.id.layout_main,"me",true);
                tab3_img.setImageDrawable(getResources().getDrawable(R.drawable.me_select));
                tab3_tv.setTextColor(getResources().getColor(R.color.green));
                navi_title.setText("我的");
                break;
        }
    }




    public void resetBut(){
        tab1_img.setImageDrawable(getResources().getDrawable(R.drawable.report_unselect));
        tab1_tv.setTextColor(getResources().getColor(R.color.text_black));
        tab2_img.setImageDrawable(getResources().getDrawable(R.drawable.news_unselect));
        tab2_tv.setTextColor(getResources().getColor(R.color.text_black));
        tab3_img.setImageDrawable(getResources().getDrawable(R.drawable.me_unselect));
        tab3_tv.setTextColor(getResources().getColor(R.color.text_black));
    }

}
