package com.hebe.report.base;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * @author Hebe
 */
public abstract class BaseFragment extends Fragment implements OnClickListener{

	public abstract void initViews(View view);
	
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
}
