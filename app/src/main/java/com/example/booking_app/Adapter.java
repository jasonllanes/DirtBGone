package com.example.booking_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<DataSummary> {

    public Adapter(Context context, ArrayList<DataSummary> values){
        super(context, R.layout.list_item,values);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        DataSummary dataSummary = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }

        TextView bookingID = convertView.findViewById(R.id.bookingID);
        TextView currenttime = convertView.findViewById(R.id.date);
        TextView name = convertView.findViewById(R.id.name);
        TextView service = convertView.findViewById(R.id.service);

        bookingID.setText(dataSummary.bookingID);
        currenttime.setText(dataSummary.currenttime);
        name.setText(dataSummary.fullname);
        service.setText(dataSummary.service);


        return super.getView(position, convertView, parent);
    }
}
