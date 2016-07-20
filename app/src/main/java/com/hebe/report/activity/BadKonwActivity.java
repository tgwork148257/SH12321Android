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
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 知识产权侵权
 * Created by Hebe on 7-7-007.
 */

public class BadKonwActivity extends BaseActivity {

    @ViewInject(R.id.et_content)
    private EditText et_content;
    @ViewInject(R.id.commit_bt)
    private Button commit_bt;

    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badknow_activity);
        x.view().inject(this);

        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navi_title.setText("知识产权侵权");

        commit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_content.getText().toString().trim())){
                    showProgressDialog("正在举报");
                    RequestParams params = Utils.getDefaultParams("App/reportIPR");
                    params.addBodyParameter("user_token", Utils.getUserToken(BadKonwActivity.this));
                    params.addBodyParameter("content",et_content.getText().toString().trim());

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
        });
    }
}
