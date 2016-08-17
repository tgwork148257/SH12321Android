package com.hebe.report.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * sp工具类
 * @author Hebe
 *
 */
public class ToolsSp {

	/**
	 * 保存或更新
	 * @param context
	 * @param spName sp文件名
	 * @param key 
	 * @param value
	 */
	public static void saveOrUpdate(Context context,String spName, String key, String value) {
		SharedPreferences sharedPreference = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		if (sharedPreference != null) {
			sharedPreference.edit().putString(key, value).commit();
		}
		sharedPreference = null;
		context = null;
	}
	
	/**
	 * 获取 没有返回null
	 * @param context
	 * @param spName sp文件名
	 * @param key 
	 */
	public static String get(Context context,String spName, String key) {
		SharedPreferences sharedPreference = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		if (sharedPreference != null) {
			String value = sharedPreference.getString(key, null);
			sharedPreference = null;
			context = null;
			return value;
		} else {
			context = null;
			return null;
		}
	}
	/**
	 * 删除
	 * @param context
	 * @param spName sp文件名
	 * @param key 
	 */
	public static void delete(Context context,String spName, String key) {
		SharedPreferences sharedPreference = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		if (sharedPreference != null) {
			sharedPreference.edit().remove(key).commit();
			sharedPreference = null;
			context = null;
		}
	}
	/**
	 * 清空
	 * @param context
	 * @param spName sp文件名
	 */
	public static void clean(Context context,String spName) {
		SharedPreferences sharedPreference = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		if (sharedPreference != null) {
			sharedPreference.edit().clear().commit();
			sharedPreference = null;
			context = null;
		}
	}
}
