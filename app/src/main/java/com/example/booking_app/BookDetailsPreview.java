package com.example.booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class BookDetailsPreview extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    TextView tvName,tvAddress,tvNumber,tvEmail,tvID,tvService,tvCleaning,tvSQM,tvPerson,tvNote,tvDate,tvTime,tvPrice,tvPayment;
    TextView tvLCleaning,tvLSQM;
    DatabaseReference myRef2 = database.getReference("PendingUser");
    private FirebaseAuth mAuth;

    String fullname,address,number,email,id,service,cleaning,sqm,person,note,date,time,price;
    String uid;
    String payment;

    Button btnBack,btnProof;
    ImageView ivProof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details_preview);


        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvNumber = findViewById(R.id.tvNumber);
        tvAddress = findViewById(R.id.tvAdrress);
        tvNote = findViewById(R.id.tvNote);
        tvCleaning = findViewById(R.id.tvCleaning);
        tvSQM = findViewById(R.id.tvSQM);
        tvPerson = findViewById(R.id.tvPerson);
        tvNote = findViewById(R.id.tvNote);
        tvID = findViewById(R.id.tvID);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        tvService = findViewById(R.id.tvService);
        tvPayment = findViewById(R.id.tvPayment);

        tvLCleaning = findViewById(R.id.tvLCleaning);
        tvLSQM = findViewById(R.id.tvLSQM);

        service = getIntent().getStringExtra("service");
        id = getIntent().getStringExtra("id");

        uid = id.substring(0,5);


        mAuth = FirebaseAuth.getInstance();


        btnBack = findViewById(R.id.btnBack);
        btnProof = findViewById(R.id.btnProof);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btnProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog builder = new Dialog(BookDetailsPreview.this);
                builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                builder.setContentView(R.layout.view_proof);
                builder.setCancelable(true);
                builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                StorageReference getQR = storageRef.child("Proof/"+ id+ ".jpg");
                final long ONE_MEGABYTE = 1024 * 1024;
                getQR.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        ivProof = builder.findViewById(R.id.ivProof);
                        ivProof.setImageBitmap(bitmap);
                    }
                });

                builder.show();


            }
        });

        getValues();

        checkService();


        Toast.makeText(BookDetailsPreview.this, tvPayment.getText().toString(), Toast.LENGTH_SHORT).show();

    }

    public void checkService(){
        if(service.equalsIgnoreCase("Post-Construction Cleaning")){
            tvLCleaning.setVisibility(View.GONE);
            tvLSQM.setVisibility(View.GONE);
            tvCleaning.setVisibility(View.GONE);
            tvSQM.setVisibility(View.GONE);
        }else if(service.equalsIgnoreCase("Sanitation and Disinfection Services")){
            tvLCleaning.setVisibility(View.GONE);
            tvCleaning.setVisibility(View.GONE);
        }else if(service.equalsIgnoreCase("Upholstery")){
            tvCleaning.setTextSize(15);
        }
    }

    public void getValues(){
        DatabaseReference data = database.getReference( "PendingUser/" + uid + "/" + id );
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvName.setText(snapshot.child("fullname").getValue(String.class));
                tvEmail.setText(snapshot.child("email").getValue(String.class));
                tvNumber.setText(snapshot.child("number").getValue(String.class));
                tvAddress.setText(snapshot.child("address").getValue(String.class));
                tvID.setText(snapshot.child("bookingID").getValue(String.class));
                tvService.setText(snapshot.child("service").getValue(String.class));
                tvCleaning.setText(snapshot.child("clean").getValue(String.class));
                tvSQM.setText(snapshot.child("sqm").getValue(String.class));
                tvPerson.setText(snapshot.child("person").getValue(String.class));
                tvNote.setText(snapshot.child("note").getValue(String.class));
                tvDate.setText(snapshot.child("date").getValue(String.class));
                tvTime.setText(snapshot.child("time").getValue(String.class));
                tvPayment.setText(snapshot.child("payment").getValue(String.class));
                payment = tvPayment.getText().toString();
//                payment = snapshot.child("payment").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}