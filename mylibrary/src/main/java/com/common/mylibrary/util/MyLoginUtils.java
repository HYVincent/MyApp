package com.common.mylibrary.util;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2019, 重庆咕点科技有限公司
 * FileName: MyLoginUtils
 * Author: Vincent
 * Date: 2019/8/9 9:04
 * Description: 描述
 * History:
 */
public class MyLoginUtils {

    private static final String TAG = "公共库";
    //true 显示log false 不显示
    private static boolean isShowLog = true;
    //日志分段数组
    private static List<String> logs = new ArrayList<>();
    //每段日志长度为100
    private static final int mItemLogLength = 150;

    public static void i(String logContent){
        i(TAG,logContent);
    }

    public static void i(String tag,String logContent){
        if(!isShowLog){
            return;
        }
        if(TextUtils.isEmpty(tag)){
            tag = TAG;
        }
        actionLogContent(logContent);
        for (int i = 0;i<logs.size();i++){
            Log.i(tag, logs.get(i));
        }
        logs.clear();
    }

    public static void v(String logContent){
        v(TAG,logContent);
    }

    public static void v(String tag,String logContent){
        if(!isShowLog){
            return;
        }
        if(TextUtils.isEmpty(tag)){
            tag = TAG;
        }
        actionLogContent(logContent);
        for (int i = 0;i<logs.size();i++){
            Log.v(tag, logs.get(i));
        }
        logs.clear();
    }

    public static void d(String logContent){
        d(TAG,logContent);
    }


    public static void d(String tag,String logContent){
        if(!isShowLog){
            return;
        }
        if(TextUtils.isEmpty(tag)){
            tag = TAG;
        }
        actionLogContent(logContent);
        for (int i = 0;i<logs.size();i++){
            Log.d(tag, logs.get(i));
        }
        logs.clear();
    }

    public static void w(String logContent){
        w(TAG,logContent);
    }

    public static void w(String tag,String logContent){
        if(!isShowLog){
            return;
        }
        if(TextUtils.isEmpty(tag)){
            tag = TAG;
        }
        actionLogContent(logContent);
        for (int i = 0;i<logs.size();i++){
            Log.w(tag, logs.get(i));
        }
        logs.clear();
    }

    public static void e(String logContent){
        e(TAG,logContent);
    }

    public static void e(String tag,String logContent){
        if(!isShowLog){
            return;
        }
        if(TextUtils.isEmpty(tag)){
            tag = TAG;
        }
        actionLogContent(logContent);
        for (int i = 0;i<logs.size();i++){
            Log.e(tag, logs.get(i));
        }
        logs.clear();
    }

    /**
     * 把日志分段处理
     * @param logContent
     */
    private static void actionLogContent(String logContent){
        logs.clear();
        //当前日志分页
        int page = logContent.length()/mItemLogLength;
        //当前日志取余
        int others = logContent.length() % mItemLogLength;
        if(page > 0){
            for (int i = 0;i<page;i++){
                logs.add("【"+ logContent.substring(i * mItemLogLength,mItemLogLength * (i+1)) +"】");
            }
        }
        if(others > 0){
            logs.add("【"+ logContent.substring(logContent.length() - others)+"】");
        }
    }
}
