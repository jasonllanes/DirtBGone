package com.example.booking_app;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentDetails {



    public String fullname,address,number,email,bookingID,service,clean,sqm,person,note,date,time;

    public AppointmentDetails() { }

    public AppointmentDetails(String fullname, String address, String number, String email, String bookingID, String service, String clean, String sqm, String person, String note, String date, String time) {
        this.fullname = fullname;
        this.address = address;
        this.number = number;
        this.email = email;
        this.bookingID = bookingID;
        this.service = service;
        this.clean = clean;
        this.sqm = sqm;
        this.person = person;
        this.note = note;
        this.date = date;
        this.time = time;
    }
}