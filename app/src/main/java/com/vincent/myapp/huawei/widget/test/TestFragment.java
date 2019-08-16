package com.vincent.myapp.huawei.widget.test;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.common.mylibrary.permission.MyPermissionListener;
import com.common.mylibrary.util.MyLogUtils;
import com.common.mylibrary.permission.PermissionHelper;
import com.vincent.myapp.huawei.R;
import com.vincent.myapp.huawei.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Copyright (C), 2018-2019, 重庆咕点科技有限公司
 * FileName: TestFragment
 * Author: Vincent
 * Date: 2019/8/16 8:06
 * Description: 描述
 * History:
 */
public class TestFragment extends BaseFragment {
    @BindView(R.id.fragment_btn_request_permission)
    Button fragmentBtnRequestPermission;
    private View view;
    private static final String TAG = "TestFragment";
    private PermissionHelper permissionHelper;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) view = inflater.inflate(R.layout.fragment_layout_test, container, false);
        unbinder = ButterKnife.bind(this, view);
        permissionHelper = new PermissionHelper(getContext());
        return view;
    }

    @OnClick(R.id.fragment_btn_request_permission)
    public void onViewClicked() {
        TestActivity testActivity = (TestActivity) getActivity();
        if(testActivity ==null){
            toastMsg("testActivity is null!!!");
            return;
        }
        testActivity.checkNeedPermission("我就是要权限", new MyPermissionListener() {
            @Override
            public void action() {
                toastMsg("好了，可以干了");
            }

            @Override
            public void doAfterGrand(String... permission) {
                toastMsg("通过【"+permission[0]+"】");
            }

            @Override
            public void doAfterDenied(String... permission) {
                toastMsg("拒绝【"+permission[0]+"】");
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
