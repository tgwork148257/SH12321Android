package com.hebe.report.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hebe.report.Constant.Constant;
import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;
import com.hebe.report.bean.MyReport;
import com.hebe.report.bean.VerifyCodeBean;
import com.hebe.report.utils.ToolsSp;
import com.hebe.report.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
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

    private MyReport myReport = new MyReport();
    private int page = 1;
    private ReportListAdapter adapter;

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
        adapter = new ReportListAdapter();
        report_list.setAdapter(adapter);
        report_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (myReport.getData().getList().get(position).getType()){
                    case "4":
                        startActivity(new Intent(MyReportListActivity.this,BadMessageResultActivity.class));
                        break;
                    case "3":
                        startActivity(new Intent(MyReportListActivity.this,SwindlePhoneResultActivity.class));
                        break;
                    case "2":
                        startActivity(new Intent(MyReportListActivity.this,HarassPhoneResultActivity.class));
                        break;
                    case "1":
                        startActivity(new Intent(MyReportListActivity.this,BadNetResultActivity.class));
                        break;
                    case "5":
                        startActivity(new Intent(MyReportListActivity.this,BadAppResultActivity.class));
                        break;
                    case "6":
                        startActivity(new Intent(MyReportListActivity.this,BadTowerResultActivity.class));
                        break;
                    case "7":
                        startActivity(new Intent(MyReportListActivity.this,BadWifiResultActivity.class));
                        break;
                    case "11":
                        startActivity(new Intent(MyReportListActivity.this,PhoneVerifyResultActivity.class));
                        break;
                    case "8":
                    case "10":
                    case "9":
                    case "12":
                        startActivity(new Intent(MyReportListActivity.this,MoreReportResultActivity.class));
                        break;
                }
            }
        });
        getReportList();
    }

    private class ReportListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (myReport == null || myReport.getData() == null||myReport.getData().getList() == null){
                return 0;
            }else {
                return myReport.getData().getList().size();
            }
        }

        @Override
        public Object getItem(int position) {
            if (myReport == null ||myReport.getData() == null|| myReport.getData().getList() == null){
                return null;
            }else {
                return myReport.getData().getList().get(position);
            }
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
            holder.id.setText(myReport.getData().getList().get(position).getJw_id());
            holder.type.setText(myReport.getData().getList().get(position).getType());
            holder.time.setText(myReport.getData().getList().get(position).getReport_time());
            return convertView;
        }
    }

    public void getReportList(){
        RequestParams params = Utils.getDefaultParams("App/reportList");
        params.addBodyParameter("user_token", Utils.getUserToken(this));
        params.addBodyParameter("page",page+"");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                MyReport bean = Utils.jsonParase(result,MyReport.class);
                if (bean != null && bean.getCode() == 200){
                    if (myReport == null || myReport.getData() == null){
                        myReport = bean;
                    }else {
                        myReport.getData().getList().addAll(bean.getData().getList());
                    }

                    adapter.notifyDataSetChanged();
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

    private class ViewHolder{
        private TextView id;
        private TextView type;
        private TextView time;
    }
}
