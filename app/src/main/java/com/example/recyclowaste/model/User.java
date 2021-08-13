package com.example.recyclowaste.model;

public class User {
    private String fname;
    private String lname;
    private String email;
    private String telno;

    public User(String fname, String lname, String email, String telno) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.telno = telno;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }
}
