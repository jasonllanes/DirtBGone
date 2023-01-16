package com.example.booking_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(service.equalsIgnoreCase("Residential")){
            tvWhatService.setText("You need to know about Residential Cleaning: ");
            tvDetails.setText("Deep Cleaning Prices\nBelow 50 SQM – 4,200.00\n" +
                    "50 – 99 SQM – 85/SQM \n" +
                    "100 – 199 SQM – 80/SQM\n" +
                    "200 – 299 SQM – 75/SQM\n" +
                    "300 – 499 SQM – 70/SQM\n" +
                    "500 – 599 SQM – 65/SQM\n" +
                    "600 – 999 SQM – 60/SQM \n\n" +
                    "Surface Cleaning Prices\nBelow 50 SQM – 4,200.00\n" +
                    "50 – 99 SQM – 85/SQM\n" +
                    "100 – 199 SQM – 80/SQM\n" +
                    "200 – 299 SQM – 75/SQM\n" +
                    "300 – 499 SQM – 70/SQM\n" +
                    "500 – 599 SQM – 65/SQM\n" +
                    "600 – 999 SQM – 60/SQ");
        }else if(service.equalsIgnoreCase("Commercial")){
            tvWhatService.setText("You need to know about Commercial Cleaning: ");
            tvDetails.setText("Deep Cleaning Prices\nBelow 50 SQM – 4,200.00\n" +
                    "50 – 99 SQM – 85/SQM \n" +
                    "100 – 199 SQM – 80/SQM\n" +
                    "200 – 299 SQM – 75/SQM\n" +
                    "300 – 499 SQM – 70/SQM\n" +
                    "500 – 599 SQM – 65/SQM\n" +
                    "600 – 999 SQM – 60/SQM \n\n" +
                    "Surface Cleaning Prices\nBelow 50 SQM – 4,200.00\n" +
                    "50 – 99 SQM – 85/SQM\n" +
                    "100 – 199 SQM – 80/SQM\n" +
                    "200 – 299 SQM – 75/SQM\n" +
                    "300 – 499 SQM – 70/SQM\n" +
                    "500 – 599 SQM – 65/SQM\n" +
                    "600 – 999 SQM – 60/SQ");
        }else if(service.equalsIgnoreCase("Post-Construction")){
            tvWhatService.setText("You need to know about Post-Construction Cleaning: ");
            tvDetails.setText("8 hours cleaning\n" +
                    "Removal of dust after construction\n" +
                    "Cleaning of glass on windows, doors etc.\n" +
                    "Removal of excess paint\n" +
                    "Minimize paint odor\n" +
                    "Notes:\n" +
                    "1. Actual price may vary depending on the condition of the house/unit to be cleaned.\n" +
                    "2. Before booking, we require client to coordinate with us via Facebook/SMS/Call so we can provide accurate quotation.\n");
        }else if(service.equalsIgnoreCase("Green")){
            tvWhatService.setText("You need to know about Green Cleaning Services: ");
            tvDetails.setText("Below 50 SQM – 4,500.00\n" +
                    "50 – 99 SQM – 90/SQM \n" +
                    "100 – 199 SQM – 85/SQM\n" +
                    "200 – 299 SQM – 80/SQM\n" +
                    "300 – 499 SQM – 75/SQM\n" +
                    "500 – 599 SQM – 70/SQM\n" +
                    "600 – 999 SQM – 65/SQM \n\n");
        }else if(service.equalsIgnoreCase("Sanitation and Disinfection")){
            tvWhatService.setText("You need to know about Sanitation and Disinfection Services: ");
            tvDetails.setText("-All rooms and areas including common rooms, hallway, restrooms\n" +
                    "-All surfaces (Tables / Chairs / Walls / Floors / High Ceiling /Handrails)\n" +
                    "-A/C unit coils\n" +
                    "-Fabric cushions, Office chairs, Curtains, Panels, Sofas, etc\n" +
                    "-Office furniture and fixtures\n" +
                    "-All “Hot spot” areas (doorknobs, elevator, etc.)\n" +
                    "\n" +
                    "0–1,000 sq. ft.\t5,000\n" +
                    "1,000–2,000 sq. ft.\t5,900\n" +
                    "2,000–3,000 sq. ft.\t6,700\n" +
                    "3,000–4,000 sq. ft.\t7,400\n" +
                    "4,000–5,000 sq. ft.\t8,500\n" +
                    "5,000–6,000 sq. ft.\t9,100\n" +
                    "6,000–7,000 sq. ft.\t9,800\n" +
                    "7,000–8,000 sq. ft.\t10,700\n" +
                    "8,000–9,000 sq. ft.\t11,400\n" +
                    "9,000–10,000 sq. ft. 12,000\n\n");
        }else if(service.equalsIgnoreCase("Upholstery Appointment")){
            tvWhatService.setText("You need to know about Upholstery: ");
            tvDetails.setText("SOFAS\n" +
                    "*1-Seater: Solo Couch, La-Z Boy, Single Recliner, Executive Chair(P1,000)\n" +
                    "*2-Seater: Love Seat, Chaise Lounge, Double Recliner(P 1,250)\n" +
                    "*3-Seater: Triple Seater Couch(P 1,500)\n" +
                    "*4-Seater: Four-Seater Couch(P 1,750)\n" +
                    "*5-Seater: Five-Seater Couch(P 2,000)\n" +
                    "*6-Seater: Six-Seater Couch(P 2,250)\n" +
                    "\n" +
                    "MATTRESSES\n" +
                    "*Single: Single Bed (36\" X 75\")(P 1,000)\n" +
                    "*Double: Standard Twin (38\" X 75\") / Full Size (54\" X 75\")(P 1,500)\n" +
                    "*Queen: Standard Queen Size (60\" X 80\") / California Queen Size (60\" X 80\")(P2,000)\n" +
                    "*King: Standard King Size (78\" X 80\") / Calfornia King Size (72\" X 84\")(P2,500)\n" +
                    "*Sofa Bed: Sofa Bed(P 2,500)\n" +
                    "\n" +
                    "CHAIRS\n" +
                    "*Chair: Dining Chair, Computer Chair, Office Chair, Bar Stool(P 200)\n" +
                    "\n" +
                    "FLOOR RUGS\n" +
                    "*Floor Rug: Floor Rugs (Not Carpet, Tiles/Flooring)(P 500)\n" +
                    "\n" +
                    "CARPET SHAMPOOING\n" +
                    "*Minimum of 100 SQM: P 50/SQM(P 5,000)\n\n");
        }

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(service.equalsIgnoreCase("Residential")){
                    Intent i = new Intent(Details.this,Appointment.class);
                    i.putExtra("Service", "Residential Cleaning");
                    startActivity(i);
                }
                else if(service.equalsIgnoreCase("Commercial")){
                    Intent i = new Intent(Details.this,Appointment.class);
                    i.putExtra("Service", "Commercial Cleaning");
                    startActivity(i);
                }
                else if(service.equalsIgnoreCase("Post-Construction")){
                    Intent i = new Intent(Details.this,PostConstructionAppointment.class);
                    i.putExtra("Service", "Post-Construction Cleaning");
                    startActivity(i);
                }
                else if(service.equalsIgnoreCase("Green")){
                    Intent i = new Intent(Details.this,Appointment.class);
                    i.putExtra("Service", "Green Cleaning Services");
                    startActivity(i);
                }
                else if(service.equalsIgnoreCase("Sanitation and Disinfection")){
                    Intent i = new Intent(Details.this,SanitationandDisinfectionAppointment.class);
                    i.putExtra("Service", "Sanitation and Disinfection Services");
                    startActivity(i);
                }
                else if(service.equalsIgnoreCase("Upholstery Appointment")){
                    Intent i = new Intent(Details.this,Upholstery_Appointment.class);
                    i.putExtra("Service", "Upholstery");
                    startActivity(i);
                }
            }
        });

    }
}