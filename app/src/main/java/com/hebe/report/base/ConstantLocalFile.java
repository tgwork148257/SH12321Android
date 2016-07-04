package com.hebe.report.base;

import android.os.Environment;


public final class ConstantLocalFile {
	
	public static final String CAR_SD_ROOT = Environment.getExternalStorageDirectory() + "/report/";
	public static final String CAR_MOUNT_ROOT = Environment.getDataDirectory() + "data/com.hebe.report/report/";
	
	public static final String CAR_SD_CACHE_ROOT = Environment.getExternalStorageDirectory() + "/report/cache";
	public static final String CAR_MOUNT_CACHE_ROOT = Environment.getDataDirectory() + "data/com.hebe.report/report/cache";
	

	public static final String SP_NAME = "userinfo";
	public static final String SP_USERINFO = "info";
	public static final String SP_USERID = "id";
	public static final String SP_PW = "pw";
}
