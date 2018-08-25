package com.example.mounika.hitam_try.BusTracking;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mounika.hitam_try.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class BusActivity extends AppCompatActivity implements View.OnClickListener{


    private int busRouteNumber;
    private FusedLocationProviderClient mFusedLocationClient;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("Locations");
        requestPermission();
        findViewById(R.id.updateButton).setOnClickListener(this);
    }

    public void setBusRouteNumber(int busRouteNumber) {
        this.busRouteNumber = busRouteNumber;
    }
    public int getBusRouteNumber() {
        return busRouteNumber;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.updateButton):
                updateLocation();

        }


    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
    private void updateLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            Toast.makeText(getBaseContext(),"Location Not Captured,Permission Denied",Toast.LENGTH_LONG).show();

            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object






                           BusObject object = new BusObject();//this is my own location object
                            object.setLongitude(String.valueOf(location.getLongitude()));//setting values
                            object.setLatitude(String.valueOf(location.getLatitude()));

                            //String id = String.valueOf(value);
                            String id =  String.valueOf(getBusRouteNumber());
                            databaseReference.child(id).setValue(object);
                              Toast.makeText(getBaseContext(),"location captured",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getBaseContext(),"Location Not Captured",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}
