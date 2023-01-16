package com.example.booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Services extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;
    AlertDialog.Builder builder;

    Button btnResidential,btnCommercial,btnConstruction,btnGreen,btnSanitation,btnUpholstery,btnLogOut;
    Button btnPending,btnUpcoming,btnCompleted;
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


        btnPending = findViewById(R.id.btnPending);
        btnUpcoming = findViewById(R.id.btnUpcoming);
        btnCompleted = findViewById(R.id.btnFinished);

        builder = new AlertDialog.Builder(this);

        tv_name = findViewById(R.id.tv_name);
//        tvEditProfile = findViewById(R.id.tvEditProfile);

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


        btnPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Services.this,Pending.class);
                startActivity(i);
            }
        });

        btnUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Services.this,UpcomingBooked.class);
                startActivity(i);
            }
        });

        btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Services.this,Completed.class);
                startActivity(i);
            }
        });

//        tvEditProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Services.this,EditProfile.class);
//                startActivity(i);
//            }
//        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.setMessage("Do you want to log out?")
                                .setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseAuth.getInstance().signOut();
                                Toast.makeText(getApplicationContext(),"Log out successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent ii = new Intent(Services.this,LogIn.class);
                                startActivity(ii);

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Log out...");
                alert.show();



            }
        });


        btnResidential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Services.this,Details.class);
                i.putExtra("Service", "Residential");
                startActivity(i);
            }
        });

        btnCommercial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Services.this,Details.class);
                i.putExtra("Service", "Commercial");
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
                Intent i = new Intent(Services.this,Details.class);
                i.putExtra("Service", "Green");
                startActivity(i);
            }
        });

        btnSanitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Services.this,Details.class);
                i.putExtra("Service", "Sanitation and Disinfection");
                startActivity(i);
            }
        });

        btnUpholstery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Services.this,Details.class);
                i.putExtra("Service", "Upholstery Appointment");
                startActivity(i);
            }
        });

    }

}