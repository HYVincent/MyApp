package com.vincent.myapp.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.vincent.myapp.R;

/**
 * Copyright (C), 2018-2019, 重庆咕点科技有限公司
 * FileName: MusicPlayStatusView
 * Author: Vincent
 * Date: 2019/8/9 11:41
 * Description: 描述
 * History:
 */
public class MusicPlayStatusView extends View {

    public MusicPlayStatusView(Context context) {
        super(context);
        init(context);
    }

    public MusicPlayStatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private Context mContext;
    /**
     * 图片资源
     */
    private int[] resId = new int[]{
            R.drawable.music_play_stop,
            R.drawable.music_play1,
            R.drawable.music_play2,
            R.drawable.music_play3,
            R.drawable.music_play4,
            R.drawable.music_play5,
            R.drawable.music_play6
    };
    private boolean isPlay = true;
    private Bitmap bitmap;
    private int mViewHeight;
    private int mViewWidth;
    private Paint mPaint;

    /**
     * true 播放 false 停止
     * @param play
     */
    public void setPlay(boolean play) {
        isPlay = play;
        index = 1;
        postInvalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mViewHeight = h;
        mViewWidth = w;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void init(Context context) {
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }
    private int index = 1;
    @Override
    protected void onDraw(Canvas canvas) {
        if(index > 6){
            index = 1;
        }
        if(!isPlay){
            //停止播放
            bitmap = BitmapFactory.decodeResource(getResources(),resId[0]);
            bitmap = resizeImage(bitmap,mViewWidth,mViewHeight);
            canvas.drawBitmap(bitmap,new Matrix(),mPaint);
        }else {
            //播放状态
            bitmap = BitmapFactory.decodeResource(getResources(),resId[index]);
            bitmap = resizeImage(bitmap,mViewWidth,mViewHeight);
            canvas.drawBitmap(bitmap,new Matrix(),mPaint);
        }
        if(isPlay){
            index ++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            postInvalidate();
        }
    }
    /**
     * Bitmap缩放
     * @param bitmap
     * @param w
     * @param h
     * @return
     */
    public Bitmap resizeImage(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float) w) / width;
        float scaleHeight = ((float) h) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        return Bitmap.createBitmap(bitmap, 0, 0, width,
                height, matrix, true);
    }




}
