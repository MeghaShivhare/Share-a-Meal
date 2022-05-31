package com.bhavinpatil.shareameal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;

public class SamplePreview extends AppCompatActivity {

    SupportMapFragment supportMapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_preview);

//        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.currentlocation);

//        if (ActivityCompat.checkSelfPermission(SamplePreview.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            //When permission granted
//            //call method
//        }else {
//            //When permission denied
//            //Request Permissions
//            ActivityCompat.requestPermissions(SamplePreview.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
//        }

    }

//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if(requestCode == 44){
//            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                //When Permision granted
//                //call method
//            }
//        }
//    }

    public void backtoHome(View view) {
        startActivity(new Intent(getApplicationContext(), Home.class));
    }

    public void requestBtn(View view) {
        Toast.makeText(this, " Requested ! wait for Approval , You will be Notified ", Toast.LENGTH_SHORT).show();
    }
}