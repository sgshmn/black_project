package com.example.naga0818;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class intro extends AppCompatActivity {

    TextView tv_na, tv_ga, tv_nol, tv_ja;
    ImageView img_intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        tv_na = findViewById(R.id.tv_na);
        tv_ga = findViewById(R.id.tv_ga);
        tv_nol = findViewById(R.id.tv_nol);
        tv_ja = findViewById(R.id.tv_ja);
        img_intro = findViewById(R.id.lottie);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        tv_na.startAnimation(animation);
        tv_ga.startAnimation(animation);
        tv_nol.startAnimation(animation);
        tv_ja.startAnimation(animation);

        img_intro.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha_intro_anim));


        LottieAnimationView animationView = findViewById(R.id.lottie);

        animationView.setSpeed(1f);
        animationView.setVisibility(View.VISIBLE);
        animationView.playAnimation();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        }, 4000);

    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
    }
