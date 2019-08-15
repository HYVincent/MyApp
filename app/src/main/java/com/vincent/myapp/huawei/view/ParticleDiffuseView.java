package com.vincent.myapp.huawei.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;

import com.common.mylibrary.util.MyLogUtils;
import com.vincent.myapp.huawei.R;
import com.vincent.myapp.huawei.bean.Particle;

import java.util.List;

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

    public ParticleDiffuseView(Context context, int duration) {
        super(context);
        this.DURATION = duration;
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
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private int mViewWidth, mViewHeight;
    private Context mContext;
    private static final String TAG = "粒子扩散效果";
    private int[] location = new int[2];

    private ValueAnimator mParticleAnimator;
    //动画持续时间
    public int DURATION = 4000;
    //动画监听
    private OnAnimationListener mOnAnimationListener;

    public void setOnAnimationListener(OnAnimationListener mOnAnimationListener) {
        this.mOnAnimationListener = mOnAnimationListener;
    }

    //画笔
    private Paint mPaint;
    //所有粒子
    private List<Particle> mParticles;

    private void init(Context context) {
        this.mContext = context;
//        attachToActivity((Activity) getContext());
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制透明背景
        canvas.drawColor(ContextCompat.getColor(mContext, R.color.color_black_transparent_00000000));
        if (mParticleAnimator != null && mParticles != null && mParticles.size() > 0)
            drawParticle(canvas);
    }

    public void drawParticle(Canvas canvas) {
        for (Particle particle : mParticles) {
            //这个值从0.000f增到0.999
            Log.d(TAG, "drawParticle: 动画值" + (Float) mParticleAnimator.getAnimatedValue());
            particle.update((Float) mParticleAnimator.getAnimatedValue());
            mPaint.setColor(particle.color);
//                mPaint.setAlpha((int) (Color.alpha(p.color) * p.alpha));
            //直接设置为白色
            mPaint.setAlpha((int) (Color.alpha(Color.WHITE) * particle.alpha));
            canvas.drawCircle(particle.cx, particle.cy, particle.radius, mPaint);//
        }
    }

    private Rect rect;

    public void boom(final View view) {
        if (view.getVisibility() != View.VISIBLE || view.getAlpha() == 0 || (mParticleAnimator != null && mParticleAnimator.isRunning())) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                //获取了view的宽高
                view.getLocationInWindow(location);
                rect = new Rect(location[0], location[1], location[0] + view.getMeasuredWidth(), location[1] + view.getMeasuredHeight());
//                rect = new Rect(location[0], location[1], location[0] + getMeasuredWidth(), location[1] + getMeasuredHeight());
                mParticles = Particle.generateParticles(getCacheBitmapFromView(view), rect);
                mParticleAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
                mParticleAnimator.setDuration(DURATION);
                mParticleAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        if (mOnAnimationListener != null) {
                            mOnAnimationListener.onAnimationStart(view, animation);
                        } else {
                            MyLogUtils.e(TAG, "【method:onAnimationStart】-->please set mOnAnimationListener!!!");
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (mOnAnimationListener != null) {
                            mOnAnimationListener.onAnimationEnd(view, animation);
                        } else {
                            MyLogUtils.e(TAG, "【method:onAnimationEnd】-->please set mOnAnimationListener!!!");
                        }
                    }
                });
                mParticleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        invalidate();
                    }
                });
                mParticleAnimator.start();
            }
        });
    }

    private Bitmap drawingCache;
    private Bitmap bitmap;

    /**
     * 获取一个 View 的缓存视图
     *
     * @param view
     * @return
     */
    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        drawingCache = view.getDrawingCache();
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            bitmap = Bitmap.createScaledBitmap(bitmap, mViewWidth, mViewHeight, false);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }

    private void attachToActivity(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.addView(this, lp);
    }


    public interface OnAnimationListener {
        //当前正在执行的view
        void onAnimationStart(View v, Animator animation);

        void onAnimationEnd(View v, Animator animation);
    }
}
