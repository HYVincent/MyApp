package com.vincent.myapp.huawei.base;

import android.text.TextUtils;

import androidx.fragment.app.Fragment;

import com.common.mylibrary.dialog.LoadingDialog;
import com.common.mylibrary.util.MyToastUtil;
import com.vincent.myapp.huawei.R;

/**
 * Copyright (C), 2018-2019, 南京宁康中科
 * FileName: Fragment
 * Author: Vincent
 * Date: 2019/7/12 16:58
 * Description: 描述
 * History:
 */
public class BaseFragment extends Fragment {

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
        if(getContext() != null) {
            loadingDialog = new LoadingDialog(getContext());
            loadingDialog.show();
        }
    }

    public void showWaitDialog(String msg){
        if(TextUtils.isEmpty(msg)){
            msg = getString(R.string.loading_dialog_request);
        }
        if(loadingDialog != null) {
            loadingDialog.setStrMsg(msg);
        }else {
            loadingDialog = new LoadingDialog(getContext(), msg);
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


}
