package com.example.mounika.hitam_try.BusTracking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mounika.hitam_try.R;

public class BusActivity extends AppCompatActivity {

    private int busRouteNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
    }

    public void setBusRouteNumber(int busRouteNumber) {
        this.busRouteNumber = busRouteNumber;
    }
}
