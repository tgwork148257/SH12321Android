package com.hebe.report.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
 * 不良APP
 * Created by Hebe on 7-7-007.
 */

public class BadAppActivity extends BaseActivity {

    @ViewInject(R.id.et_badapp_name)
    EditText etBadappName;
    @ViewInject(R.id.et_badapp_from)
    EditText etBadappFrom;
    @ViewInject(R.id.et_badapp_content)
    EditText etBadappContent;
    @ViewInject(R.id.badapp_commit)
    Button badappCommit;
    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badapp_activity);
        x.view().inject(this);

        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navi_title.setText("不良APP");
    }

    @Event({R.id.badapp_commit})
    private void onClick(View view) {
        String name = etBadappName.getText().toString().trim();
        String from = etBadappFrom.getText().toString().trim();
        String content = etBadappContent.getText().toString().trim();
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(from) &&!TextUtils.isEmpty(content)){
            showProgressDialog("正在举报");
            RequestParams params = Utils.getDefaultParams("App/reportApp");
            params.addBodyParameter("user_token", Utils.getUserToken(BadAppActivity.this));
            params.addBodyParameter("name",etBadappName.getText().toString().trim());
            params.addBodyParameter("source",etBadappFrom.getText().toString().trim());
            params.addBodyParameter("content",etBadappContent.getText().toString().trim());

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
            showToast("请填写完整信息");
        }


    }
}
