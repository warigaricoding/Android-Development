package com.example.deliveryappliction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    Button mLogout;
    Button mGoToScheduleDelivery;
    Button mGoToContactDrycleaners;
    TextView mWelcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Casting Views
        mGoToScheduleDelivery = findViewById(R.id.button_scheduleDelivery);
        mGoToContactDrycleaners = findViewById(R.id.button_contactDrycleaners);
        mLogout = findViewById(R.id.button_logout);
        mWelcomeText =findViewById(R.id.welcomeText);

        welcome();
        mGoToScheduleDelivery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToScheduleDelivery();
            }
        });

        mGoToContactDrycleaners.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToContactDrycleaners();
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                logout();
            }
        });
    }
    public void goToScheduleDelivery() {
        startActivity(new Intent(getApplicationContext(),SelectItems.class));
        finish();
    }
    public void goToContactDrycleaners() {
        startActivity(new Intent(getApplicationContext(),ContactDrycleaners.class));
        finish();
    }
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
    public void welcome() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String firstName = dataSnapshot.child("FirstName").getValue().toString();
                mWelcomeText.setText("Welcome, "+firstName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}