package com.example.recyclowaste.model;

public class ReviewTwo {
    private static String rev;

    public ReviewTwo(String rev) {
        this.rev = rev;
    }

    public  static String getRev() {return rev;}

    public void setRev(String rev) {this.rev = rev;}
}
