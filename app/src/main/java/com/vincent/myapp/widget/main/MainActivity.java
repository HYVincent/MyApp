package com.vincent.myapp.widget.main;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.github.penfeizhou.animation.apng.APNGDrawable;
import com.github.penfeizhou.animation.loader.FileLoader;
import com.github.penfeizhou.animation.loader.Loader;
import com.vincent.myapp.R;
import com.vincent.myapp.base.BaseActivity;
import com.vincent.myapp.view.MusicPlayStatusView;

public class MainActivity extends BaseActivity {

    private ImageView imageView;
    private AnimationDrawable animationDrawable;
    private MusicPlayStatusView musicPlayStatusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image_view);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.music_play));


        musicPlayStatusView = findViewById(R.id.mpsv);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                musicPlayStatusView.setPlay(true);
                imageView.setImageResource(R.drawable.music_play);
                animationDrawable = (AnimationDrawable) imageView.getDrawable();
                animationDrawable.start();
            }
        });
        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                musicPlayStatusView.setPlay(false);
                animationDrawable.stop();
                imageView.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.music_play_stop));
            }
        });
        FileLoader fileLoader = new FileLoader("assets/test.png");
        APNGDrawable apngDrawable = new APNGDrawable(fileLoader);
        ImageView imageViewPng = findViewById(R.id.image_png);
        imageViewPng.setImageDrawable(apngDrawable);
    }
}
