package com.example.booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import java.util.concurrent.atomic.AtomicLong;

public class Upholstery_Appointment extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Pending");
    private FirebaseAuth mAuth;

    DatePicker dp_date;
    TimePicker tpTime;
    TextView tvName,tvAddress,tvNumber,tvEmail,tvID,tvService,tvNote;
    RadioGroup rgCleaning;
    Spinner sSQM;
    EditText etAccommodated,etNotes;
    Button btnBack,btnNext;
    double estimatedPrice =0;
    String service;
    public String cleaning = "Deep";
    private static AtomicLong idCounter = new AtomicLong();

    CheckBox s1,s2,s3,s4,s5,s6,
            m1,m2,m3,m4,m5,
            c1,fr1;
    String vs1 = " ",vs2= " ",vs3= " ",vs4= " ",vs5= " ",vs6= " ",
            vm1= " ",vm2= " ",vm3= " ",vm4= " ",vm5= " ",
            vc1= " ",vfr1= " ";
    EditText etCarpet;
    String otherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upholstery_appointment);

        rgCleaning = findViewById(R.id.rgCleaning);

        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        dp_date = findViewById(R.id.dp_date);
        Calendar mcurrentDate = Calendar.getInstance();
        mcurrentDate.add(Calendar.DATE, 1);
        tpTime = findViewById(R.id.tpTime);
        tpTime.setIs24HourView(false);
        dp_date.setMinDate(mcurrentDate.getTimeInMillis() - 1000);
        sSQM = findViewById(R.id.sSQM);

        etAccommodated = findViewById(R.id.etAccommodated);
        etNotes = findViewById(R.id.etNotes);


        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvNumber = findViewById(R.id.tvNumber);
        tvAddress = findViewById(R.id.tvAdrress);
        tvNote = findViewById(R.id.tvNote);

        tvID = findViewById(R.id.tvID);


        tvService = findViewById(R.id.tvService);


        mAuth = FirebaseAuth.getInstance();
        int max=100; int min=0;
        Random randomNum = new Random();

        int randomID1 = randomNum.nextInt(max);
        int randomID2 = randomNum.nextInt(max);
        int randomID3 = randomNum.nextInt(max);
        int randomID4 = randomNum.nextInt(max);
        int randomID5 = randomNum.nextInt(max);

        tvID.setText(mAuth.getUid().substring(0,5)+randomID1+randomID2+randomID3+randomID4+randomID5);


        service = getIntent().getStringExtra("Service");
        tvService.setText(service);

        s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);
        s3 = findViewById(R.id.s3);
        s4 = findViewById(R.id.s4);
        s5 = findViewById(R.id.s5);
        s6 = findViewById(R.id.s6);

        m1 = findViewById(R.id.m1);
        m2 = findViewById(R.id.m2);
        m3 = findViewById(R.id.m3);
        m4 = findViewById(R.id.m4);
        m5 = findViewById(R.id.m5);

        c1 = findViewById(R.id.c1);
        fr1 = findViewById(R.id.fr1);

        etCarpet = findViewById(R.id.etCarpet);



        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    estimatedPrice += 1000.00;
                    vs1 = "Solo Couch, La-Z Boy, Single Recliner, Executive Chair";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }else{
                    estimatedPrice -= 1000.00;
                    vs1 = " ";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }

            }
        });
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    estimatedPrice += 1250.00;
                    vs2 = "\nLove Seat, Chaise Lounge, Double Recliner";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }else{
                    estimatedPrice -= 1250.00;
                    vs2 = " ";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }

            }
        });
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    estimatedPrice += 1500.00;
                    vs3 = "\nTriple Seater Couch";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }else{
                    estimatedPrice -= 1500.00;
                    vs3 = " ";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }

            }
        });
        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    estimatedPrice += 1750.00;
                    vs4 = "Four-Seater Couch";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }else{
                    estimatedPrice -= 1750.00;
                    vs4 = " ";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }

            }
        });
        s5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    estimatedPrice += 2000.00;
                    vs5 = "Five-Seater Couch";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }else{
                    estimatedPrice -= 2000.00;
                    vs5 = " ";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }

            }
        });
        s6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    estimatedPrice += 2250.00;
                    vs6 = "Six-Seater Couch";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }else{
                    estimatedPrice -= 2250.00;
                    vs6 = " ";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }

            }
        });

        m1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    estimatedPrice += 1000.00;
                    vm1 = "\nSingle Bed (36\" X 75\")";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }else{
                    estimatedPrice -= 1000.00;
                    vm1 = " ";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }

            }
        });
        m2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    estimatedPrice += 1500.00;
                    vm2 = "\nDouble Standard Twin (38\" X 75\") / Full Size (54\" X 75\")";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }else{
                    estimatedPrice -= 1500.00;
                    vm2 = " ";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }

            }
        });
        m3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    estimatedPrice += 2000.00;
                    vm3 = "\nStandard Queen Size (60\" X 80\") / California Queen Size (60\" X 80\")";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }else{
                    estimatedPrice -= 2000.00;
                    vm3 = " ";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }

            }
        });
        m4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    estimatedPrice += 2500.00;
                    vm4 = "\nStandard King Size (78\" X 80\") / California King Size (72\" X 84\")";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }else{
                    estimatedPrice -= 2500.00;
                    vm4 = " ";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }

            }
        });
        m5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    estimatedPrice += 2500.00;
                    vm5 = "\nSofa Bed";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }else{
                    estimatedPrice -= 2500.00;
                    vm5 = " ";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }

            }
        });

        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    estimatedPrice += 200.00;
                    vc1 = "\nDining Chair, Computer Chair, Office Chair, Bar Stool";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }else{
                    estimatedPrice -= 200.00;
                    vc1 = " ";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }

            }
        });

        fr1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    estimatedPrice += 500.00;
                    vfr1 = "\nFloor Rugs (Not Carpet, Tiles/Flooring)";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }else{
                    estimatedPrice -= 500.00;
                    vfr1 = " ";
                    Toast.makeText(Upholstery_Appointment.this, String.valueOf(estimatedPrice), Toast.LENGTH_SHORT).show();
                }

            }
        });


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
                String otherServ = vs1 + vs2 + vs3 + vs4 + vs5 + vs6 +
                                    vm1 + vm2 + vm3 + vm4 + vm5 +
                                    vc1 + vfr1;
                String sqm = etCarpet.getText().toString();

                double carpetValue = Double.valueOf(etCarpet.getText().toString())*50;
                double total = estimatedPrice + carpetValue;
                String price = String.valueOf(total);

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

                    Toast.makeText(Upholstery_Appointment.this, price, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Upholstery_Appointment.this, Transaction.class);
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
                    i.putExtra("Price", price);
                    i.putExtra("Cleaning", otherServ);
                    i.putExtra("SQM", sqm);
                    startActivity(i);

            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}