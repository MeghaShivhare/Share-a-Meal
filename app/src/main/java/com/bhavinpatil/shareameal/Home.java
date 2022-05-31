package com.bhavinpatil.shareameal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Home extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

//    FirebaseAuth fAuth;
//    FirebaseFirestore fStore;
//    String userID;
//    TextView mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//          mUserName = findViewById(R.id.userName1);
//
//        fAuth = FirebaseAuth.getInstance();
//        fStore = FirebaseFirestore.getInstance();
//        userID = fAuth.getCurrentUser().getUid();
//
//        DocumentReference documentReference = fStore.collection("users").document(userID);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                mUserName.setText(value.getString("fName"));
//
//            }
//        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.fragment1);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }

    //Floating Action Buttons

    public void popup1(View view) {
        Toast.makeText(this, "Add Free Food to Give away", Toast.LENGTH_SHORT).show();
    }
    public void popup2(View view) {
        Toast.makeText(this, "Add Made Food to Give away", Toast.LENGTH_SHORT).show();
    }
    public void popup3(View view) {
        Toast.makeText(this, "Add to Forum what's on your mind?", Toast.LENGTH_SHORT).show();
    }


    //Go to Action



    public void gotoProfile(MenuItem item) {
        startActivity(new Intent(getApplicationContext(), MainProfile.class));

    }

    public void gotoMessages(MenuItem item) {
        startActivity(new Intent(getApplicationContext(), Messages.class));
    }

    public void gotoNotification(MenuItem item) {
        startActivity(new Intent(getApplicationContext(), Notifications.class));
    }

    public void editLocation(MenuItem item) {
        startActivity(new Intent(getApplicationContext(), EditLocation.class));
    }

    public void gotoGuide(MenuItem item) {
        startActivity(new Intent(getApplicationContext(), Guide.class));
    }




    //Back to Action

    public void backtoHome(View view) {
        startActivity(new Intent(getApplicationContext(), Home.class));
    }

    public void backtoHome(MenuItem item) {
        startActivity(new Intent(getApplicationContext(), Home.class));
    }


    //Logout using firebase
    public void backToLogin(MenuItem item) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
    //public void bakctoLogin(MenuItem item) {
    //    startActivity(new Intent(getApplicationContext(), Login.class));
    //}



    // Preview Action
    public void preview1(View view) {
        startActivity(new Intent(getApplicationContext(), SamplePreview.class));
    }
}