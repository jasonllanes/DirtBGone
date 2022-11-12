package com.example.booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Appointment extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Pending");
    private FirebaseAuth mAuth;

    DatePicker dp_date;
    TimePicker tpTime;
    TextView tvName,tvAddress,tvNumber,tvEmail,tvID,tvService;
    RadioGroup rgCleaning;
    Spinner sSQM;
    EditText etAccommodated,etNotes;
    Button btnBack,btnNext;
    int estimatedPrice;

    public String cleaning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        rgCleaning = findViewById(R.id.rgCleaning);

        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        dp_date = findViewById(R.id.dp_date);
        Calendar mcurrentDate = Calendar.getInstance();
        mcurrentDate.add(Calendar.DATE, 1);
        tpTime = findViewById(R.id.tpTime);
        dp_date.setMinDate(mcurrentDate.getTimeInMillis() - 1000);

        sSQM = findViewById(R.id.sSQM);

        etAccommodated = findViewById(R.id.etAccommodated);
        etNotes = findViewById(R.id.etNotes);


        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvNumber = findViewById(R.id.tvNumber);
        tvAddress = findViewById(R.id.tvAdrress);

        tvID = findViewById(R.id.tvID);
        tvService = findViewById(R.id.tvService);


        mAuth = FirebaseAuth.getInstance();

        SpinnerItems();

        rgCleaning.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.rbDeep:
                        cleaning = "Deep";
                        break;
                    case R.id.rbSurface:
                        cleaning = "Surface";
                        break;

                }
            }
        });





        mAuth = FirebaseAuth.getInstance();

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




        sSQM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 1:
                        if(cleaning == "Deep"){
                            Toast.makeText(Appointment.this, "₱4,200.00", Toast.LENGTH_SHORT).show();
                        }else if(cleaning == "Surface"){
                            Toast.makeText(Appointment.this, "₱3,750.00", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if(cleaning == "Deep"){
                            Toast.makeText(Appointment.this, "₱85/SQM ", Toast.LENGTH_SHORT).show();
                        }else if(cleaning == "Surface"){
                            Toast.makeText(Appointment.this, "₱75/SQM ", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        if(cleaning == "Deep"){
                            Toast.makeText(Appointment.this, "₱80/SQM", Toast.LENGTH_SHORT).show();
                        }else if(cleaning == "Surface"){
                            Toast.makeText(Appointment.this, "₱65/SQM ", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 4:
                        if(cleaning == "Deep"){
                            Toast.makeText(Appointment.this, "₱75/SQM", Toast.LENGTH_SHORT).show();
                        }else if(cleaning == "Surface"){
                            Toast.makeText(Appointment.this, "₱60/SQM ", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 5:
                        if(cleaning == "Deep"){
                            Toast.makeText(Appointment.this, "₱70/SQM", Toast.LENGTH_SHORT).show();
                        }else if(cleaning == "Surface"){
                            Toast.makeText(Appointment.this, "₱55/SQM ", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 6:
                        if(cleaning == "Deep"){
                            Toast.makeText(Appointment.this, "₱65/SQM", Toast.LENGTH_SHORT).show();
                        }else if(cleaning == "Surface"){
                            Toast.makeText(Appointment.this, "₱50/SQM ", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 7:
                        if(cleaning == "Deep"){
                            Toast.makeText(Appointment.this, "₱60/SQM", Toast.LENGTH_SHORT).show();
                        }else if(cleaning == "Surface"){
                            Toast.makeText(Appointment.this, "₱45/SQM ", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAppointment();
            }
        });

    }

    public void SpinnerItems(){
        List<String> list =  new ArrayList<String>();
        list.add("Select among the choices...");
        list.add("Below 50 SQM");
        list.add("50 – 99 SQM");
        list.add("100 – 199 SQM");
        list.add("200 – 299 SQM");
        list.add("300 – 499 SQM");
        list.add("500 – 599 SQM");
        list.add("600 – 999 SQM");
        list.add("Not sure");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                Appointment.this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.sSQM);
        sItems.setAdapter(adapter);
    }

    public void getAppointment(){
        String fullname = tvName.getText().toString();
        String address = tvAddress.getText().toString();
        String number = tvNumber.getText().toString();
        String email = tvEmail.getText().toString();
        String bookingID = tvID.getText().toString();
        String service = tvService.getText().toString();
        String clean = cleaning;
        String sqm = sSQM.getSelectedItem().toString();
        String person = etAccommodated.getText().toString();
        String note = etNotes.getText().toString();

        String date = dp_date.getMonth() + "/" + dp_date.getDayOfMonth() + "/" + dp_date.getYear();
        String timee;
        if(tpTime.getCurrentHour() < 12 ){
            timee = "AM";
        }else{
            timee = "PM";
        }
        String time = tpTime.getCurrentHour() + ": " + tpTime.getCurrentMinute() + " " + timee;

        AppointmentDetails appointmentDetails = new AppointmentDetails(fullname,address,number,email,bookingID,service,clean,sqm,person,note,date,time);
        myRef.child(mAuth.getUid()).setValue(appointmentDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(Appointment.this, "Success", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(Appointment.this, "Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}