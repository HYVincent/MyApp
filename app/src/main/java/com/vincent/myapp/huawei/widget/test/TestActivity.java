package com.vincent.myapp.huawei.widget.test;

import android.Manifest;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.common.mylibrary.permission.MyPermissionListener;
import com.common.mylibrary.permission.PermissionHelper;
import com.vincent.myapp.huawei.R;
import com.vincent.myapp.huawei.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright (C), 2018-2019, 重庆咕点科技有限公司
 * FileName: TestActivity
 * Author: Vincent
 * Date: 2019/8/16 8:02
 * Description: 描述
 * History:
 */
public class TestActivity extends BaseActivity implements PermissionHelper.PermissionListener{


    @BindView(R.id.test_fl_content)
    FrameLayout testFlContent;

    private Fragment testFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
//        permissionHelper = new PermissionHelper(TestActivity.this);
        initFragment();
    }

    private void initFragment() {
        testFragment = new TestFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.test_fl_content, testFragment, TestFragment.class.getSimpleName());
        transaction.show(testFragment);
        transaction.commit();
    }
    /*private PermissionHelper permissionHelper;
    private MyPermissionListener permissionListener;
    public void checkPermission(MyPermissionListener permissionListener) {
        this.permissionListener = permissionListener;
        if (PermissionHelper.hasPermissions(TestActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permissionListener.action();
        } else {
            permissionHelper.requestPermissions(
                    "需要获取外部写入存储权限",this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionHelper.handleRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void doAfterGrand(String... permission) {
        permissionListener.doAfterGrand(permission);
    }

    @Override
    public void doAfterDenied(String... permission) {
        permissionListener.doAfterDenied(permission);
    }*/
}
