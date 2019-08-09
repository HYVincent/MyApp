package com.common.mylibrary.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.common.mylibrary.R;
import com.common.mylibrary.view.BaseDialog;

/**
 * @author win 10 Vincent
 * @version v1.0
 * @name SmartNecklace
 * @page com.common.mylibrary.dialog
 * @class describe
 * @date 2018/6/29 9:27
 */
public class ImgSelectDialog extends BaseDialog {

    private TextView tvAlbum,tvPhotograph,tvCancel;
    private OnSelectClickListener onSelectClickListener;

    public ImgSelectDialog setOnSelectClickListener(OnSelectClickListener onSelectClickListener) {
        this.onSelectClickListener = onSelectClickListener;
        return this;
    }

    public ImgSelectDialog(@NonNull Context context) {
        super(context);
        setShowBottom(true);
        setOutCancel(true);
        setMargin(10);//设置左右两边的间距
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.library_layout_img_select_dialog);
        tvAlbum = findViewById(R.id.library_img_for_album);
        tvPhotograph = findViewById(R.id.library_img_for_photograph);
        tvCancel = findViewById(R.id.library_img_select_cancel);
        tvAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onSelectClickListener != null){
                    onSelectClickListener.onAlbumClick();
                }
                dismiss();
            }
        });
        tvPhotograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onSelectClickListener != null){
                    onSelectClickListener.onPhotograph();
                }
                dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        setAnimStyle(R.style.ActionSheetDialogAnimation);
    }

    public interface OnSelectClickListener{
        //相册
        void onAlbumClick();
        //拍照
        void onPhotograph();
    }

}
