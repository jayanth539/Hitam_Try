package com.example.mounika.hitam_try;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private ProgressBar mProgress;
    private Thread timer;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        ;
        final Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        iv.startAnimation(myanim);
        final Intent homeIntent = new Intent(MainActivity.this, Nav_Draw.class);

        mProgress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);


        mProgress.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
       timer = new Thread() {
            public void run() {

                doWork();

                startActivity(homeIntent); finish();
            }
        };

       timer.start();


    }

    private void doWork() {

            for(int i=0 ; i<100 ; i+= 10 ){
                i+= new Random().nextInt(10);
                try {
                    timer.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mProgress.setProgress(i);

            }
        }

}
