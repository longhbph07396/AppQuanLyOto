package com.example.qlda.base;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    public Animation makeAnimation(View view, Context context, int anim) {
        Animation animation = AnimationUtils.loadAnimation(context, anim);
        view.startAnimation(animation);
        return animation;
    }


    public void sendMess(Context context, String mess) {
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
    }
}
