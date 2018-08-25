package com.example.mounika.hitam_try.BusTracking;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mounika.hitam_try.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class BusActivity extends AppCompatActivity implements View.OnClickListener{


    private static int busRouteNumber;
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
        BusActivity.busRouteNumber = busRouteNumber;
    }
    public int getBusRouteNumber() {
        return busRouteNumber;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.updateButton):
                updateLocation();
                break;
            case (R.id.retrieveButton):

        }


    }

    private void retrieveLocation() {
        final DatabaseReference root = FirebaseDatabase.getInstance().getReference("Locations/Route "+ getBusRouteNumber());
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BusObject route = dataSnapshot.getValue(BusObject.class);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void drawMap(String latitude, String longitude) {
        Intent mapsIntent = new Intent(Intent.ACTION_VIEW);
        mapsIntent.setData(Uri.parse("geo:" + latitude + "," +longitude+ "?q="+ latitude + "," + longitude +"Bus is Here"));
        startActivity(mapsIntent);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
    private void updateLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


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
                           String id =  "Route  " + getBusRouteNumber();
                            databaseReference.child(id).setValue(object);
                              Toast.makeText(getBaseContext(),"location captured",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getBaseContext(),"Location Not Captured",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}
