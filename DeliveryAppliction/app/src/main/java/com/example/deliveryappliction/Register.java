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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class Register extends AppCompatActivity {
    EditText mFirstName,mLastName,mPhoneNumber,mEmail,mAddress,mPassword,mConfirmPassword;
    Button mRegisterButton,mBackToLogin;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    String userID;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstName = findViewById(R.id.first_name);
        mLastName = findViewById(R.id.last_name);
        mAddress = findViewById(R.id.address);
        mEmail = findViewById(R.id.email);
        mPhoneNumber = findViewById(R.id.phone_number);
        mRegisterButton = findViewById(R.id.button_registration);
        mBackToLogin = findViewById(R.id.button_back_to_login);
        mPassword = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.confirm_password);
        //firebase, to store Registered info onto the database
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User");
        //if the user is already registered
        if(fAuth.getCurrentUser() != null)  {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstName = mFirstName.getText().toString().trim();
                final String lastName = mLastName.getText().toString().trim();
                final String address = mAddress.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();
                final String phoneNumber =mPhoneNumber.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String confirmPassword = mConfirmPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required");
                    return;
                }
                if(password.length() < 6) {
                    mPassword.setError("Password must be at least 6 characters");
                    return;
                }

                if(!confirmPassword.equals(password)){
                    mConfirmPassword.setError("Please check to see if it matches with password");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //registers the user on the firebase
                            User userInfo = new User(
                                    firstName, lastName, phoneNumber, email, address
                            );

                            FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                }
                            });



                        } else {
                            Toast.makeText(Register.this, "Error, " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                fAuth.createUserWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });


            }
        });


     mBackToLogin.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View v){
             openLogin();
         }
     });
    }

    public void openLogin() {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}