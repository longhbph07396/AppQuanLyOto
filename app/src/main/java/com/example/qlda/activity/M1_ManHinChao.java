package com.example.qlda.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qlda.R;
import com.example.qlda.base.BaseActivity;

public class M1_ManHinChao extends BaseActivity {
    private ImageView imageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        startAnimation();
    }

    private void startAnimation() {
        makeAnimation(imageViewLogo, this, R.anim.m1).setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(M1_ManHinChao.this, M2_LoginActivity.class));
                overridePendingTransition(R.anim.in, R.anim.out);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void anhXa() {
        imageViewLogo = findViewById(R.id.imgvLogo);
    }
}
