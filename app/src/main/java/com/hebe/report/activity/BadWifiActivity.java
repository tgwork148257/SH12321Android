package com.hebe.report.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebe.report.Constant.Constant;
import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;
import com.hebe.report.bean.CommonResultBean;
import com.hebe.report.utils.DateTimeDialog;
import com.hebe.report.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * 不良wifi
 * Created by Hebe on 7-7-007.
 */

public class BadWifiActivity extends BaseActivity {

    @ViewInject(R.id.et_badwifi_name)
    EditText etBadwifiName;
    @ViewInject(R.id.badwifi_address)
    LinearLayout badwifiAddress;
    @ViewInject(R.id.et_badwifi_detail)
    EditText etBadwifiDetail;
    @ViewInject(R.id.select_time)
    LinearLayout selectTime;
    @ViewInject(R.id.badwifi_commit)
    Button badwifiCommit;
    @ViewInject(R.id.address_tv)
    private TextView address_tv;
    @ViewInject(R.id.select_time_tv)
    private TextView select_time_tv;
    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badwifi_activity);
        x.view().inject(this);

        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navi_title.setText("不良WIFI");
    }

    @Event({R.id.badwifi_address, R.id.select_time, R.id.badwifi_commit})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.badwifi_address:
                Intent intent = new Intent(BadWifiActivity.this,CommonListActivity.class);
                intent.putExtra("title","选择区县");
                intent.putStringArrayListExtra("items", new Constant().getQuxian());
                startActivityForResult(intent,1);
                break;
            case R.id.select_time:
                DateTimeDialog dialog = new DateTimeDialog(BadWifiActivity.this,null);
                dialog.dateTimePicKDialog(select_time_tv);
                break;
            case R.id.badwifi_commit:
                if (!TextUtils.isEmpty(address_tv.getText().toString().trim())&&!TextUtils.isEmpty(etBadwifiDetail.getText().toString().trim())&&!TextUtils.isEmpty(select_time_tv.getText().toString().trim())&&!TextUtils.isEmpty(etBadwifiName.getText().toString().trim())){
                    showProgressDialog("正在举报");
                    RequestParams params = Utils.getDefaultParams("App/reportWifi");
                    params.addBodyParameter("user_token", Utils.getUserToken(BadWifiActivity.this));
                    params.addBodyParameter("report_address",address_tv.getText().toString().trim()+etBadwifiDetail.getText().toString().trim());
                    params.addBodyParameter("name",etBadwifiName.getText().toString().trim());
                    params.addBodyParameter("call_time",select_time_tv.getText().toString().trim());

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
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            address_tv.setText(data.getStringExtra("address"));
        }
    }
}
