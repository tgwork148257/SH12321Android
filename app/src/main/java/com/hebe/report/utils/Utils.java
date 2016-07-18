package com.hebe.report.utils;

import com.alibaba.fastjson.JSONObject;

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

}
