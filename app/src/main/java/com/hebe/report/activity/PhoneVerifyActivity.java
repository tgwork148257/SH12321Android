package com.hebe.report.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hebe.report.Constant.Constant;
import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;
import com.hebe.report.bean.CommonResultBean;
import com.hebe.report.bean.FileResultBean;
import com.hebe.report.bean.MyReport;
import com.hebe.report.utils.DateTimeDialog;
import com.hebe.report.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;


/**
 * 手机实名制
 * Created by Hebe on 7-7-007.
 */

public class PhoneVerifyActivity extends BaseActivity {

    @ViewInject(R.id.lay1_layout1_img)
    ImageView lay1Layout1Img;
    @ViewInject(R.id.lay1_layout1)
    LinearLayout lay1Layout1;
    @ViewInject(R.id.lay1_layout2_img)
    ImageView lay1Layout2Img;
    @ViewInject(R.id.lay1_layout2)
    LinearLayout lay1Layout2;
    @ViewInject(R.id.shitidian_weigui)
    LinearLayout shitidianWeigui;
    @ViewInject(R.id.et_shitidian_name)
    EditText etShitidianName;
    @ViewInject(R.id.et_shitidian_hao)
    EditText etShitidianHao;
    @ViewInject(R.id.shitidian_selecttime)
    LinearLayout shitidianSelecttime;
    @ViewInject(R.id.shitidian_yunyingshang)
    LinearLayout shitidianYunyingshang;
    @ViewInject(R.id.shitidian_address)
    LinearLayout shitidianAddress;
    @ViewInject(R.id.et_shitidian_dizhixiangxi)
    EditText etShitidianDizhixiangxi;
    @ViewInject(R.id.shitidian_photo)
    LinearLayout shitidianPhoto;
    @ViewInject(R.id.shitidian_chikaphoto)
    LinearLayout shitidianChikaphoto;
    @ViewInject(R.id.et_shitidian_miaoshu)
    EditText etShitidianMiaoshu;
    @ViewInject(R.id.shitidian_layout)
    LinearLayout shitidianLayout;
    @ViewInject(R.id.wangdian_weigui)
    LinearLayout wangdianWeigui;
    @ViewInject(R.id.et_wagndian_name)
    EditText etWagndianName;
    @ViewInject(R.id.et_wangdian_hao)
    EditText etWangdianHao;
    @ViewInject(R.id.wangdian_selecttime)
    LinearLayout wangdianSelecttime;
    @ViewInject(R.id.wangdian_yunyingshang)
    LinearLayout wangdianYunyingshang;
    @ViewInject(R.id.et_wangdian_wangzhi)
    EditText etWangdianWangzhi;
    @ViewInject(R.id.et_wangdian_xiaoshoudizhi)
    EditText etWangdianXiaoshoudizhi;
    @ViewInject(R.id.wangdian_photo)
    LinearLayout wangdianPhoto;
    @ViewInject(R.id.wangdian_chikaphoto)
    LinearLayout wangdianChikaphoto;
    @ViewInject(R.id.et_wangdian_miaoshu)
    EditText etWangdianMiaoshu;
    @ViewInject(R.id.wangdian_layout)
    LinearLayout wangdianLayout;
    @ViewInject(R.id.sim_commit)
    Button simCommit;
    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;

    @ViewInject(R.id.shitiweigui_yuanyin_tv)
    private TextView shitiweigui_yuanyin_tv;
    @ViewInject(R.id.shiti_goukashijian_tv)
    private TextView shiti_goukashijian_tv;
    @ViewInject(R.id.shiti_yunyingshang_tv)
    private TextView shiti_yunyingshang_tv;
    @ViewInject(R.id.shiti_quxian_tv)
    private TextView shiti_quxian_tv;
    @ViewInject(R.id.shiti_zhaopian)
    private ImageView shiti_zhaopian;
    @ViewInject(R.id.shiti_benrenzhaopian)
    private ImageView shiti_benrenzhaopian;

    @ViewInject(R.id.wangdian_weigui_tv)
    private TextView wangdian_weigui_tv;
    @ViewInject(R.id.wangdian_goukashijian_tv)
    private TextView wangdian_goukashijian_tv;
    @ViewInject(R.id.wangdian_yunyingshang_tv)
    private TextView wangdian_yunyingshang_tv;
    @ViewInject(R.id.wangdian_dingdanzhaopian)
    private ImageView wangdian_dingdanzhaopian;
    @ViewInject(R.id.wangdian_benrenzhaopian)
    private ImageView wangdian_benrenzhaopian;

    private int lay1check = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phoneverify_activity);
        x.view().inject(this);

        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navi_title.setText("手机实名制举报");
        lay1Layout1Img.setImageResource(R.drawable.but_checked);
    }

    @Event({R.id.lay1_layout1, R.id.lay1_layout2, R.id.shitidian_weigui, R.id.shitidian_selecttime, R.id.shitidian_yunyingshang, R.id.shitidian_address, R.id.shitidian_photo, R.id.shitidian_chikaphoto, R.id.wangdian_weigui, R.id.wangdian_selecttime, R.id.wangdian_yunyingshang, R.id.wangdian_photo, R.id.wangdian_chikaphoto, R.id.sim_commit})
    private void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.lay1_layout1:
                lay1Layout1Img.setImageResource(R.drawable.but_checked);
                lay1Layout2Img.setImageResource(R.drawable.but_uncheck);
                lay1check = 1;
                shitidianLayout.setVisibility(View.VISIBLE);
                wangdianLayout.setVisibility(View.GONE);
                break;
            case R.id.lay1_layout2:
                lay1Layout2Img.setImageResource(R.drawable.but_checked);
                lay1Layout1Img.setImageResource(R.drawable.but_uncheck);
                lay1check = 2;
                shitidianLayout.setVisibility(View.GONE);
                wangdianLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.shitidian_weigui:
                intent = new Intent(PhoneVerifyActivity.this,CommonListActivity.class);
                intent.putExtra("title","违规原因");
                intent.putStringArrayListExtra("items", new Constant().getWeiguishiti());
                startActivityForResult(intent,11);
                break;
            case R.id.shitidian_selecttime:
                DateTimeDialog dialog = new DateTimeDialog(PhoneVerifyActivity.this,null);
                dialog.dateTimePicKDialog(shiti_goukashijian_tv);
                break;
            case R.id.shitidian_yunyingshang:
                intent = new Intent(PhoneVerifyActivity.this,CommonListActivity.class);
                intent.putExtra("title","运营商");
                intent.putStringArrayListExtra("items", new Constant().getYunyingshang());
                startActivityForResult(intent,12);
                break;
            case R.id.shitidian_address:
                intent = new Intent(PhoneVerifyActivity.this,CommonListActivity.class);
                intent.putExtra("title","所在区县");
                intent.putStringArrayListExtra("items", new Constant().getQuxian());
                startActivityForResult(intent,13);
                break;
            case R.id.shitidian_photo:
                showSelect(14);
                break;
            case R.id.shitidian_chikaphoto:
                showSelect(15);
                break;
            case R.id.wangdian_weigui:
                intent = new Intent(PhoneVerifyActivity.this,CommonListActivity.class);
                intent.putExtra("title","违规原因");
                intent.putStringArrayListExtra("items", new Constant().getWeiguiwangdian());
                startActivityForResult(intent,21);
                break;
            case R.id.wangdian_selecttime:
                DateTimeDialog dialog1 = new DateTimeDialog(PhoneVerifyActivity.this,null);
                dialog1.dateTimePicKDialog(wangdian_goukashijian_tv);
                break;
            case R.id.wangdian_yunyingshang:
                intent = new Intent(PhoneVerifyActivity.this,CommonListActivity.class);
                intent.putExtra("title","运营商");
                intent.putStringArrayListExtra("items", new Constant().getYunyingshang());
                startActivityForResult(intent,22);
                break;
            case R.id.wangdian_photo:
                showSelect(23);
                break;
            case R.id.wangdian_chikaphoto:
                showSelect(24);
                break;
            case R.id.sim_commit:
                if (lay1check ==1){
                    String shitidianming = etShitidianName.getText().toString().trim();
                    String shitihao = etShitidianHao.getText().toString().trim();
                    String shijian = shiti_goukashijian_tv.getText().toString().trim();
                    String address = shiti_quxian_tv.getText().toString().trim()+etShitidianDizhixiangxi.getText().toString().trim();
                    String content = etShitidianMiaoshu.getText().toString().trim();
                    if (shiti11 != -1 && shiti12 != -1 && shiti13 != -1 && !TextUtils.isEmpty(shitidianming)&&isPhoneNumber(shitihao)&&!TextUtils.isEmpty(shijian)&&!TextUtils.isEmpty(address)&&!TextUtils.isEmpty(phototmep14)&&!TextUtils.isEmpty(phototmep15)&&!TextUtils.isEmpty(content)){
                        showProgressDialog("正在提交");
                        uploadPic(14,phototmep14);
                        uploadPic(15,phototmep15);
                    }else {
                        if (shiti11 == -1){
                            showToast("请选择违规原因");
                            return;
                        }
                        if (TextUtils.isEmpty(shitidianming)){
                            showToast("请填写实体店名");
                            return;
                        }
                        if (TextUtils.isEmpty(shitihao)){
                            showToast("请填写购买的手机号码");
                            return;
                        }
                        if (TextUtils.isEmpty(shijian)){
                            showToast("请选择购卡时间");
                            return;
                        }
                        if (shiti12 == -1){
                            showToast("请选择运营商");
                            return;
                        }
                        if (shiti13 == -1){
                            showToast("请选择区县");
                            return;
                        }
                        if (TextUtils.isEmpty(etShitidianDizhixiangxi.getText().toString().trim())){
                            showToast("请填写详细地址");
                            return;
                        }
                        if (TextUtils.isEmpty(phototmep14)){
                            showToast("请选择实体店照片");
                            return;
                        }
                        if (TextUtils.isEmpty(phototmep15)){
                            showToast("请选择本人持卡照片");
                            return;
                        }
                        if (TextUtils.isEmpty(content)){
                            showToast("请填写举报描述");
                            return;
                        }

                    }
                }else {
                    String wangdianming = etWagndianName.getText().toString().trim();
                    String wangdianhao = etWangdianHao.getText().toString().trim();
                    String wangzhi = etWangdianWangzhi.getText().toString().trim();
                    String shijian = wangdian_goukashijian_tv.getText().toString().trim();
                    String xiaoshoudizhi = etWangdianXiaoshoudizhi.getText().toString().trim();
                    String content = etWangdianMiaoshu.getText().toString().trim();
                    if (wangdian21 != -1&&wangdian22!=-1&&!TextUtils.isEmpty(wangdianming)&&!TextUtils.isEmpty(wangdianhao)&&!TextUtils.isEmpty(wangzhi)&&!TextUtils.isEmpty(xiaoshoudizhi)&&!TextUtils.isEmpty(content)&&!TextUtils.isEmpty(shijian)){
                        showProgressDialog("正在提交");
                        uploadPic(23,phototmep23);
                        uploadPic(24,phototmep24);
                    }else {
                        if (wangdian21 == -1){
                            showToast("请选择违规原因");
                            return;
                        }
                        if (TextUtils.isEmpty(wangdianming)){
                            showToast("请填写网店名称");
                            return;
                        }
                        if (TextUtils.isEmpty(wangdianhao)){
                            showToast("请填写购买的手机号码");
                            return;
                        }
                        if (TextUtils.isEmpty(shijian)){
                            showToast("请选择购卡时间");
                            return;
                        }
                        if (wangdian22 == -1){
                            showToast("请选择运营商");
                            return;
                        }
                        if (TextUtils.isEmpty(wangzhi)){
                            showToast("请填写网店网址");
                            return;
                        }if (TextUtils.isEmpty(xiaoshoudizhi)){
                            showToast("请填写销售页面地址");
                            return;
                        }
                        if (TextUtils.isEmpty(phototmep23)){
                            showToast("请选择订单截图");
                            return;
                        }
                        if (TextUtils.isEmpty(phototmep24)){
                            showToast("请选择本人持卡照片");
                            return;
                        }
                        if (TextUtils.isEmpty(content)){
                            showToast("请填写举报描述");
                            return;
                        }
                    }
                }
                break;
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    reportshiti();
                    break;
                case 2:
                    reportwangdian();
                    break;
            }
        }
    };

    public void reportshiti(){
        String shitidianming = etShitidianName.getText().toString().trim();
        String shitihao = etShitidianHao.getText().toString().trim();
        String shijian = shiti_goukashijian_tv.getText().toString().trim();
        String address = shiti_quxian_tv.getText().toString().trim()+etShitidianDizhixiangxi.getText().toString().trim();
        String url1 = (String) shiti_zhaopian.getTag();
        String url2 = (String) shiti_benrenzhaopian.getTag();
        String content = etShitidianMiaoshu.getText().toString().trim();


        RequestParams params = Utils.getDefaultParams("App/reportRealName");
        params.addBodyParameter("user_token", Utils.getUserToken(this));
        params.addBodyParameter("name",shitidianming);
        params.addBodyParameter("buy_mobile",shitihao);
        params.addBodyParameter("report_type",lay1check==1?"实体店":"网店");
        params.addBodyParameter("buy_time",shijian);
        params.addBodyParameter("operator",new Constant().getYunyingshang().get(shiti12));
        params.addBodyParameter("address",address);
        params.addBodyParameter("img",url1);
        params.addBodyParameter("card_img",url2);
        params.addBodyParameter("reason",new Constant().getWeiguishiti().get(shiti11));

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
                closeProgressDialog();
            }
        });
    }

    public void reportwangdian(){
        String wangdianming = etWagndianName.getText().toString().trim();
        String wangdianhao = etWangdianHao.getText().toString().trim();
        String wangzhi = etWangdianWangzhi.getText().toString().trim();
        String shijian = wangdian_goukashijian_tv.getText().toString().trim();
        String xiaoshoudizhi = etWangdianXiaoshoudizhi.getText().toString().trim();
        String content = etWangdianMiaoshu.getText().toString().trim();
        String url1 = (String) wangdian_dingdanzhaopian.getTag();
        String url2 = (String) wangdian_benrenzhaopian.getTag();


        RequestParams params = Utils.getDefaultParams("App/reportRealName");
        params.addBodyParameter("user_token", Utils.getUserToken(this));
        params.addBodyParameter("name",wangdianming);
        params.addBodyParameter("buy_mobile",wangdianhao);
        params.addBodyParameter("report_type",lay1check==1?"实体店":"网店");
        params.addBodyParameter("buy_time",shijian);
        params.addBodyParameter("operator",new Constant().getYunyingshang().get(wangdian22));
        params.addBodyParameter("store_websit",wangzhi);
        params.addBodyParameter("store_sale_website",xiaoshoudizhi);
        params.addBodyParameter("img",url1);
        params.addBodyParameter("card_img",url2);
        params.addBodyParameter("reason",new Constant().getWeiguiwangdian().get(wangdian21));

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
                closeProgressDialog();
            }
        });
    }

    private int shiti11 =  -1;//实体违规选择结果
    private int shiti12 =  -1;//运营商选择结果
    private int shiti13 =  -1;//实体区县选择结果

    private int wangdian21 = -1;//网店违规原因
    private int wangdian22 = -1;//网店运营商
    private Bitmap bitmap;
    private String phototmep14 ="";
    private String phototmep15 ="";
    private String phototmep23 ="";
    private String phototmep24 ="";
    private boolean uppic1 = false;
    private boolean uppic2 = false;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            switch (requestCode){
                case 11:
                    shitiweigui_yuanyin_tv.setText(data.getStringExtra("address"));
                    shiti11 = data.getIntExtra("position",-1);
                    break;
                case 12:
                    shiti_yunyingshang_tv.setText(data.getStringExtra("address"));
                    shiti12 = data.getIntExtra("position",-1);
                    break;
                case 13:
                    shiti_quxian_tv.setText(data.getStringExtra("address"));
                    shiti13 = data.getIntExtra("position",-1);
                    break;
                case 21:
                    wangdian_weigui_tv.setText(data.getStringExtra("address"));
                    wangdian21 = data.getIntExtra("position",-1);
                    break;
                case 22:
                    wangdian_yunyingshang_tv.setText(data.getStringExtra("address"));
                    wangdian22 = data.getIntExtra("position",-1);
                    break;
            }
        }

        if (resultCode == RESULT_OK){
            if (data != null) {
                Uri mImageCaptureUri = data.getData();
                if (mImageCaptureUri != null) {
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageCaptureUri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        bitmap = extras.getParcelable("data");
                    }
                }

                switch (requestCode){
                    case 14:
                        phototmep14 = savePicToSdcard(bitmap,phototmep14,Environment.getExternalStorageDirectory().getAbsolutePath()+"/phototemp14"+ System.currentTimeMillis()+".jpg");
                        x.image().bind(shiti_zhaopian,"file://"+phototmep14);
                        break;
                    case 15:
                        phototmep15 = savePicToSdcard(bitmap,phototmep15,Environment.getExternalStorageDirectory().getAbsolutePath()+"/phototemp15"+ System.currentTimeMillis()+".jpg");
                        x.image().bind(shiti_benrenzhaopian,"file://"+phototmep15);
                        break;
                    case 23:
                        phototmep23 = savePicToSdcard(bitmap,phototmep23,Environment.getExternalStorageDirectory().getAbsolutePath()+"/phototemp23"+ System.currentTimeMillis()+".jpg");
                        x.image().bind(wangdian_dingdanzhaopian,"file://"+phototmep23);
                        break;
                    case 24:
                        phototmep24 = savePicToSdcard(bitmap,phototmep24,Environment.getExternalStorageDirectory().getAbsolutePath()+"/phototemp24"+ System.currentTimeMillis()+".jpg");
                        x.image().bind(wangdian_benrenzhaopian,"file://"+phototmep24);
                        break;
                }
                if(bitmap !=null && !bitmap.isRecycled()){
                    bitmap.recycle();
                }
            }
        }
    }

    public String savePicToSdcard(Bitmap bitmap,String lastname , String nowname) {
        String filePath = "";
        File lastfile = new File(lastname);
        if (lastfile.exists())
            lastfile.delete();
        File destFile = new File(nowname);
        if (destFile.exists())
            destFile.delete();
        OutputStream os = null;
        try {
            os = new FileOutputStream(destFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, os);
            os.flush();
            os.close();
            filePath = destFile.getAbsolutePath();
        } catch (IOException e) {
            filePath = "";
        }
        return filePath;
    }

    public void showSelect(final int requestcode){
        boolean issd = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (!issd){
            showToast("SD卡不存在");
            return;
        }
        if (ActivityCompat.checkSelfPermission(PhoneVerifyActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PhoneVerifyActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},(800+requestcode));
        }else {
            showSelectDialog(requestcode);
        }

    }

    private void showSelectDialog(final int requestcode){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(new String[]{"拍照", "从相册选择"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    try {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, requestcode);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else {
                    try {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        startActivityForResult(intent, requestcode);
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        builder.create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode >=800&&requestCode<900 && grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            showSelectDialog(requestCode-800);
        }else {
            showToast("请同意权限后继续");
        }
        }

    public void uploadPic(final int whitch, String path){
        File file = new File(path);
        if (!file.exists()){
            closeProgressDialog();
            showToast("文件不存在");
            return;
        }
        RequestParams params = Utils.getDefaultParams("App/uploadPic");
        params.setMultipart(true);
        params.addBodyParameter("user_token", Utils.getUserToken(this));
        params.addBodyParameter("file",file);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                FileResultBean bean = Utils.jsonParase(result,FileResultBean.class);
                if (bean != null && bean.getCode() == 200){
                    switch (whitch){
                        case 14:
                            shiti_zhaopian.setTag(bean.getData().getFile_path());
                            uppic1 = true;
                            if (uppic1&&uppic2){
                                handler.sendEmptyMessage(1);
                                uppic1 = false;
                                uppic2 = false;
                            }
                            break;
                        case 15:
                            shiti_benrenzhaopian.setTag(bean.getData().getFile_path());
                            uppic2 = true;
                            if (uppic1&&uppic2){
                                handler.sendEmptyMessage(1);
                                uppic1 = false;
                                uppic2 = false;
                            }
                            break;
                        case 23:
                            wangdian_dingdanzhaopian.setTag(bean.getData().getFile_path());
                            uppic1 = true;
                            if (uppic1&&uppic2){
                                handler.sendEmptyMessage(2);
                                uppic1 = false;
                                uppic2 = false;
                            }
                            break;
                        case 24:
                            wangdian_benrenzhaopian.setTag(bean.getData().getFile_path());
                            uppic2 = true;
                            if (uppic1&&uppic2){
                                handler.sendEmptyMessage(2);
                                uppic1 = false;
                                uppic2 = false;
                            }
                            break;
                    }

                }else {
                    if (whitch == 14 || whitch == 15){
                        if (uppic1 == false && whitch == 14){
                            showToast("请重试");
                        }else if (uppic1 == true&&uppic2==false){
                            showToast("请重试");
                        }
                    }else if (whitch == 23|| whitch == 24){
                        if (uppic1 == false && whitch == 23){
                            showToast("请重试");
                        }else if (uppic1 == true&&uppic2==false){
                            showToast("请重试");
                        }
                    }
                    closeProgressDialog();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (whitch == 14 || whitch == 15){
                    if (uppic1 == false && whitch == 14){
                        showToast("请重试");
                    }else if (uppic1 == true&&uppic2==false){
                        showToast("请重试");
                    }
                }else if (whitch == 23|| whitch == 24){
                    if (uppic1 == false && whitch == 23){
                        showToast("请重试");
                    }else if (uppic1 == true&&uppic2==false){
                        showToast("请重试");
                    }
                }
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

}
