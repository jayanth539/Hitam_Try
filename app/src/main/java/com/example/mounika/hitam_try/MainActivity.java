package com.example.mounika.hitam_try;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        long SPLASH_TIME_OUT=400;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run(){
                Intent homeIntent = new Intent(MainActivity.this,Nav_Draw.class);
                startActivity(homeIntent);
                finish();
            }

        },SPLASH_TIME_OUT);

    }
}
