package com.hebe.report.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebe.report.Constant.Constant;
import com.hebe.report.R;
import com.hebe.report.activity.BadWifiActivity;
import com.hebe.report.activity.LoginActivity;
import com.hebe.report.activity.MyReportListActivity;
import com.hebe.report.activity.UserInfoActivity;
import com.hebe.report.base.BaseFragment;
import com.hebe.report.bean.CommonResultBean;
import com.hebe.report.bean.UpdateBean;
import com.hebe.report.utils.ToolsSp;
import com.hebe.report.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by Hebe on 16/7/5.
 */

public class MeFragment extends BaseFragment {

    @ViewInject(R.id.myreport_layout)
    private LinearLayout myreport_layout;
    @ViewInject(R.id.userinfo_layout)
    private LinearLayout userinfo_layout;
    @ViewInject(R.id.update_layout)
    private LinearLayout update_layout;
    @ViewInject(R.id.version_tv)
    private TextView version_tv;
    private View view;
    private AlertDialog downdialog = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me_layout,null);
        x.view().inject(this,view);

        myreport_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyReportListActivity.class));
            }
        });
        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(),0);
            version_tv.setText(info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        update_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showdialog();
                checkUpdate();
            }
        });
        userinfo_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
            }
        });

        return view;
    }

    public void showdialog(final UpdateBean bean){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.download_layout,null,false);
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

    public void checkUpdate(){
        RequestParams params = Utils.getDefaultParams("App/updateVersion");
        params.addBodyParameter("user_token", Utils.getUserToken(getActivity()));
        params.addBodyParameter("device", "android");

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                UpdateBean bean = Utils.jsonParase(result,UpdateBean.class);
                if (bean != null && result.contains("http")){
                    PackageInfo info = null;
                    try {
                        info = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(),0);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (!info.versionName.equals(bean.getVersion())){
                        showdialog(bean);
                    }else {
                        showToast("已是最新版本");
                    }

                }else {
                    showToast("检查失败");
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
                getActivity().startActivity(intent);
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
