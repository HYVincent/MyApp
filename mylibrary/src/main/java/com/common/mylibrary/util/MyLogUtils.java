package com.common.mylibrary.util;

import android.util.Log;

/**
 * Copyright (C), 2018-2019, 重庆咕点科技有限公司
 * FileName: MyLogUtils
 * Author: Vincent
 * Date: 2019/8/6 11:49
 * Description: 描述
 * History:
 */
public class MyLogUtils {

    private static boolean isShowLog = true;

    /**
     * 显示日志
     * @param tag
     * @param log
     */
    public static void d(String tag,String log){
        if(isShowLog){
            Log.d(tag,log);
        }
    }
    public static void e(String tag,String log){
        if(isShowLog){
            Log.e(tag,log);
        }
    }

    public static void e(String tag,String log,Throwable throwable){
        if(isShowLog){
            Log.e(tag,log,throwable);
        }
    }

    public static void i(String tag,String log){
        if(isShowLog){
            Log.i(tag,log);
        }
    }

    public static void w(String tag,String log){
        if(isShowLog){
            Log.w(tag,log);
        }
    }
}
