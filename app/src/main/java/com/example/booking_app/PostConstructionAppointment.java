package com.example.booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Random;

public class PostConstructionAppointment extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;

    DatePicker dp_date;
    TimePicker tpTime;
    TextView tvName,tvAddress,tvNumber,tvEmail,tvID,tvService,tvNote;
    EditText etAccommodated,etNotes;

    Button btnBack,btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_construction_appointment);

        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        dp_date = findViewById(R.id.dp_date);
        Calendar mcurrentDate = Calendar.getInstance();
        mcurrentDate.add(Calendar.DATE, 1);
        tpTime = findViewById(R.id.tpTime);
        tpTime.setIs24HourView(false);
        dp_date.setMinDate(mcurrentDate.getTimeInMillis() - 1000);


        etAccommodated = findViewById(R.id.etAccommodated);
        etNotes = findViewById(R.id.etNotes);




        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvNumber = findViewById(R.id.tvNumber);
        tvAddress = findViewById(R.id.tvAdrress);
        tvNote = findViewById(R.id.tvNote);

        tvID = findViewById(R.id.tvID);

        mAuth = FirebaseAuth.getInstance();
        int max=100; int min=0;
        Random randomNum = new Random();

        int randomID1 = randomNum.nextInt(max);
        int randomID2 = randomNum.nextInt(max);
        int randomID3 = randomNum.nextInt(max);
        int randomID4 = randomNum.nextInt(max);
        int randomID5 = randomNum.nextInt(max);

        tvID.setText(mAuth.getUid().substring(0,5)+randomID1+randomID2+randomID3+randomID4+randomID5);



        tvService = findViewById(R.id.tvService);


        DatabaseReference data = database.getReference( "Users/" + mAuth.getCurrentUser().getUid() );
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvName.setText(snapshot.child("fullname").getValue(String.class));
                tvEmail.setText(snapshot.child("email").getValue(String.class));
                tvNumber.setText(snapshot.child("number").getValue(String.class));
                tvAddress.setText(snapshot.child("address").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullname = tvName.getText().toString();
                String address = tvAddress.getText().toString();
                String number = tvNumber.getText().toString();
                String email = tvEmail.getText().toString();
                String bookingID = tvID.getText().toString();
                String service = tvService.getText().toString();


                String person = etAccommodated.getText().toString();
                String note = etNotes.getText().toString();


                String date = dp_date.getMonth() + "/" + dp_date.getDayOfMonth() + "/" + dp_date.getYear();
                String timee;

                if(tpTime.getCurrentHour() < 12 ){
                    timee = "AM";
                }else{
                    timee = "PM";
                }

                tpTime.setIs24HourView(false);

                String time = null;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    int hr;
                    switch (tpTime.getHour()){
                        case 13:
                            hr = 1;
                            break;
                        case 14:
                            hr = 2;
                            break;
                        case 15:
                            hr = 3;
                            break;
                        case 16:
                            hr = 4;
                            break;
                        case 17:
                            hr = 5;
                            break;
                        case 18:
                            hr = 6;
                            break;
                        case 19:
                            hr = 7;
                            break;
                        case 20:
                            hr = 8;
                            break;
                        case 21:
                            hr = 9;
                            break;
                        case 22:
                            hr = 10;
                            break;
                        case 23:
                            hr = 11;
                            break;
                        case 24:
                            hr = 12;
                            break;
                        default:
                            hr = tpTime.getHour();
                    }
                    time = hr + ": " + tpTime.getMinute() + " " + timee;
                }

                    Intent i = new Intent(PostConstructionAppointment.this, Transaction.class);
                    i.putExtra("Fullname", fullname);
                    i.putExtra("Address", address);
                    i.putExtra("Number", number);
                    i.putExtra("Email", email);
                    i.putExtra("ID", bookingID);
                    i.putExtra("Service", service);
                    i.putExtra("Person", person);
                    i.putExtra("Note", note);
                    i.putExtra("Date", date);
                    i.putExtra("Time", time);
                    startActivity(i);




            }
        });
    }
}