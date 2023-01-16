package com.example.booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
import java.util.Date;

public class AdminBookDetailsPreview extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    TextView tvName,tvAddress,tvNumber,tvEmail,tvID,tvService,tvCleaning,tvSQM,tvPerson,tvNote,tvDate,tvTime,tvPrice,tvPayment;
    TextView tvLCleaning,tvLSQM;
    DatabaseReference myRef = database.getReference("Pending");
    DatabaseReference myRef2 = database.getReference("PendingUser");
    DatabaseReference myRef3 = database.getReference("Upcoming");
    DatabaseReference myRef4 = database.getReference("UpcomingUser");
    private FirebaseAuth mAuth;

    String fullname,address,number,email,id,service,cleaning,sqm,person,note,date,time,price;
    String uid;

    Button btnChange,btnBack,btnProof;

    Date currentTime;
    String payment;

    ImageView ivProof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_book_details_preview);

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

        currentTime = Calendar.getInstance().getTime();

        Toast.makeText(AdminBookDetailsPreview.this, uid, Toast.LENGTH_SHORT).show();

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnProof = findViewById(R.id.btnProof);
        btnProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog builder = new Dialog(AdminBookDetailsPreview.this);
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



        checkService();

        btnChange = findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fullname = tvName.getText().toString();
                address = tvAddress.getText().toString();
                number =  tvNumber.getText().toString();
                email =  tvEmail.getText().toString();
                id = tvID.getText().toString();
                uid = id.substring(0,5);
                service = tvService.getText().toString();
                cleaning = tvCleaning.getText().toString();
                sqm = tvSQM.getText().toString();
                person = tvPerson.getText().toString();
                note = tvNote.getText().toString();
                date = tvDate.getText().toString();
                time = tvTime.getText().toString();
                payment = tvPayment.getText().toString();

                AppointmentDetails appointmentDetails = new AppointmentDetails(fullname,address,number,email,id,service,cleaning,sqm,person,note,date,time,currentTime.toString(),"Pending",payment);
                myRef3.child(id).setValue(appointmentDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(AdminBookDetailsPreview.this, "Success", Toast.LENGTH_SHORT).show();
                            myRef.child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(AdminBookDetailsPreview.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Intent i = new Intent(AdminBookDetailsPreview.this, AdminPane.class);
                            startActivity(i);

                        }else{
                            Toast.makeText(AdminBookDetailsPreview.this, "Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                myRef4.child(uid + "/" + id).setValue(appointmentDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(AdminBookDetailsPreview.this, "Success", Toast.LENGTH_SHORT).show();
                            myRef2.child(uid + "/" + id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(AdminBookDetailsPreview.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Intent i = new Intent(AdminBookDetailsPreview.this, AdminPane.class);
                            startActivity(i);

                        }else{
                            Toast.makeText(AdminBookDetailsPreview.this, "Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }
        });


        mAuth = FirebaseAuth.getInstance();

        DatabaseReference data = database.getReference( "PendingUser/" + uid + "/" + id + "/");
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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


}