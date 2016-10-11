package com.hebe.report.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebe.report.Constant.Constant;
import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;
import com.hebe.report.bean.UpdateBean;
import com.hebe.report.fragment.MeFragment;
import com.hebe.report.fragment.NewsFragment;
import com.hebe.report.fragment.ReportFragment;
import com.hebe.report.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

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
    private AlertDialog downdialog = null;
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
        if (Constant.check == 0){
            checkUpdate();
            Constant.check = 1;
        }

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

    public void checkUpdate(){
        RequestParams params = Utils.getDefaultParams("App/updateVersion");
        params.addBodyParameter("user_token", Utils.getUserToken(this));
        params.addBodyParameter("device", "android");

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                UpdateBean bean = Utils.jsonParase(result,UpdateBean.class);
                if (bean != null && result.contains("http")){
                    PackageInfo info = null;
                    try {
                        info = getPackageManager().getPackageInfo(getPackageName(),0);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (!info.versionName.equals(bean.getVersion())){
                        showdialog(bean);
                    }else {
                        showToast("已是最新版本");
                    }

                }else {
                    showToast("检查新版本失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                showToast("检查失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void showdialog(final UpdateBean bean){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        final View view = LayoutInflater.from(this).inflate(R.layout.download_layout,null,false);
        builder.setView(view);
        downdialog = builder.create();
        downdialog.show();


        final TextView content = (TextView) view.findViewById(R.id.download_content);
        final Button cancle = (Button) view.findViewById(R.id.download_cancle);
        final Button down = (Button) view.findViewById(R.id.download_down);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downdialog.dismiss();
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                down.setEnabled(false);
                cancle.setEnabled(false);
                download(content,bean.getUrl());
                cancle.setVisibility(View.GONE);
                down.setVisibility(View.GONE);
            }
        });
    }

    public void download(final TextView view,String downurl){
        RequestParams params = new RequestParams(downurl);
        final File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/sh12321.apk");
        if (file.exists())
            file.delete();
        params.setSaveFilePath(file.getAbsolutePath());
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                view.setText("已完成:" + (current * 100/total) + "%");
            }

            @Override
            public void onSuccess(File result) {
                showToast("下载成功");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(file.getAbsolutePath())),"application/vnd.android.package-archive");
                startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                showToast("下载失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                downdialog.dismiss();
            }
        });
    }

}
