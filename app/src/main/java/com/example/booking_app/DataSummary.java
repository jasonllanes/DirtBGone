package com.example.booking_app;

public class DataSummary {

    public String bookingID;
    public String currenttime;
    public String fullname;
    public String service;

    public DataSummary(){

    }


    public DataSummary(String bookingID, String currenttime, String fullname, String service) {
        this.bookingID = bookingID;
        this.currenttime = currenttime;
        this.fullname = fullname;
        this.service = service;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getCurrenttime() {
        return currenttime;
    }

    public void setCurrenttime(String currenttime) {
        this.currenttime = currenttime;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

}
