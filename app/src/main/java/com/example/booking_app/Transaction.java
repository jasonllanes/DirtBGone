package com.example.booking_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Transaction extends AppCompatActivity {

    Button btnBank,btnGCASH,btnCash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        btnBank = findViewById(R.id.btnBank);
        btnGCASH = findViewById(R.id.btnGcash);
        btnCash = findViewById(R.id.btnCash);

        btnBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnGCASH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Transaction.this,GCASH.class);
                startActivity(i);
            }
        });

        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        String id = getIntent().getStringExtra("ID");
        String sqm = getIntent().getStringExtra("SQM");


    }
}