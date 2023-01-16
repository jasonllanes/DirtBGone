package com.example.booking_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class Pending extends AppCompatActivity {

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
        setContentView(R.layout.activity_pending);

        lvPending = findViewById(R.id.lvPending);


        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getUid().substring(0,5);

        Query query = FirebaseDatabase.getInstance().getReference().child("PendingUser/" + uid);
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
                        Intent intent = new Intent(Pending.this, BookDetailsPreview.class);
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

    public void RetrievePending(){





//        pendingList = new ArrayList<>();
//        adapter = new ArrayAdapter<String>(this, R.layout.list_item, pendingList);
//        lvPending.setAdapter(adapter);
//
//        String[] bookingID;
//
//        mAuth = FirebaseAuth.getInstance();
//        String uid = mAuth.getUid().substring(0,5);
//        DatabaseReference data = database.getReference( "Pending/" + uid + "/");
//
//        data.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                pendingList.clear();
//                for(DataSnapshot snapshot1 : snapshot.getChildren()){
//                    DataSummary summary = snapshot1.getValue(DataSummary.class);
//
//                    String details = summary.getBookingID() + "\n" + summary.getFullname();
//                    pendingList.add(details);
//                }
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//
//        data.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                pendingList.clear();
//                for(DataSnapshot snapshot1 : snapshot.getChildren()){
//                    DataSummary summary = snapshot1.getValue(DataSummary.class);
//                    String details = summary.getBookingID() + "/n" + summary.getName();
//                    pendingList.add(details);
//                }
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                String value = snapshot.getValue(String.class);
//                String key = snapshot.getKey();
//                int index = adapter.getPosition(key);
//                adapter.remove(key);
//                adapter.insert(value, index);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                pendingList.remove(snapshot.getValue(String.class));
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                String value = snapshot.getValue(String.class);
//                String key = snapshot.getKey();
//                int index = adapter.getPosition(key);
//                adapter.remove(key);
//                adapter.insert(value, index);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.w("TAG", "Failed to read value.", error.toException());
//            }
//
//        });
//


    }
}