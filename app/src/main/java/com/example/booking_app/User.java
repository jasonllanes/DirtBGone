package com.example.booking_app;

public class User {

    public String fullname,email,number,address,password;

    public User() { }

    public User(String fullname, String email,String number,String address,String password) {
       this.fullname = fullname;
       this.email = email;
       this.number = number;
       this.address = address;
       this.password = password;
    }
}
