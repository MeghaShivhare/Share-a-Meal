package com.bhavinpatil.shareameal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void toLogin(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
    }

    public void toSignup(View view) {
        startActivity(new Intent(getApplicationContext(), Signup.class));
    }
}