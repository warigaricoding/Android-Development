package com.example.deliveryappliction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MessageConfirmation extends AppCompatActivity {

    Button mBackToMain, mLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_confirmation);

        mBackToMain = findViewById(R.id.button_back_to_main_3);
        mLogout = findViewById(R.id.button_logout4);

        mBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain();
            }
        });
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }
    public void backToMain() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}