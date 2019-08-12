package com.vincent.myapp.widget.main;

import android.os.Bundle;

import com.vincent.myapp.R;
import com.vincent.myapp.base.BaseActivity;
import com.vincent.myapp.view.ParticleDiffuseView;

public class MainActivity extends BaseActivity {
    private ParticleDiffuseView particleDiffuseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        particleDiffuseView = findViewById(R.id.pdv);
    }
}
