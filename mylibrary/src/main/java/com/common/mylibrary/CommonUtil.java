package com.common.mylibrary;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.common.mylibrary.util.SPUtil;

/**
 * @author win 10 Vincent
 * @version v1.0
 * @name SmartNecklace
 * @page com.common.mylibrary
 * @class describe
 * @date 2018/6/22 15:17
 */
public class CommonUtil {

    private static CommonUtil instance;
    private SPUtil spUtil;
    //当前的activity
    private Activity mCurrentActivity;

    public static CommonUtil getInstance() {
        if(instance == null){
            instance = new CommonUtil();
        }
        return instance;
    }

    private Context mContext;

    /**
     * 这里的mContext是Application对象
     * @param mContext
     * @param spName
     */
    public void init(Context mContext,String spName){
        this.mContext = mContext;
        spUtil = SPUtil.getInstance(mContext,spName);
    }

    public SPUtil getSpUtil() {
        if(spUtil == null){
            throw new NullPointerException("Please call init method in your Application's onCreate method!");
        }
        return spUtil;
    }

    public Context getmContext() {
        return mContext;
    }





}
