package com.hebe.report.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;

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

    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        tab1.setBackgroundColor(getResources().getColor(R.color.green));
        navi_title.setText("举报");
    }

    @Event({R.id.tab1,R.id.tab2,R.id.tab3})
    private void onTabClick(View view){
        resetBut();
        switch (view.getId()){
            case R.id.tab1:
                tab1.setBackgroundColor(getResources().getColor(R.color.green));
                navi_title.setText("举报");
                break;
            case R.id.tab2:
                tab2.setBackgroundColor(getResources().getColor(R.color.green));
                navi_title.setText("咨询");
                break;
            case R.id.tab3:
                tab3.setBackgroundColor(getResources().getColor(R.color.green));
                navi_title.setText("我的");
                break;
        }
    }


    public void resetBut(){
        tab1.setBackgroundColor(getResources().getColor(R.color.white));
        tab2.setBackgroundColor(getResources().getColor(R.color.white));
        tab3.setBackgroundColor(getResources().getColor(R.color.white));
    }

}
