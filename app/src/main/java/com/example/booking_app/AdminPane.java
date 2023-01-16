package com.example.booking_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AdminPane extends AppCompatActivity {

    Button btnPending,btnUpcoming,btnCompleted,btnLogOut;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pane);

        btnPending = findViewById(R.id.btnPending);
        btnUpcoming = findViewById(R.id.btnUpcoming);
        btnCompleted = findViewById(R.id.btnCompleted);

        btnLogOut = findViewById(R.id.btnLogOut);
        builder = new AlertDialog.Builder(this);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("Do you want to log out?")
                        .setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Toast.makeText(getApplicationContext(),"Log out successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent ii = new Intent(AdminPane.this,LogIn.class);
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


        btnPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminPane.this,AdminPending.class);
                startActivity(i);
            }
        });

        btnUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminPane.this,AdminUpcoming.class);
                startActivity(i);
            }
        });

        btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminPane.this,AdminCompleted.class);
                startActivity(i);
            }
        });


    }
}