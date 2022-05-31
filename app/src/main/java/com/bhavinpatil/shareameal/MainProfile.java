package com.bhavinpatil.shareameal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainProfile extends AppCompatActivity {

    EditText mFirstName, mLastname, mEmail, mMobile;
    //Button mSaveAndBack;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);
        mFirstName = findViewById(R.id.firstName);
        mLastname = findViewById(R.id.lastName);
        mEmail = findViewById(R.id.emailAdd);
        mMobile = findViewById(R.id.mobileNum);
       // mSaveAndBack = findViewById(R.id.saveAndBack);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                String fullName = value.getString("fName");
                int idx = fullName.lastIndexOf(' ');
                if (idx == -1)
                    throw new IllegalArgumentException("Only a single name: " + fullName);
                String firstName = fullName.substring(0, idx);
                String lastName  = fullName.substring(idx + 1);

                mFirstName.setText(firstName);
                mLastname.setText(lastName);
                mEmail.setText(value.getString("email"));
                mMobile.setText(value.getString("Phone"));
            }
        });
    }

    public void backToHome(View view) {
        startActivity(new Intent(getApplicationContext(), Home.class));
    }
}