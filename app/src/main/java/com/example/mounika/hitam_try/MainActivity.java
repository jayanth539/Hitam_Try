package com.example.mounika.hitam_try;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        ;
        final Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        iv.startAnimation(myanim);
        final Intent homeIntent = new Intent(MainActivity.this, Nav_Draw.class);
        final Thread timer = new Thread() {
            public void run() {

                try {

                    sleep(5000);


                } catch (Exception c) {
                    c.printStackTrace();

                } finally {
                    startActivity(homeIntent);
                    finish();
                }
            }
        };

       timer.start();



       /* long SPLASH_TIME_OUT=400;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run(){


                Intent homeIntent = new Intent(MainActivity.this,Nav_Draw.class);
                startActivity(homeIntent);
               finish();
            }

        },SPLASH_TIME_OUT);
       */


    }
}
