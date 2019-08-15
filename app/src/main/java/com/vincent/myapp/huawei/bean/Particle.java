package com.vincent.myapp.huawei.bean;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Copyright (C), 2018-2019, 重庆咕点科技有限公司
 * FileName: Particle
 * Author: Vincent
 * Date: 2019/8/12 17:09
 * Description: 描述
 * History:
 */
public class Particle {

    //默认小球宽高
    public static final int PART_WH = 8 * 2;
    //x值
    public float cx;
    //y值
    public float cy;
    //绘制圆的半径
    public float radius;
    //颜色
    public int color;
    //透明度
    public float alpha;
    //用于生成随机数
    static Random random = new Random();
    //粒子所在的矩形区域
    public Rect mBound;
    private static int row;
    private static int column;
    public static Particle generateParticle(int color, Rect bound, Point point) {
        row = point.y; //行是高
        column = point.x; //列是宽

        Particle particle = new Particle();
        particle.mBound = bound;
        particle.color = color;
        particle.alpha = 1f;

        particle.radius = PART_WH;
        particle.cx = bound.left + PART_WH * column;
        particle.cy = bound.top + PART_WH * row;

        return particle;
    }

    /**
     * 更新视图
     * @param factor 动画值  这个值从0.0000f -> 0.999f
     */
    public void update(float factor) {
        if(cx < mBound.right/2.0f){
            //粒子在左边 x坐标应该递减
            cx = cx * (1 - factor);
            if(cy < mBound.bottom/2.0f){
                //粒子在左上边 y坐标应该递减
                cy= cy * (1 - factor);
            }else {
                //粒子在左下边 y坐标应该递增
                cy= cy * (1 + factor);
            }
        }else {
            cx = cx * (1 + factor);
            //粒子在右边 x坐标应该递增
            if(cy < mBound.bottom/2.0f){
                //粒子在右上边 y坐标递减
                cy= cy * (1 - factor);
            }else {
                //粒子在右下边 y坐标递增
                cy= cy * (1 + factor);
            }
        }
        /*cx = cx + factor * random.nextInt(mBound.width()) * (random.nextFloat() - 0.5f);
        cy = cy + factor * (mBound.height()*1.0f/(random.nextInt(4)+1)) ;*/
        radius = radius - factor * random.nextInt(3);
        if (radius<=0)
            radius = 0;
        alpha = 1f - factor;
    }
    //横向上粒子的个数 取整数个
    private static int partW_Count;
    //纵向上粒子的个数 取整
    private static int partH_Count;
    //视图的宽高
    private static int w;
    private static int h;
    //获取粒子
    private static List<Particle> particles = new ArrayList<>();
    private static Point point = null;
    /**
     * 获取所有的粒子 当然也可能是部分粒子
     * @param bitmap
     * @param bound
     * @return
     */
    public static List<Particle> generateParticles(Bitmap bitmap, Rect bound) {
        w = bound.width();
        h = bound.height();

        partW_Count = w / Particle.PART_WH * 2;
        partH_Count = h / Particle.PART_WH * 2;
        particles.clear();

        //以下方法取的时候全图的粒子
        /*for (int row = 0; row < partH_Count; row ++) { //行
            for (int column = 0; column < partW_Count; column ++) { //列
                //取得当前粒子所在位置的颜色
                int color = bitmap.getPixel(column * Particle.PART_WH, row * Particle.PART_WH);
                point = new Point(column, row); //x是列，y是行
                particles[row][column] = Particle.generateParticle(color, bound, point);
            }
        }*/
        for (int row = (int)(partH_Count * 0.25); row < (int)(partH_Count*0.75); row ++) { //行
            for (int column = (int)(partW_Count * 0.25); column <(int)(partW_Count * 0.75); column ++) { //列
                //取得当前粒子所在位置的颜色
//                int color = bitmap.getPixel(column * Particle.PART_WH, row * Particle.PART_WH);
                int color = Color.WHITE;
                point = new Point(column, row); //x是列，y是行
                particles.add(Particle.generateParticle(color, bound, point));
            }
        }
        return particles;
    }
}
