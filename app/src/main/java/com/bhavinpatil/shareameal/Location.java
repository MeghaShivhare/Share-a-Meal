package com.bhavinpatil.shareameal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class Location extends AppCompatActivity {

    //Initializing variable

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
//        getActionBar().hide();

        //Assign Variable
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.currentlocation);

        //Initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this);

        //Check permission

        if (ActivityCompat.checkSelfPermission(Location.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //When permission granted
            //call method
            getCurrentLocation();
        }else {
            //When permission denied
            //Request Permissions
            ActivityCompat.requestPermissions(Location.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }



    private void getCurrentLocation() {
//        //Initialized task location
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
////             TODO: Consider calling
////                ActivityCompat#requestPermissions
////             here to request the missing permissions, and then overriding
////               public void onRequestPermissionsResult(int requestCode, String[] permissions,
////                                                      int[] grantResults)
////             to handle the case where the user grants the permission. See the documentation
////             for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        @SuppressLint("MissingPermission") Task<android.location.Location> task = client.getLastLocation();

        if (ActivityCompat.checkSelfPermission(Location.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //When permission granted
            //call method
            Task<android.location.Location> task = client.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<android.location.Location>() {
                @Override
                public void onSuccess(android.location.Location location) {
                    //When Success
                    if(location!=null){
                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                // Initialized Lat and Lng
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                //Create Marker option
                                MarkerOptions options = new MarkerOptions().position(latLng).title("Here You Are");
                                //Zoom map
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                                //Add Marker on Map
                                googleMap.addMarker(options);
                            }
                        });
                    }
                }
            });
//            getCurrentLocation();
        }
//        else {
//            //When permission denied
//            //Request Permissions
//            ActivityCompat.requestPermissions(Location.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
//        }

//        Task<android.location.Location> task = client.getLastLocation();
//        task.addOnSuccessListener(new OnSuccessListener<android.location.Location>() {
//            @Override
//            public void onSuccess(android.location.Location location) {
//                //When Success
//                if(location!=null){
//                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
//                        @Override
//                        public void onMapReady(GoogleMap googleMap) {
//                            // Initialized Lat and Lng
//                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                            //Create Marker option
//                            MarkerOptions options = new MarkerOptions().position(latLng).title("Here You Are");
//                            //Zoom map
//                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//                            //Add Marker on Map
//                            googleMap.addMarker(options);
//                        }
//                    });
//                }
//            }
//        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 44){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //When Permision granted
                //call method
                getCurrentLocation();
            }
        }
    }

    public void toAddress(View view) {
        startActivity(new Intent(getApplicationContext(), AddressDetails.class));
    }
}