package com.vincent.myapp.huawei.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.common.mylibrary.CommonUtil;
import com.common.mylibrary.util.MyLogUtils;
import com.vincent.myapp.huawei.base.AppConfig;

/**
 * Copyright (C), 2018-2019, 重庆咕点科技有限公司
 * FileName: MyApp
 * Author: Vincent
 * Date: 2019/8/9 8:59
 * Description: 描述
 * History:
 */
public class MyApp extends Application {
    private static final String tag = "MyApp";
    @Override
    public void onCreate() {
        super.onCreate();
        CommonUtil.getInstance().init(this, AppConfig.SHARED_NAME);
        registerActivityLifecycle();
    }
    private void registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
                MyLogUtils.d(tag,activity.getClass().getSimpleName() + "  创建");
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                MyLogUtils.d(tag,activity.getClass().getSimpleName() + "  开始");
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                MyLogUtils.d(tag,activity.getClass().getSimpleName() + "  显示");
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                MyLogUtils.d(tag,activity.getClass().getSimpleName() + "  暂停");
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                MyLogUtils.d(tag,activity.getClass().getSimpleName() + "  停止");
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
                MyLogUtils.d(tag,activity.getClass().getSimpleName() + "  onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                MyLogUtils.d(tag,activity.getClass().getSimpleName() + "  移除");
            }
        });
    }



}
