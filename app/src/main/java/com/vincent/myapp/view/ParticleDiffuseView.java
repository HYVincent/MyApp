package com.vincent.myapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.vincent.myapp.bean.Particle;

/**
 * Copyright (C), 2018-2019, 重庆咕点科技有限公司
 * FileName: ParticleDiffuseView
 * Author: Vincent
 * Date: 2019/8/12 17:01
 * Description:                 参考:https://www.jianshu.com/p/1af53020719f
 * History:
 */
public class ParticleDiffuseView extends View {


    public ParticleDiffuseView(Context context) {
        super(context);
        init(context);
    }

    public ParticleDiffuseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mViewWidth = w;
        mViewHeight = h;
        getLocationInWindow(location);
        mViewRect = new Rect(location[0],location[1],location[0]+getMeasuredWidth(),location[1]+getMeasuredHeight());
        Log.d(TAG, "init: "+mViewRect.left + " "+mViewRect.right + " "+mViewRect.top + " "+mViewRect.bottom);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private Context mContext;
    private static final String TAG = "粒子扩散效果";
    private int mViewHeight;
    private int mViewWidth;
    private int[] location = new int[2];
    //整个View视图
    private Rect mViewRect = new Rect();
    private void init(Context context){
        this.mContext = context;
    }

    public static Particle[][] generateParticles(Bitmap bitmap, Rect bound) {
        int w = bound.width();
        int h = bound.height();

        int partW_Count = w / Particle.PART_WH;
        int partH_Count = h / Particle.PART_WH;

        Particle[][] particles = new Particle[partH_Count][partW_Count];
        Point point = null;
        for (int row = 0; row < partH_Count; row ++) { //行
            for (int column = 0; column < partW_Count; column ++) { //列
                //取得当前粒子所在位置的颜色
                int color = bitmap.getPixel(column * Particle.PART_WH, row * Particle.PART_WH);
                point = new Point(column, row); //x是列，y是行
                particles[row][column] = Particle.generateParticle(color, bound, point);
            }
        }
        return particles;
    }
}
