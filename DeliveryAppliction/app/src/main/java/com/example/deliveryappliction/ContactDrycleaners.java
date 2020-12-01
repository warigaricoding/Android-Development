package com.example.deliveryappliction;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactDrycleaners extends AppCompatActivity {

    Button mSubmitMessage,mBackToMain,mLogout;
    EditText mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_drycleaners);

        mSubmitMessage = findViewById(R.id.button_SubmitMessage);
        mBackToMain = findViewById(R.id.button_back_to_main);
        mLogout = findViewById(R.id.button_logout5);
        mMessage = findViewById(R.id.editTextMessage);

        mSubmitMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                submitMessage();
            }
        });
        mBackToMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
        mLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    public void submitMessage() {
        if (mMessage.getText().toString().trim().equals(" ")){
            mSubmitMessage.setError("Please write more in detail");
        }
        else {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.child("Message").push().setValue(mMessage.getText().toString().trim());
        goToMailConfirmation();
       finish();
    }}
    public void backToMain() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
    public void goToMailConfirmation() {
        startActivity(new Intent(getApplicationContext(),MessageConfirmation.class));
        finish();
    }
}