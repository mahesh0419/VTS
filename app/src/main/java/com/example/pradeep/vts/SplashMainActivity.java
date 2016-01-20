package com.example.pradeep.vts;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by MAHESH on 12/30/2015.
 */
public class SplashMainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        textView = (TextView) findViewById(R.id.text);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                ImageView img = (ImageView) findViewById(R.id.imageView);
                img.setBackgroundResource(R.drawable.fade_in);

                // Get the background, which has been compiled to an AnimationDrawable object.
                AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();


                // Start the animation (looped playback by default).
                frameAnimation.start();
            }
        }, 500);

        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(5 * 1000);

                    // After 5 seconds redirect to another intent
                    Intent i = new Intent(SplashMainActivity.this, LoginActivity.class);
                    startActivity(i);

                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };
        background.start();


    }

}