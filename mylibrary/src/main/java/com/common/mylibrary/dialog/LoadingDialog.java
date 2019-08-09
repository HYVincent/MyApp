package com.common.mylibrary.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.common.mylibrary.R;
import com.common.mylibrary.view.BaseDialog;


/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name CustomDialog
 * @page com.vincent.dialog
 * @class describe
 * @date 2017/12/26 9:51
 */


public class LoadingDialog extends BaseDialog {

    private ProgressBar progressBar;
    private TextView tvMsg;
    private String strMsg;


    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.MyDialogBgIsWhite);
    }

    public LoadingDialog(Context context,String msg){
        super(context, R.style.MyDialogBgIsWhite);
        this.strMsg = msg;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_layout_loading);
        //下面的参数设置也可以在别的地方设置
        setOutCancel(false);
        setWidth(-1);
        setDimAmount(0);
        initWidget();
    }

    public void setStrMsg(String strMsg) {
        this.strMsg = strMsg;
    }

    public void initWidget() {
        progressBar = findViewById(R.id.library_loading_progressBar);
        tvMsg = findViewById(R.id.library_loading_text);
        if(TextUtils.isEmpty(strMsg)){
            tvMsg.setVisibility(View.GONE);
        }else {
            tvMsg.setText(strMsg);
            tvMsg.setVisibility(View.VISIBLE);
        }
    }
}
