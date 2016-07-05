package com.hebe.report.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.widget.Toast;

/**
 * @author Hebe
 */
public abstract class BaseFragment extends Fragment{

	private DisplayMetrics displayMetrics = null;

	public DisplayMetrics getDisplayMetrics(){
		displayMetrics = getResources().getDisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics;
	}

	/**
	 * 显示自定义toast
	 * @param msg
	 */
	public void showToast(String msg){
		Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();;
	}
	/**
	 * 启动新的activity
	 * @param intent
	 */
	public void startNewActivity(Intent intent){
		startActivity(intent);
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
