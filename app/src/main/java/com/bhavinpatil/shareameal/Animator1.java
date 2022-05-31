package com.bhavinpatil.shareameal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Animator1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator1);
    }

    public void gotoHOme(View view) {
        startActivity(new Intent(getApplicationContext(), Home.class));
    }
}