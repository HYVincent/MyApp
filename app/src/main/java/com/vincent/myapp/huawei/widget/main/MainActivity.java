package com.vincent.myapp.huawei.widget.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.common.mylibrary.util.MyToastUtil;
import com.common.mylibrary.util.NotificationUtils;
import com.common.mylibrary.util.TimeCount;
import com.vincent.myapp.huawei.R;
import com.vincent.myapp.huawei.base.BaseActivity;
import com.vincent.myapp.huawei.widget.test.TestActivity;
import com.vincent.myapp.huawei.widget.welcome.WelcomeActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {
    private static final String TAG = "主页";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemStatusColor(R.color.color_white_f7f7f7);
        setSystemStatusTextColor(true);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, TestActivity.class));
        findViewById(R.id.btn_show_notification).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
               /*NotificationUtils.notifyDownloadingProgress(MainActivity.this,R.mipmap.ic_launcher,intent,
                       "我是通知啊",100,100,"通知标题");*/
                NotificationUtils.commonTextNotification(MainActivity.this, R.mipmap.ic_launcher, intent, "内容文本", "标题文本");
            }
        });
        findViewById(R.id.btn_cancel_notification).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                NotificationUtils.cancelNotification(100);
            }
        });
        findViewById(R.id.btn_start_time_count).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isStart){
                    toastMsg("正在计时..");
                }else {
                    isStart = true;
                    showTimer();
                }
            }
        });
    }

    private TimeCount timeCount;
    private boolean isStart = false;

    private void showTimer() {
        timeCount = new TimeCount(10, new TimeCount.TimeOnListener() {
            @Override
            public void finishAction() {
                isStart = false;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((Button)findViewById(R.id.btn_start_time_count)).setText("开启倒计时");
                        MyToastUtil.showMsg("超时!!!!");
                    }
                });
            }

            @Override
            public void everyAction(final int s) {
                Log.d(TAG, "everyAction: 正在连接..."+s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((Button)findViewById(R.id.btn_start_time_count)).setText("剩余"+s+"秒");
                    }
                });
            }
        });
        timeCount.start();
    }

}
