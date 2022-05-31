package com.bhavinpatil.shareameal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddressDetails extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mAddLine1, mAddline2, mCity, mState, mPostalCode;
    Button mSaveBtn;
    FirebaseAuth fAuth;

    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_details);

        mAddLine1 = findViewById(R.id.address1);
        mAddline2 = findViewById(R.id.address2);
        mCity = findViewById(R.id.city);
        mState = findViewById(R.id.state);
        mPostalCode = findViewById(R.id.postalCode);
        mSaveBtn = findViewById(R.id.saveAdd);

        fStore = FirebaseFirestore.getInstance();
        //-------------------------
        fAuth = FirebaseAuth.getInstance();

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address1 = mAddLine1.getText().toString().trim();
                String address2 = mAddline2.getText().toString().trim();
                //Changes
                String city = mCity.getText().toString().trim();
                String state = mState.getText().toString().trim();
                String postalCode = mPostalCode.getText().toString().trim();


                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("addressDetails").document(userID);
                Map<String, Object> user = new HashMap<>();
                user.put("Address Line 1",address1);
                user.put("Address Line 2",address2);
                user.put("City",city);
                user.put("State",state);
                user.put("Postal Code",postalCode);

                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: User profile is created for"+userID);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: "+ e.toString());
                    }
                });

                startActivity(new Intent(getApplicationContext(), Home.class));

            }
        });

    }


}