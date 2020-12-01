package com.example.deliveryappliction;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SelectItems extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button mResetBtn1,mResetBtn2,mResetBtn3,mResetBtn4,mResetBtn5,mResetBtn6;
    Button mMinBtn1,mMinBtn2,mMinBtn3,mMinBtn4,mMinBtn5,mMinBtn6;
    Button mPlusBtn1,mPlusBtn2,mPlusBtn3,mPlusBtn4,mPlusBtn5,mPlusBtn6;
    TextView mCount1,mCount2,mCount3,mCount4,mCount5,mCount6;
    Button mSubmitDelivery;
    Button mBackToMenu;
    Button mLogout;
    Button mDateView;
    TextView mTextDate;
    String mDate;

    int count1 = 0;
    int count2 = 0;
    int count3 = 0;
    int count4 = 0;
    int count5 = 0;
    int count6 = 0;
    boolean check = false;
    //TextView mTextDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_items);
        mDateView = findViewById(R.id.button_DateView);
        mTextDate = findViewById(R.id.textDate);
        mResetBtn1 = findViewById(R.id.button_reset1);
        mResetBtn2 = findViewById(R.id.button_reset2);
        mResetBtn3 = findViewById(R.id.button_reset3);
        mResetBtn4 = findViewById(R.id.button_reset4);
        mResetBtn5 = findViewById(R.id.button_reset5);
        mResetBtn6 = findViewById(R.id.button_reset6);
        mMinBtn1 = findViewById(R.id.button_min1);
        mMinBtn2 = findViewById(R.id.button_min2);
        mMinBtn3 = findViewById(R.id.button_min3);
        mMinBtn4 = findViewById(R.id.button_min4);
        mMinBtn5 = findViewById(R.id.button_min5);
        mMinBtn6 = findViewById(R.id.button_min6);
        mPlusBtn1 = findViewById(R.id.button_plus1);
        mPlusBtn2 = findViewById(R.id.button_plus2);
        mPlusBtn3 = findViewById(R.id.button_plus3);
        mPlusBtn4 = findViewById(R.id.button_plus4);
        mPlusBtn5 = findViewById(R.id.button_plus5);
        mPlusBtn6 = findViewById(R.id.button_plus6);
        mCount1 = findViewById(R.id.textCount1);
        mCount2 = findViewById(R.id.textCount2);
        mCount3 = findViewById(R.id.textCount3);
        mCount4 = findViewById(R.id.textCount4);
        mCount5 = findViewById(R.id.textCount5);
        mCount6 = findViewById(R.id.textcount6);
        mSubmitDelivery = findViewById(R.id.button_SubmitDelivery);
        mBackToMenu = findViewById(R.id.button_backToMain2);
        mLogout = findViewById(R.id.button_logout3);
        check = false;

        mDateView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //goToScheduleDelivery();
                showDatePickerDialog();
            }
        });

        mResetBtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count1=0;
                mCount1.setText(Integer.toString(count1));
            }
        });
        mResetBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count2=0;
                mCount2.setText(Integer.toString(count2));
            }
        });
        mResetBtn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count3=0;
                mCount3.setText(Integer.toString(count3));
            }
        });
        mResetBtn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count4=0;
                mCount4.setText(Integer.toString(count4));
            }
        });
        mResetBtn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count5=0;
                mCount5.setText(Integer.toString(count5));
            }
        });
        mResetBtn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count6=0;
                mCount6.setText(Integer.toString(count6));
            }
        });
        mPlusBtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count1++;
                mCount1.setText(Integer.toString(count1));

            }
        });
        mPlusBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count2++;
                mCount2.setText(Integer.toString(count2));

            }
        });
        mPlusBtn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count3++;
                mCount3.setText(Integer.toString(count3));

            }
        });
        mPlusBtn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count4++;
                mCount4.setText(Integer.toString(count4));

            }
        });
        mPlusBtn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count5++;
                mCount5.setText(Integer.toString(count5));

            }
        });
        mPlusBtn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count6++;
                mCount6.setText(Integer.toString(count6));

            }
        });
        mMinBtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(count1>=1) {
                    count1--;
                    mCount1.setText(Integer.toString(count1));
                } else { return; }

            }
        });
        mMinBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(count2>=1) {
                    count2--;
                    mCount2.setText(Integer.toString(count2));
                } else { return; }

            }
        });
        mMinBtn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(count3>=1) {
                    count3--;
                    mCount3.setText(Integer.toString(count3));
                } else { return; }

            }
        });
        mMinBtn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(count4>=1) {
                    count4--;
                    mCount4.setText(Integer.toString(count4));
                } else { return; }

            }
        });
        mMinBtn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(count5>=1) {
                    count5--;
                    mCount5.setText(Integer.toString(count5));
                } else { return; }

            }
        });
        mMinBtn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(count6>=1) {
                    count6--;
                    mCount6.setText(Integer.toString(count6));
                } else { return; }

            }
        });

        mSubmitDelivery.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if (mCount1.getText().toString().equals("0") && mCount2.getText().toString().equals("0") && mCount3.getText().toString().equals("0") && mCount4.getText().toString().equals("0") &&
                        mCount5.getText().toString().equals("0") && mCount6.getText().toString().equals("0")) {
                    mSubmitDelivery.setError("Cannot submit 0 items!");

                } else if (check == false) {
                    mSubmitDelivery.setError("You must choose a date in order to continue");

                } else {
                    submitDelivery();
                }

            } });
        mBackToMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                backToMenu();
            }
        });
        mLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                logout();
            }
        });
    }
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
    public void submitDelivery(){
        //create item
        String shirts = mCount1.getText().toString().trim();
        String pants = mCount2.getText().toString().trim();
        String blouse = mCount3.getText().toString().trim();
        String suits = mCount4.getText().toString().trim();
        String comforters = mCount5.getText().toString().trim();
        String shoes = mCount6.getText().toString().trim();




        String deliveryKey =  FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Delivery").push().getKey();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Delivery").child(deliveryKey);

            HashMap hashMap = new HashMap();
                hashMap.put("Shirts",shirts);
                hashMap.put("Pants",pants);
                hashMap.put("Blouse",blouse);
                hashMap.put("Suits",suits);
                hashMap.put("Comforters",comforters);
                hashMap.put("Shoes",shoes);
                hashMap.put("Date",mDate);

                ref.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(SelectItems.this, "Items Received" + "\n" + "Your Delivery Request Has Been Completed!", Toast.LENGTH_SHORT).show();

            }
        });

        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    public void backToMenu(){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int month, int dayOfMonth, int year) {
        String date = "The Pickup Day Is Set To" + "\n" + "month/day/year: " + month + "/" + dayOfMonth + "/" + year;
        mTextDate.setText(date);
        date = mTextDate.getText().toString().trim();

        date =  year + "/" + month + "/" + dayOfMonth;
        mDate = date;
        check = true;

        //felt giddy after getting the control over .getUid, .child, .setValue. XD

    }
}