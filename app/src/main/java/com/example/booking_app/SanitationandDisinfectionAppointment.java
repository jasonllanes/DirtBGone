package com.example.booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SanitationandDisinfectionAppointment extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;

    TextView tvName,tvEmail,tvNumber,tvAddress;

    Button btnBack;

    Spinner sSQM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanitationand_disinfection_appointment);

        sSQM = findViewById(R.id.sSQM);

        btnBack = findViewById(R.id.btnBack);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvNumber = findViewById(R.id.tvNumber);
        tvAddress = findViewById(R.id.tvAdrress);

        mAuth = FirebaseAuth.getInstance();


        SpinnerItems();

        String sqm = sSQM.getSelectedItem().toString();

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





    }
    public void SpinnerItems(){
        List<String> list =  new ArrayList<String>();
        list.add("0–1,000 sq. ft.");
        list.add("1,000–2,000 sq. ft.");
        list.add("2,000–3,000 sq. ft.");
        list.add("3,000–4,000 sq. ft.");
        list.add("4,000–5,000 sq. ft. SQM");
        list.add("5,000–6,000 sq. ft.");
        list.add("6,000–7,000 sq. ft.");
        list.add("7,000–8,000 sq. ft.");
        list.add("8,000–9,000 sq. ft");
        list.add("9,000–10,000 sq. ft.");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                SanitationandDisinfectionAppointment.this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.sSQM);
        sItems.setAdapter(adapter);
    }
}