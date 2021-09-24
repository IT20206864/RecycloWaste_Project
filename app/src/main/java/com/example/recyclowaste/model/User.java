package com.example.recyclowaste.model;

import java.io.Serializable;

public class User implements Serializable {
    private String fname;
    private String lname;
    private String email;
    private String telno;
    private String username;
    private String password;
    private String type;

    public User(String fname, String lname, String email, String telno, String username, String password, String type) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.telno = telno;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
