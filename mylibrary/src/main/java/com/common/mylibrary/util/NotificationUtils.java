package com.common.mylibrary.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;

import com.common.mylibrary.R;

/**
 * Copyright (C), 2018-2019, 重庆咕点科技有限公司
 * FileName: NotificationUtils
 * Author: Vincent
 * Date: 2019/8/14 14:01
 * Description: 描述
 * History:
 */
public class NotificationUtils {

    private static final String TAG = "通知";

    private static final int NOTIFY_ID = 100;
    private static NotificationManager mNotifyManager;
    private static Notification.Builder mBuilder;
    private static NotificationChannel channel;

    /**
     * 这是显示下载进度的notification
     *
     * @param mContext     mContext
     * @param smallIcon    显示的小图标
     * @param tagIntent    目标Intent 点击之后跳转的Intent
     * @param contentText  内容文本
     * @param progress     进度
     * @param num          总的进度
     * @param titleContent 标题内容
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void notifyDownloadingProgress(Context mContext, int smallIcon, Intent tagIntent, String contentText, long progress, long num, String titleContent) {
        mNotifyManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mBuilder = new Notification.Builder(mContext, "com.common.mylibrary.util");
            channel = new NotificationChannel("com.common.mylibrary.util", "com.common.mylibrary", NotificationManager.IMPORTANCE_LOW);
            if (mNotifyManager != null) mNotifyManager.createNotificationChannel(channel);
        } else {
            mBuilder = new Notification.Builder(mContext);
        }
        mBuilder.setSmallIcon(smallIcon);
        mBuilder.setProgress((int) num, (int) progress, false);
        mBuilder.setOngoing(true);
        mBuilder.setWhen(System.currentTimeMillis());
        mBuilder.setContentTitle(titleContent);
        mBuilder.setContentText(contentText);
        PendingIntent pendIntent = PendingIntent.getActivity(
                mContext, NOTIFY_ID, tagIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendIntent);
        if (mNotifyManager != null) mNotifyManager.notify(NOTIFY_ID, mBuilder.build());
    }

    /**
     * 自定义通知栏
     *
     * @param mContext     mContext
     * @param smallIcon    小图标
     * @param tagIntent    目标Activity
     * @param contentText  内容
     * @param titleContent 标题
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void commonTextNotification(Context mContext, int smallIcon, Intent tagIntent, String contentText, String titleContent) {
        mNotifyManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mBuilder = new Notification.Builder(mContext, "com.common.mylibrary.util");
            channel = new NotificationChannel("com.common.mylibrary.util", "com.common.mylibrary", NotificationManager.IMPORTANCE_LOW);
            if (mNotifyManager != null) mNotifyManager.createNotificationChannel(channel);
        } else {
            mBuilder = new Notification.Builder(mContext);
        }
        mBuilder.setSmallIcon(smallIcon);
        mBuilder.setContentTitle(titleContent);
        mBuilder.setContentText(contentText);
        mBuilder.setAutoCancel(true);//点击之后消失
        PendingIntent pendIntent = PendingIntent.getActivity(
                mContext, NOTIFY_ID, tagIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendIntent);
        if (mNotifyManager != null)
            mNotifyManager.notify((int) System.currentTimeMillis(), mBuilder.build());
    }

    /**
     * 自定义View通知栏
     *
     * @param mContext
     * @param smallIcon
     * @param tagIntent
     * @param contentText
     * @param titleContent
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void customNotificationLayout(Context mContext, int smallIcon, Intent tagIntent, String contentText, String titleContent) {
        mNotifyManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mBuilder = new Notification.Builder(mContext, "com.common.mylibrary.util");
            channel = new NotificationChannel("com.common.mylibrary.util", "com.common.mylibrary", NotificationManager.IMPORTANCE_LOW);
            if (mNotifyManager != null) mNotifyManager.createNotificationChannel(channel);
        } else {
            mBuilder = new Notification.Builder(mContext);
        }
        mBuilder.setSmallIcon(smallIcon);
//        mBuilder.setContentTitle(titleContent);
//        mBuilder.setContentText(contentText);
        /*RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.message);
        rv.setTextViewText(R.id.tv, "泡沫");//修改自定义View中的歌名
        //修改自定义View中的图片(两种方法)
        //        rv.setImageViewResource(R.id.iv,R.mipmap.ic_launcher);
        rv.setImageViewBitmap(R.id.iv, BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher));
        mBuilder.setContent(rv);*/
        mBuilder.setAutoCancel(true);//点击之后消失
        PendingIntent pendIntent = PendingIntent.getActivity(
                mContext, NOTIFY_ID, tagIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendIntent);
        if (mNotifyManager != null)
            mNotifyManager.notify((int) System.currentTimeMillis(), mBuilder.build());
    }


    /**
     * 取消通知
     *
     * @param notifyId
     */
    public static void cancelNotification(int notifyId) {
        if (mNotifyManager != null) mNotifyManager.cancel(notifyId);
    }


}
