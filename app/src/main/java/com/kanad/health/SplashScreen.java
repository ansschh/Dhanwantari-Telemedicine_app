package com.kanad.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
    TextView app;
    LottieAnimationView animation_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        app = findViewById(R.id.app);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        animation_view = findViewById(R.id.animation_view);

        Thread thread = new Thread(){

            public void run(){
                try{
                    sleep(5000);

                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animationbro);
        app.setAnimation(animation);
        animation_view.setAnimation(animation);

    }
}