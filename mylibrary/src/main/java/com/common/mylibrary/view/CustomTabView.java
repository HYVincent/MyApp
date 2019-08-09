package com.common.mylibrary.view;

import android.content.Context;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.mylibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouwei on 17/4/25.
 */

public class CustomTabView extends LinearLayout implements View.OnClickListener{
    private List<View> mTabViews;
    private List<Tab> mTabs;
    private OnTabCheckListener mOnTabCheckListener;

    public void setOnTabCheckListener(OnTabCheckListener onTabCheckListener) {
        mOnTabCheckListener = onTabCheckListener;
    }

    public CustomTabView(Context context) {
        super(context);
        init();
    }

    public CustomTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomTabView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        mTabViews = new ArrayList<>();
        mTabs = new ArrayList<>();
    }


    /**
     * 添加Tab
     * @param tab
     */
    public void addTab(Tab tab){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.library_view_tab,null,false);
        TextView textView =  view.findViewById(R.id.custom_tab_text);
        ImageView imageView =  view.findViewById(R.id.custom_tab_icon);
//        LinearLayout llRoot = view.findViewById(R.id.custom_ll_root);
        imageView.setImageResource(tab.mIconNormalResId);
        textView.setText(tab.mText);
        textView.setTextColor(tab.mNormalColor);
//        llRoot.setBackgroundColor(ContextCompat.getColor(getContext(),tab.mBackgroundColor));
        view.setTag(mTabViews.size());
        view.setOnClickListener(this);

        mTabViews.add(view);
        mTabs.add(tab);

        addView(view);

    }

    /**
     * 这个方法是为了语言国际化刷新table文字使用的
     * @param datas
     */
   public void replaceTabText(List<String> datas){
       if(datas.size() != mTabs.size()){
           throw new RuntimeException("datas size is error........");
       }
        for (int i = 0;i<mTabs.size();i++){
            String title = datas.get(i);
            mTabs.get(i).setText(title);
            View view = mTabViews.get(i);
            TextView textView = view.findViewById(R.id.custom_tab_text);
            textView.setText(title);
        }
   }

    /**
     * 设置选中Tab
     * @param position
     */
    public void setCurrentItem(int position){
        if(position>=mTabs.size() || position<0){
            position = 0;
        }
         mTabViews.get(position).performClick();
        updateState(position);
    }

    /**
     * 更新状态
     * @param position
     */
    private void updateState(int position){
        for(int i= 0;i<mTabViews.size();i++){
            View view = mTabViews.get(i);
            TextView textView =  view.findViewById(R.id.custom_tab_text);
            ImageView imageView =  view.findViewById(R.id.custom_tab_icon);
            if(i == position){
                imageView.setImageResource(mTabs.get(i).mIconPressedResId);
                textView.setTextColor(mTabs.get(i).mSelectColor);
                if(mTabs.get(i).mSelectBackgroundColor != 0)
                    mTabViews.get(i).setBackgroundColor(mTabs.get(i).mSelectBackgroundColor);
            }else{
                imageView.setImageResource(mTabs.get(i).mIconNormalResId);
                textView.setTextColor(mTabs.get(i).mNormalColor);
                if(mTabs.get(i).mBackgroundColor != 0)
                    mTabViews.get(i).setBackgroundColor(mTabs.get(i).mBackgroundColor);
            }
        }
    }


    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if(mOnTabCheckListener!=null){
            mOnTabCheckListener.onTabSelected(v, position);
        }

        updateState(position);
    }

    public interface  OnTabCheckListener{
        public void onTabSelected(View v, int position);
    }


    public static class Tab{
        private int mIconNormalResId;
        private int mIconPressedResId;
        private int mNormalColor;
        private int mSelectColor;
        //正常情况的背景颜色
        private int mBackgroundColor = 0;
        //选中状态下的背景颜色
        private int mSelectBackgroundColor = 0;
        private String mText;

        public Tab setmSelectBackgroundColor(int mSelectBackgroundColor) {
            this.mSelectBackgroundColor = mSelectBackgroundColor;
            return this;
        }

        public Tab setmBackgroundColor(int mBackgroundColor) {
            this.mBackgroundColor = mBackgroundColor;
            return this;
        }

        public Tab setText(String text){
            mText = text;
            return this;
        }

        public Tab setNormalIcon(int res){
            mIconNormalResId = res;
            return this;
        }

        public Tab setPressedIcon(int res){
            mIconPressedResId = res;
            return this;
        }

        public Tab setColor(int color){
            mNormalColor = color;
            return this;
        }

        public Tab setCheckedColor(int color){
            mSelectColor = color;
            return this;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(mTabViews!=null){
            mTabViews.clear();
        }
        if(mTabs!=null){
            mTabs.clear();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        for(int i=0;i<mTabViews.size();i++){
            View view = mTabViews.get(i);
            int width = getResources().getDisplayMetrics().widthPixels / (mTabs.size());
            LayoutParams params = new LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(params);
        }

    }
}
