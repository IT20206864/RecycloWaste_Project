package com.example.recyclowaste.model;

public class ReviewTwo {
    private static String rev, purl;


    public ReviewTwo(String rev, String purl) {
        this.rev = rev;
        this.purl = purl;
    }

    public  static String getRev() {return rev;}

    public void setRev(String rev) {this.rev = rev;}

    public static String getPurl() {return purl;}

    public void setPurl(String purl) {this.purl = purl;}
}
