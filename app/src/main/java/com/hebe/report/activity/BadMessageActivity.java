package com.hebe.report.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hebe.report.Constant.Constant;
import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;
import com.hebe.report.bean.CommonResultBean;
import com.hebe.report.utils.ToolsSp;
import com.hebe.report.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 不良短信
 * Created by Hebe on 7-7-007.
 */

public class BadMessageActivity extends BaseActivity {

    @ViewInject(R.id.navi_title)
    private TextView navi_title;

    @ViewInject(R.id.navi_back)
    private ImageView navi_back;

    @ViewInject(R.id.message_phone_receiver)
    private EditText message_phone_receiver;

    @ViewInject(R.id.message_phone_send)
    private EditText message_phone_send;
    @ViewInject(R.id.message_phone_content)
    private EditText message_phone_content;
    @ViewInject(R.id.message_submmit)
    private Button message_submmit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badmessage_activity);
        x.view().inject(this);

        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navi_title.setText("不良短信");

        message_submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone1 = message_phone_receiver.getText().toString().trim();
                String phone2 = message_phone_send.getText().toString().trim();
                String content = message_phone_content.getText().toString().trim();
                if (isPhoneNumber(phone1)&&isPhoneNumber(phone2)&&!TextUtils.isEmpty(content)){
                    showProgressDialog("正在举报");
                    RequestParams params = Utils.getDefaultParams("App/reportMessage");
                    params.addBodyParameter("user_token", Utils.getUserToken(BadMessageActivity.this));
                    params.addBodyParameter("accept_mobile",phone2);
                    params.addBodyParameter("report_mobile",phone1);
                    params.addBodyParameter("content",content);
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
                    showToast("输入信息有误");
                }
            }
        });
    }
}
