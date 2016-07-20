package com.hebe.report.utils;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.hebe.report.Constant.Constant;

import org.xutils.http.RequestParams;

/**
 * Created by Hebe on 7-18-018.
 */

public class Utils {

    public static <T> T jsonParase(String json,Class<T> clazz) {
        try {
            return JSONObject.parseObject(json,clazz);
        }catch (Exception e){
            return null;
        }
    }

    public static RequestParams getDefaultParams(String addUrl){
        return new RequestParams(Constant.base_url+addUrl);
    }
    public static String getUserToken(Context context){
        return ToolsSp.get(context, Constant.SP_NAME,"utoken");
    }

}
