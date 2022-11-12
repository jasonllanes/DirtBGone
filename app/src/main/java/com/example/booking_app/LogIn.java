package com.example.booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    EditText etEmail,etPassword;
    TextView tvSignUp;
    Button btnLogIn;
    ProgressBar pbLoading;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth = FirebaseAuth.getInstance();

        pbLoading = findViewById(R.id.pbLoading);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        tvSignUp = findViewById(R.id.tvSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent i = new Intent(LogIn.this, Services.class);
            startActivity(i);
        }

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent i = new Intent(LogIn.this, SignUp.class);
                startActivity(i);
            }
        });


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbLoading.setVisibility(View.VISIBLE);
                if(etEmail.getText().toString().isEmpty()){
                    etEmail.setError("Please input your email.");
                    pbLoading.setVisibility(View.GONE);
                }else if(etPassword.getText().toString().isEmpty()){
                    etPassword.setError("Please input your password.");
                    pbLoading.setVisibility(View.GONE);
                }else{
                    mAuth.signInWithEmailAndPassword(etEmail.getText().toString(),etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                pbLoading.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Log In successful!", Toast.LENGTH_LONG).show();

                                // hide the progress bar
//                                pbLoading.setVisibility(View.GONE);
                                // if the user created intent to login activity
                                Intent i = new Intent(LogIn.this, Services.class);
                                startActivity(i);
                            }
                            else {
                                pbLoading.setVisibility(View.GONE);
                                // Registration failed
                                Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();
                                // hide the progress bar

                            }
                        }
                    });
                }

            }
        });

    }
}