package com.example.booking_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    Button btnBack,btnBook;
    TextView tvWhatService,tvDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        btnBack = findViewById(R.id.btnBack);
        btnBook = findViewById(R.id.btnBook);

        tvWhatService = findViewById(R.id.tvWhatService);
        tvDetails = findViewById(R.id.tvDetails);

        String service = getIntent().getStringExtra("Service");

        if(service == "Post-Construction"){
            tvWhatService.setText("What is Post-Construction Cleaning? ");
            tvDetails.setText("8 hours cleaning\n" +
                    "Removal of dust after construction\n" +
                    "Cleaning of glass on windows, doors etc.\n" +
                    "Removal of excess paint\n" +
                    "Minimize paint odor\n" +
                    "Notes:\n" +
                    "1. Actual price may vary depending on the condition of the house/unit to be cleaned.\n" +
                    "2. Before booking, we require client to coordinate with us via Facebook/SMS/Call so we can provide accurate quotation.\n");
        }

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}