package com.example.recyclowaste.model;

public class Booking {
    private String driver;
    private String type;
    private String location;
    private String date;
    private String time;

    public Booking(String driver, String type, String location, String date, String time) {
        this.driver = driver;
        this.type = type;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
