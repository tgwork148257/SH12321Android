package com.hebe.report.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hebe
 */
public abstract class BaseActivity extends FragmentActivity{

	private MyApplication application =null;
	private ProgressDialog mProgressDialog = null ;
	private DisplayMetrics displayMetrics = null;
	private int screenWidth = 0;
	private int screenHeight = 0;	
	private InputMethodManager im = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setCancelable(false);
			mProgressDialog.setCanceledOnTouchOutside(false);
			
			displayMetrics = getResources().getDisplayMetrics();
			this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
			this.screenWidth = displayMetrics.widthPixels;//获取屏幕像素
			this.screenHeight = displayMetrics.heightPixels;
			
			im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	}
	

	public MyApplication getApp(){
		if (application == null) {
			application = (MyApplication) getApplication();
		}
		return application;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (application != null) {
			application = null;
		}
	}



	public void showToast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();;
	}

	public void showProgressDialog(String msg){
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setCancelable(false);
			mProgressDialog.setCanceledOnTouchOutside(false);
		}
		if (!mProgressDialog.isShowing()) {
			mProgressDialog.setMessage(msg);
			mProgressDialog.show();
		}
	}

	public void closeProgressDialog(){
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
		}
	}
	
	
	public void showFragment(Fragment fragment,int rootView, String tag, boolean isReplace) {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		if (isReplace) {
			fragmentTransaction.replace(rootView, fragment, tag);
		} else {
			if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
				fragmentTransaction.add(rootView, fragment, tag);
			} else {
				return;
			}
		}
		fragmentTransaction.commitAllowingStateLoss();
	}

	public void removeFragment(Fragment fragment) {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commitAllowingStateLoss();
	}
	
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    } 

	public boolean isNetConnect(Context context){
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		if (info != null && info.isAvailable()) {
			return true;
		}else {
			return false;
		}
	}
	
	public DisplayMetrics getDisplayMetrics(){
		return displayMetrics;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}
	
	public void hideKeyBoard() {
		if (im.isActive()) {
			if (getCurrentFocus() != null && getCurrentFocus().getApplicationWindowToken() != null) {
				im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
	}

	public void startNewActivity(Intent intent){
		startActivity(intent);
	}

	public boolean isPhoneNumber(String phone){
		if (TextUtils.isEmpty(phone))
			return false;
		Pattern p = Pattern.compile("^(1\\d{10})$");
		Matcher m = p.matcher(phone);
		return m.matches();
	}
}
