package com.vincent.myapp.huawei.widget.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.common.mylibrary.util.NotificationUtils;
import com.vincent.myapp.huawei.R;
import com.vincent.myapp.huawei.base.BaseActivity;
import com.vincent.myapp.huawei.widget.welcome.WelcomeActivity;

public class MainActivity extends BaseActivity {
    private static final String TAG = "主页";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemStatusColor(R.color.color_white_f7f7f7);
        setSystemStatusTextColor(true);
        setContentView(R.layout.activity_main);
       findViewById(R.id.btn_show_notification).setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.N)
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
               /*NotificationUtils.notifyDownloadingProgress(MainActivity.this,R.mipmap.ic_launcher,intent,
                       "我是通知啊",100,100,"通知标题");*/
               NotificationUtils.commonTextNotification(MainActivity.this,R.mipmap.ic_launcher,intent,"内容文本","标题文本");
           }
       });
        findViewById(R.id.btn_cancel_notification).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                NotificationUtils.cancelNotification(100);
            }
        });
    }

}
