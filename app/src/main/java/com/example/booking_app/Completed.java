package com.example.booking_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Completed extends AppCompatActivity {
    FirebaseListAdapter listAdapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private FirebaseAuth mAuth;
    ListView lvPending;

    ArrayList<String> pendingList;
    ArrayAdapter adapter;

    String id,servicee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);

        lvPending = findViewById(R.id.lvPending);


        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getUid().substring(0,5);

        Query query = FirebaseDatabase.getInstance().getReference().child("CompletedUser/" + uid);
        FirebaseListOptions<DataSummary> o = new FirebaseListOptions.Builder<DataSummary>()
                .setLayout(R.layout.list_item)
                .setQuery(query,DataSummary.class)
                .build();
        listAdapter = new FirebaseListAdapter(o) {
            @Override
            protected void populateView(View v, Object model, int position) {

                TextView bookID = v.findViewById(R.id.bookingID);
                TextView currenttime = v.findViewById(R.id.date);
                TextView name = v.findViewById(R.id.name);
                TextView service = v.findViewById(R.id.service);


                DataSummary dataSummary = (DataSummary) model;
                bookID.setText(((DataSummary) model).getBookingID());
                currenttime.setText(((DataSummary) model).getCurrenttime());
                name.setText(((DataSummary) model).getFullname());
                service.setText(((DataSummary) model).getService());

                lvPending.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TextView tvID = view.findViewById(R.id.bookingID);
                        TextView tvService = view.findViewById(R.id.service);
                        id = tvID.getText().toString();
                        servicee = tvService.getText().toString();
                        Intent intent = new Intent(Completed.this, CompletedBookDetails.class);
                        intent.putExtra("id",id);
                        intent.putExtra("service",servicee);
                        startActivity(intent);

                    }
                });

            }
        };
        lvPending.setAdapter(listAdapter);




    }

    @Override
    protected void onStart() {
        super.onStart();
        listAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        listAdapter.stopListening();
    }

}