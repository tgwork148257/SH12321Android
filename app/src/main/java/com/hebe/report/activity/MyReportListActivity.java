package com.hebe.report.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;
import com.hebe.report.bean.MyReport;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的举报
 * Created by Hebe on 7-11-011.
 */

public class MyReportListActivity extends BaseActivity {

    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;
    @ViewInject(R.id.commonlist)
    private ListView report_list;

    private List<MyReport> reports = new ArrayList<MyReport>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commonlist_activity);
        x.view().inject(this);
        navi_title.setText("我的举报");
        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        reports.add(new MyReport(1,"不良短信","2016-6-6"));
        reports.add(new MyReport(1,"诈骗电话","2016-6-6"));
        reports.add(new MyReport(1,"骚扰电话","2016-6-6"));
        reports.add(new MyReport(1,"不良网站","2016-6-6"));
        reports.add(new MyReport(1,"垃圾邮件","2016-6-6"));
        reports.add(new MyReport(1,"不良APP","2016-6-6"));
        reports.add(new MyReport(1,"伪基站","2016-6-6"));
        reports.add(new MyReport(1,"不良WIFI","2016-6-6"));
        reports.add(new MyReport(1,"手机实名制","2016-6-6"));
        reports.add(new MyReport(1,"个人信息泄露","2016-6-6"));
        reports.add(new MyReport(1,"不良舆情","2016-6-6"));
        reports.add(new MyReport(1,"知识产权侵权","2016-6-6"));
        reports.add(new MyReport(1,"其他","2016-6-6"));
        report_list.setAdapter(new ReportListAdapter());
        report_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (reports.get(position).getReporttype()){
                    case "不良短信":
                        startActivity(new Intent(MyReportListActivity.this,BadMessageResultActivity.class));
                        break;
                    case "诈骗电话":
                        startActivity(new Intent(MyReportListActivity.this,SwindlePhoneResultActivity.class));
                        break;
                    case "骚扰电话":
                        startActivity(new Intent(MyReportListActivity.this,HarassPhoneResultActivity.class));
                        break;
                    case "不良网站":
                        startActivity(new Intent(MyReportListActivity.this,BadNetResultActivity.class));
                        break;
                    case "不良APP":
                        startActivity(new Intent(MyReportListActivity.this,BadAppResultActivity.class));
                        break;
                    case "伪基站":
                        startActivity(new Intent(MyReportListActivity.this,BadTowerResultActivity.class));
                        break;
                    case "不良WIFI":
                        startActivity(new Intent(MyReportListActivity.this,BadWifiResultActivity.class));
                        break;
                    case "手机实名制":
                        startActivity(new Intent(MyReportListActivity.this,PhoneVerifyResultActivity.class));
                        break;
                    case "个人信息泄露":
                    case "不良舆情":
                    case "知识产权侵权":
                    case "其他":
                        startActivity(new Intent(MyReportListActivity.this,MoreReportResultActivity.class));
                        break;
                }
            }
        });
    }

    private class ReportListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return reports.size();
        }

        @Override
        public Object getItem(int position) {
            return reports.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null){
                convertView = LayoutInflater.from(MyReportListActivity.this).inflate(R.layout.myreportlist_item,null,false);
                holder = new ViewHolder();
                holder.id = (TextView) convertView.findViewById(R.id.myreport_item_id);
                holder.type = (TextView) convertView.findViewById(R.id.myreport_item_type);
                holder.time = (TextView) convertView.findViewById(R.id.myreport_item_time);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.id.setText(reports.get(position).getReportid()+"");
            holder.type.setText(reports.get(position).getReporttype());
            holder.time.setText(reports.get(position).getReporttime());
            return convertView;
        }
    }

    private class ViewHolder{
        private TextView id;
        private TextView type;
        private TextView time;
    }
}
