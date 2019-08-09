package com.common.mylibrary.view;

import android.app.Activity;
import android.widget.ImageView;

/**
 * @author win 10 Vincent
 * @version v1.0
 * @name SmartNecklace
 * @page com.common.mylibrary.view
 * @class describe
 * @date 2018/6/25 15:18
 */

public class ImageUtil {
    /**
     * 获取图片的宽
     * @param activity
     * @param imageView
     * @return
     */
    public static int getImageViewWidth(Activity activity, ImageView imageView){
        float density =activity.getResources().getDisplayMetrics().density;
        // 通过getIntrinsic 获得ImageView中Image的真实宽高，
        int dw = (int) (imageView.getDrawable().getBounds().width()/density);
//        int dh = (int) (imageView.getDrawable().getBounds().height()/density);
        // 通过get获得ImageView中Image的真实宽高，
//        int inDw = (int) (imageView.getDrawable().getIntrinsicWidth()/density); //单位是dp
//        int inDh = (int) (imageView.getDrawable().getIntrinsicHeight()/density); //单位是dp
       return dw;
    }

    /**
     * 获取图片的高度
     * @param activity
     * @param imageView
     * @return
     */
    public static int getImageViewHeight(Activity activity, ImageView imageView){
        float density =activity.getResources().getDisplayMetrics().density;
        // 通过getIntrinsic 获得ImageView中Image的真实宽高，
//        int dw = (int) (imageView.getDrawable().getBounds().width()/density);
//        int dh = (int) (imageView.getDrawable().getBounds().height()/density);
        // 通过get获得ImageView中Image的真实宽高，
//        int inDw = (int) (imageView.getDrawable().getIntrinsicWidth()/density); //单位是dp
        int inDh = (int) (imageView.getDrawable().getIntrinsicHeight()/density); //单位是dp
        return inDh;
    }

}
