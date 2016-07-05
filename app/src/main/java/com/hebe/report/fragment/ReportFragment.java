package com.hebe.report.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hebe.report.R;
import com.hebe.report.base.BaseFragment;
import com.hebe.report.bean.ReportItemBean;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hebe on 16/7/5.
 */

public class ReportFragment extends BaseFragment {

    private View view;
    @ViewInject(R.id.report_viewpager)
    private ViewPager report_viewpager;
    private List<ReportItemBean> beanList1 = new ArrayList<ReportItemBean>();
    private List<ReportItemBean> beanList2 = new ArrayList<ReportItemBean>();
    private FPAdapter adapter;
    private ReportInnerFragment innerFragment1;
    private ReportInnerFragment innerFragment2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_report_layout,null);
        x.view().inject(this,view);

        initData();
        adapter = new FPAdapter(getChildFragmentManager());
        report_viewpager.setAdapter(adapter);
        return view;
    }

    public void initData(){
        if (beanList1.size() > 0){
            return;
        }
        ReportItemBean reb1 = new ReportItemBean(R.drawable.message_icon,"不良短信");
        ReportItemBean reb2 = new ReportItemBean(R.drawable.tele_icon,"诈骗电话");
        ReportItemBean reb3 = new ReportItemBean(R.drawable.phone_icon,"骚扰电话");
        ReportItemBean reb4 = new ReportItemBean(R.drawable.internet_icon,"不良网站");
        ReportItemBean reb5 = new ReportItemBean(R.drawable.email_icon,"垃圾邮件");
        ReportItemBean reb6 = new ReportItemBean(R.drawable.app_cion,"不良APP");
        ReportItemBean reb7 = new ReportItemBean(R.drawable.tower_icon,"不良基站");
        ReportItemBean reb8 = new ReportItemBean(R.drawable.wifi_icon,"不良WIFI");
        ReportItemBean reb9 = new ReportItemBean(R.drawable.sim_icon,"手机实名制");
        beanList1.add(reb1);
        beanList1.add(reb2);
        beanList1.add(reb3);
        beanList1.add(reb4);
        beanList1.add(reb5);
        beanList1.add(reb6);
        beanList1.add(reb7);
        beanList1.add(reb8);
        beanList1.add(reb9);

        ReportItemBean reb21 = new ReportItemBean(R.drawable.account_icon,"个人信息泄露");
        ReportItemBean reb22 = new ReportItemBean(R.drawable.info_icon,"不良舆情");
        ReportItemBean reb23 = new ReportItemBean(R.drawable.known_icon,"知识产权侵权");
        ReportItemBean reb24 = new ReportItemBean(R.drawable.other_icon,"其他举报");
        ReportItemBean reb25 = new ReportItemBean(0,"");
        beanList2.add(reb21);
        beanList2.add(reb22);
        beanList2.add(reb23);
        beanList2.add(reb24);
        beanList2.add(reb25);
        beanList2.add(reb25);
        beanList2.add(reb25);
        beanList2.add(reb25);
        beanList2.add(reb25);
    }

    private class FPAdapter extends FragmentPagerAdapter{

        public FPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                if (innerFragment1 == null)
                innerFragment1 = new ReportInnerFragment();
                innerFragment1.setItemBeens(beanList1);
                return innerFragment1;
            }else {
                if (innerFragment2 == null)
                innerFragment2 = new ReportInnerFragment();
                innerFragment2.setItemBeens(beanList2);
                return innerFragment2;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
