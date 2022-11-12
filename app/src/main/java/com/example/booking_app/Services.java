package com.example.booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Services extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;


    Button btnResidential,btnCommercial,btnConstruction,btnGreen,btnSanitation,btnUpholstery,btnLogOut;
    TextView tv_name,tvEditProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);


        btnLogOut = findViewById(R.id.btnLogOut);

        btnResidential = findViewById(R.id.btnResidential);
        btnCommercial = findViewById(R.id.btnCommercial);
        btnConstruction = findViewById(R.id.btnConstruction);
        btnGreen = findViewById(R.id.btnGreen);
        btnSanitation = findViewById(R.id.btnSanitation);
        btnUpholstery = findViewById(R.id.btnUpholstery);




        tv_name = findViewById(R.id.tv_name);
        tvEditProfile = findViewById(R.id.tvEditProfile);

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference data = database.getReference( "Users/" + mAuth.getCurrentUser().getUid() );
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tv_name.setText("Hi " + snapshot.child("fullname").getValue(String.class)+ "!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        tvEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Services.this,EditProfile.class);
                startActivity(i);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Services.this,LogIn.class);
                startActivity(i);
            }
        });


        btnResidential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Services.this,Appointment.class);
                startActivity(i);
            }
        });

        btnCommercial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Services.this,Appointment.class);
                startActivity(i);
            }
        });

        btnConstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Services.this,Details.class);
                i.putExtra("Service", "Post-Construction");
                startActivity(i);
            }
        });

        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Services.this,Appointment.class);
                startActivity(i);
            }
        });

        btnSanitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Services.this,SanitationandDisinfectionAppointment.class);
                startActivity(i);
            }
        });

        btnUpholstery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Services.this,Upholstery_Appointment.class);
                startActivity(i);
            }
        });

    }

}