package com.vincent.myapp.huawei.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Copyright (C), 2018-2019, 重庆咕点科技有限公司
 * FileName: TestView
 * Author: Vincent
 * Date: 2019/8/15 9:02
 * Description: 描述
 * History:
 */
public class TestView extends ViewGroup {

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private Context mContext;
    private void init(Context context){
        this.mContext = context;
    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
