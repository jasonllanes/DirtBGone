package com.example.booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    EditText etFullname,etEmail,etNumber,etAddress,etPassword,etRePassword;
    Button btnBack,btnSignUp;
    ProgressBar pbLoading;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        
        DatabaseReference myRef = database.getReference("Users");
        mAuth = FirebaseAuth.getInstance();

        etFullname = findViewById(R.id.etFullname);
        etEmail = findViewById(R.id.etEmail);
        etNumber = findViewById(R.id.etNumber);
        etAddress = findViewById(R.id.etAddress);
        etPassword = findViewById(R.id.etPassword);
//        etRePassword = findViewById(R.id.etRePassword);

        btnBack = findViewById(R.id.btnBack);
        btnSignUp = findViewById(R.id.btnSignUp);

        pbLoading = findViewById(R.id.pbLoading);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbLoading.setVisibility(View.VISIBLE);
                String password = etPassword.getText().toString().trim();

                if(etFullname.getText().toString().isEmpty()){
                    etFullname.setError("Please fill up your fullname.");
                    pbLoading.setVisibility(View.GONE);
                }else if(etEmail.getText().toString().isEmpty()){
                    etEmail.setError("Please fill up your email.");
                    pbLoading.setVisibility(View.GONE);
                }else if(etNumber.getText().toString().isEmpty()){
                    etNumber.setError("Please fill up your number.");
                    pbLoading.setVisibility(View.GONE);
                }else if(etAddress.getText().toString().isEmpty()){
                    etAddress.setError("Please fill up your full address.");
                    pbLoading.setVisibility(View.GONE);
                }else if(etPassword.getText().toString().isEmpty()){
                    etPassword.setError("Please fill up your password.");
                    pbLoading.setVisibility(View.GONE);
                }else if(etPassword.getText().toString().length() < 6){
                    etPassword.setError("Password should  be atleast 6 characters.");
                    pbLoading.setVisibility(View.GONE);
                }else{
                    mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                                // hide the progress bar

                                User user = new User(etFullname.getText().toString(),etEmail.getText().toString(),etNumber.getText().toString(),etAddress.getText().toString(),etPassword.getText().toString());
                                myRef.child(mAuth.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            pbLoading.setVisibility(View.GONE);
                                            // if the user created intent to login activity
                                            Intent i = new Intent(SignUp.this, LogIn.class);
                                            i.putExtra("Fullname", etFullname.getText().toString());
                                            i.putExtra("Email",etEmail.getText().toString());
                                            i.putExtra("Number",etNumber.getText().toString());
                                            i.putExtra("Address",etAddress.getText().toString());
                                            startActivity(i);
                                        }else{
                                            // Registration failed
                                            Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();
                                            // hide the progress bar
                                            pbLoading.setVisibility(View.GONE);
                                        }
                                    }
                                });
                            }
                            else {
                                // Registration failed
                                Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();
                                // hide the progress bar
                                pbLoading.setVisibility(View.GONE);
                            }
                        }
                    });


                }



            }
        });

    }
}