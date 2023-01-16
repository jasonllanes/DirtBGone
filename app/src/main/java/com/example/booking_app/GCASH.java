package com.example.booking_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GCASH extends AppCompatActivity {
    private static int RESULT_LOAD_IMG = 1;
    int imageSize = 224;
    String imgDecodableString;
    Button btnProof,btnClear,btnNext,btnBack;
    ImageView ivReceipt;
    TextView tvPay;

    Bitmap image;
    byte [] byteArray;
    String id,cleaning,sqm,price;

    FirebaseStorage storage;
    StorageReference storageRef;
    private Uri filePath;

    String fullname,address,number,email,service,person,note,date,time;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcash);

        btnNext = findViewById(R.id.btnNext);
        btnClear = findViewById(R.id.btnClear);
        btnProof = findViewById(R.id.btnProof);
        btnBack = findViewById(R.id.btnBack);

        tvPay = findViewById(R.id.tvPay);


        ivReceipt = findViewById(R.id.ivReceipt);

        price = getIntent().getStringExtra("Price");


        tvPay.setText(price);

        id = getIntent().getStringExtra("ID");

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();


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


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.id.ivReceipt);
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                image.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                byte [] byteArray = stream.toByteArray();
//
//                Intent intent = new Intent(GCASH.this, Summary.class);
//                intent.putExtra("isGCASH", "YES");
//                intent.putExtra("Proof", byteArray);
//                startActivity(intent);


                StorageReference ref = storageRef.child("Proof/" + id + ".jpg");

                ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(GCASH.this, "Success", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(GCASH.this, Summary.class);
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
                                i.putExtra("isGCASH", "YES");
                                i.putExtra("Payment", "GCASH");
                                startActivity(i);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(GCASH.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });

        btnProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImagefromGallery(view);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivReceipt.setVisibility(View.GONE);
                btnNext.setVisibility(View.GONE);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(cameraIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == 3){
                filePath = data.getData();
                image = (Bitmap) data.getExtras().get("data");
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();

                ivReceipt.setImageBitmap(image);

            }else{
                Uri dat = data.getData();
                filePath = data.getData();
                 image = null;
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dat);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();
                ivReceipt.setImageBitmap(image);
                ivReceipt.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.VISIBLE);



            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



}
