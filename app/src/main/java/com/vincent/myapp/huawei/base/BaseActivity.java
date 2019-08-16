package com.vincent.myapp.huawei.base;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.common.mylibrary.dialog.LoadingDialog;
import com.common.mylibrary.permission.MyPermissionListener;
import com.common.mylibrary.permission.PermissionHelper;
import com.common.mylibrary.util.ActivityUtils;
import com.common.mylibrary.util.MyToastUtil;
import com.vincent.myapp.huawei.R;

/**
 * Copyright (C), 2018-2019, 重庆咕点科技有限公司
 * FileName: BaseActivity
 * Author: Vincent
 * Date: 2019/8/9 9:04
 * Description: 描述
 * History:
 */
public class BaseActivity extends AppCompatActivity implements PermissionHelper.PermissionListener {

    private PermissionHelper permissionHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.addActivity(this);
        initPermission();
    }

    private void initPermission() {
        permissionHelper = new PermissionHelper(this);
    }
    private MyPermissionListener myPermissionListener;
    public void checkNeedPermission(String hintPermissionMsg,MyPermissionListener myPermissionListener,String...permission){
        this.myPermissionListener = myPermissionListener;
        if(PermissionHelper.hasPermissions(this,permission)){
            myPermissionListener.action();
        }else {
            permissionHelper.requestPermissions(hintPermissionMsg,this,permission);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionHelper.handleRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    public void doAfterGrand(String... permission) {
        myPermissionListener.doAfterGrand(permission);
    }

    @Override
    public void doAfterDenied(String... permission) {
        myPermissionListener.doAfterDenied(permission);
    }

    /**
     * 修改系统状态栏字体的颜色
     * @param isBlack
     */
    public void setSystemStatusTextColor(boolean isBlack){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (isBlack) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
            }else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
            }
        }
    }

    /**
     * 注意此方法需要在setContentView之前调用
     * @param colorId
     */
    public void setSystemStatusColor(int colorId){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            //兼容4.4
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
            View statusBarView = new View(window.getContext());
            int statusBarHeight = getStatusBarHeight();
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
            params.gravity = Gravity.TOP;
            statusBarView.setLayoutParams(params);
            statusBarView.setBackgroundColor(ContextCompat.getColor(BaseActivity.this, colorId));
            decorViewGroup.addView(statusBarView);
        }else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(BaseActivity.this, colorId));
        }
    }


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 弹出toast消息
     * @param msg
     */
    public void toastMsg(String msg){
        if(TextUtils.isEmpty(msg)){
            return;
        }
        MyToastUtil.showMsg(msg);
    }

    private LoadingDialog loadingDialog;

    public void showWaitDialog(){
        loadingDialog = new LoadingDialog(BaseActivity.this);
        loadingDialog.show();
    }

    public void showWaitDialog(String msg){
        if(TextUtils.isEmpty(msg)){
            msg = getString(R.string.loading_dialog_request);
        }
        if(loadingDialog != null) {
            loadingDialog.setStrMsg(msg);
        }else {
            loadingDialog = new LoadingDialog(BaseActivity.this, msg);
        }
        loadingDialog.show();
    }

    public void cancelWaitDialog(){
        if(loadingDialog != null && loadingDialog.isShowing()){
            loadingDialog.dismiss();
            loadingDialog.cancel();
            loadingDialog = null;
        }
    }


  /*  private HintMsgDialog hintMsgDialog ;

    public void showHintMsgDialog(String title,String leftMsg,String rightMsg,final HintMsgDialog.HintMsgDialogClickListener HintMsgDialogClickListener) {
        hintMsgDialog = new HintMsgDialog(BaseActivity.this, title,leftMsg,rightMsg, new HintMsgDialog.HintMsgDialogClickListener() {
            @Override
            public void onLeftClick() {
                HintMsgDialogClickListener.onLeftClick();
            }

            @Override
            public void onRightClick() {
                HintMsgDialogClickListener.onRightClick();
            }
        });
        if(!hintMsgDialog.isShowing()){
            hintMsgDialog.show();
        }
    }*/

    private Handler handler = new Handler();

    /**
     * 延迟一段时间执行某个操作
     * @param time      time
     * @param listener  listener
     */
    public void delayedTimeAction(long time, final ActionListener listener){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onAction();
            }
        },time);
    }



    public interface ActionListener{

        void onAction();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.removeActivity(this);
    }
}
