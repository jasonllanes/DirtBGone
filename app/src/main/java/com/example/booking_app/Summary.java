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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Summary extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Pending");
    DatabaseReference myRef2 = database.getReference("PendingUser");
    private FirebaseAuth mAuth;

    DatePicker dp_date;
    TimePicker tpTime;
    TextView tvName,tvAddress,tvNumber,tvEmail,tvID,tvService,tvCleaning,tvSQM,tvPerson,tvNote,tvDate,tvTime,tvPrice,tvPayment;
    TextView tvLCleaning,tvLSQM;
    RadioGroup rgCleaning;
    Spinner sSQM;
    EditText etAccommodated,etNotes;
    Button btnBack,btnNext,btnProof;
    String estimatedPrice;
    String servicee;
    public String cleaningg = "Deep";
    Date currentTime;
    String fullname,address,number,email,id,service,cleaning,sqm,person,note,date,time,price,payment = "Cash";
    String uid;

    ImageView ivProof;

    String isGCASH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

         currentTime = Calendar.getInstance().getTime();

//        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
//        String formattedDate = df.format(currentTime);


        rgCleaning = findViewById(R.id.rgCleaning);

        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        btnProof = findViewById(R.id.btnProof);

        Calendar mcurrentDate = Calendar.getInstance();
        mcurrentDate.add(Calendar.DATE, 1);


        sSQM = findViewById(R.id.sSQM);

        etAccommodated = findViewById(R.id.etAccommodated);
        etNotes = findViewById(R.id.etNotes);


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

        mAuth = FirebaseAuth.getInstance();
        int max=100; int min=0;
        Random randomNum = new Random();

        int randomID1 = randomNum.nextInt(max);
        int randomID2 = randomNum.nextInt(max);
        int randomID3 = randomNum.nextInt(max);
        int randomID4 = randomNum.nextInt(max);
        int randomID5 = randomNum.nextInt(max);

        tvID.setText(mAuth.getUid().substring(0,5)+randomID1+randomID2+randomID3+randomID4+randomID5);


        fullname = getIntent().getStringExtra("Fullname");
        address = getIntent().getStringExtra("Address");
        number = getIntent().getStringExtra("Number");
        email = getIntent().getStringExtra("Email");
        id = getIntent().getStringExtra("ID");
        uid = id.substring(0,5);
        service = getIntent().getStringExtra("Service");
        cleaning = getIntent().getStringExtra("Cleaning");
        sqm = getIntent().getStringExtra("SQM");
        person = getIntent().getStringExtra("Person");
        note = getIntent().getStringExtra("Note");
        date = getIntent().getStringExtra("Date");
        time = getIntent().getStringExtra("Time");
        price = getIntent().getStringExtra("Price");


        isGCASH = getIntent().getStringExtra("isGCASH");
        if(isGCASH.equalsIgnoreCase("YES")){
            btnProof.setVisibility(View.VISIBLE);
            payment = "GCASH";
        }else if(isGCASH.equalsIgnoreCase("NO")){
            btnProof.setVisibility(View.GONE);
            payment = "Cash";
        }

        appointment();




        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAppoointment();
            }
        });

        btnProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog builder = new Dialog(Summary.this);
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

    }
    public void appointment(){
        if(service.equalsIgnoreCase("Residential Cleaning") || service.equalsIgnoreCase("Commercial Cleaning") ||
                service.equalsIgnoreCase("Green Cleaning Services")){
            tvName.setText(fullname);
            tvAddress.setText(address);
            tvNumber.setText(number);
            tvEmail.setText(email);
            tvID.setText(id);
            tvService.setText(service);
            tvCleaning.setText(cleaning);
            tvSQM.setText(sqm);
            tvPerson.setText(person);
            tvNote.setText(note);
            tvDate.setText(date);
            tvTime.setText(time);
            tvPayment.setText(payment);
        }else if(service.equalsIgnoreCase("Post-construction Cleaning")){
            tvLCleaning.setVisibility(View.GONE);
            tvLSQM.setVisibility(View.GONE);
            tvCleaning.setVisibility(View.GONE);
            tvSQM.setVisibility(View.GONE);
            tvName.setText(fullname);
            tvAddress.setText(address);
            tvNumber.setText(number);
            tvEmail.setText(email);
            tvID.setText(id);
            tvService.setText(service);
            tvPerson.setText(person);
            tvNote.setText(note);
            tvDate.setText(date);
            tvTime.setText(time);
            tvPayment.setText(payment);
        }else if(service.equalsIgnoreCase("Upholstery")){
            tvLCleaning.setText("Services Availed: ");
            tvCleaning.setTextSize(15);
            tvCleaning.setText(cleaning);
            tvSQM.setText(sqm);
            tvName.setText(fullname);
            tvAddress.setText(address);
            tvNumber.setText(number);
            tvEmail.setText(email);
            tvID.setText(id);
            tvService.setText(service);
            tvPerson.setText(person);
            tvNote.setText(note);
            tvDate.setText(date);
            tvTime.setText(time);
            tvPayment.setText(payment);
        }else if(service.equalsIgnoreCase("Sanitation and Disinfection Services")){
            tvLCleaning.setVisibility(View.GONE);
            tvCleaning.setVisibility(View.GONE);
            tvName.setText(fullname);
            tvAddress.setText(address);
            tvNumber.setText(number);
            tvEmail.setText(email);
            tvID.setText(id);
            tvService.setText(service);
            tvSQM.setText(sqm);
            tvPerson.setText(person);
            tvNote.setText(note);
            tvDate.setText(date);
            tvTime.setText(time);
            tvPayment.setText(payment);
        }

    }
    public void submitAppoointment(){
            AppointmentDetails appointmentDetails = new AppointmentDetails(fullname,address,number,email,id,service,cleaning,sqm,person,note,date,time,currentTime.toString(),"Pending",payment);
            myRef.child(id).setValue(appointmentDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(Summary.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Summary.this, Services.class);

                        startActivity(i);

                    }else{
                        Toast.makeText(Summary.this, "Failed", Toast.LENGTH_SHORT).show();

                    }
                }
            });

        myRef2.child(uid + "/" + id).setValue(appointmentDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(Summary.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Summary.this, Services.class);

                    startActivity(i);

                }else{
                    Toast.makeText(Summary.this, "Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });

        }

        public void isGCASH(){

        }

}