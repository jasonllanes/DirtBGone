package com.example.booking_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Transaction extends AppCompatActivity {

    Button btnBack,btnBank,btnGCASH,btnCash;
    TextView tvBank,tvGCASH,tvCash;
    String fullname,address,number,email,id,service,cleaning,sqm,person,note,date,time,price;
    boolean no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        btnBack = findViewById(R.id.btnBack);

        btnBank = findViewById(R.id.btnBank);
        btnGCASH = findViewById(R.id.btnGcash);
        btnCash = findViewById(R.id.btnCash);
        tvBank = findViewById(R.id.tvBank);
        tvGCASH = findViewById(R.id.tvGCASH);



        fullname = getIntent().getStringExtra("Fullname");
        address = getIntent().getStringExtra("Address");
        number = getIntent().getStringExtra("Number");
        email = getIntent().getStringExtra("Email");
        id = getIntent().getStringExtra("ID");
        service = getIntent().getStringExtra("Service");
        cleaning = getIntent().getStringExtra("Cleaning");
        sqm = getIntent().getStringExtra("SQM");
        person = getIntent().getStringExtra("Person");
        note = getIntent().getStringExtra("Note");
        date = getIntent().getStringExtra("Date");
        time = getIntent().getStringExtra("Time");
        price = getIntent().getStringExtra("Price");


        checkSQM();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Transaction.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        btnGCASH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Transaction.this,GCASH.class);
                i.putExtra("Fullname", fullname);
                i.putExtra("Address", address);
                i.putExtra("Number", number);
                i.putExtra("Email", email);
                i.putExtra("ID", id);
                i.putExtra("Service", service);
                i.putExtra("Cleaning", cleaning);
                i.putExtra("SQM", sqm);
                i.putExtra("Person", person);
                i.putExtra("Note", note);
                i.putExtra("Date", date);
                i.putExtra("Time", time);
                i.putExtra("Price", price);

                startActivity(i);
            }
        });

        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(service.equalsIgnoreCase("Post-Construction Cleaning")){
                    Intent i = new Intent(Transaction.this,Summary.class);
                    i.putExtra("Fullname", fullname);
                    i.putExtra("Address", address);
                    i.putExtra("Number", number);
                    i.putExtra("Email", email);
                    i.putExtra("ID", id);
                    i.putExtra("Service", service);
                    i.putExtra("Person", person);
                    i.putExtra("Note", note);
                    i.putExtra("Date", date);
                    i.putExtra("Time", time);
                    i.putExtra("Payment", "Cash");
                    startActivity(i);
                }else{
                    Intent i = new Intent(Transaction.this,Summary.class);
                    i.putExtra("Fullname", fullname);
                    i.putExtra("Address", address);
                    i.putExtra("Number", number);
                    i.putExtra("Email", email);
                    i.putExtra("ID", id);
                    i.putExtra("Service", service);
                    i.putExtra("Cleaning", cleaning);
                    i.putExtra("SQM", sqm);
                    i.putExtra("Person", person);
                    i.putExtra("Note", note);
                    i.putExtra("Date", date);
                    i.putExtra("Time", time);
                    i.putExtra("Price", price);
                    i.putExtra("isGCASH", "NO");
                    startActivity(i);
                }

            }
        });



    }
    public void checkSQM(){
        if(service.equalsIgnoreCase("Residential Cleaning") || service.equalsIgnoreCase("Commercial Cleaning") || service.equalsIgnoreCase("Green Cleaning Services")){
            tvBank.setVisibility(View.VISIBLE);
            tvGCASH.setVisibility(View.VISIBLE);
            btnBank.setVisibility(View.GONE);
            btnGCASH.setVisibility(View.GONE);
        }else if(service.equalsIgnoreCase("Post-Construction Cleaning")){
            tvBank.setVisibility(View.VISIBLE);
            tvGCASH.setVisibility(View.VISIBLE);
            btnBank.setVisibility(View.GONE);
            btnGCASH.setVisibility(View.GONE);
        }else if(service.equalsIgnoreCase("Sanitation and Disinfection Services")){
            tvBank.setVisibility(View.VISIBLE);
        }else if(service.equalsIgnoreCase("Upholstery")){

            tvBank.setVisibility(View.VISIBLE);
        }else if(sqm.equalsIgnoreCase("Not sure")){
            tvBank.setVisibility(View.VISIBLE);
            tvGCASH.setVisibility(View.VISIBLE);
            btnBank.setVisibility(View.GONE);
            btnGCASH.setVisibility(View.GONE);
        }
    }
}