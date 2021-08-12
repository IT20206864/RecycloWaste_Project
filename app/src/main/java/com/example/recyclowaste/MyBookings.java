package com.example.recyclowaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclowaste.model.Booking;

import java.util.ArrayList;

public class MyBookings extends AppCompatActivity {
    RecyclerView myBookingsView;
    Adapter adapter;
    ArrayList<Booking> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);
        myBookingsView = findViewById(R.id.myBookingsView);
        Booking booking1 = new Booking("Kusal","Domestic Waste", "Malabe Town", "08-08-2021","08:30AM");
        Booking booking2 = new Booking("Ramesh","Medical Waste", "Matale Town", "08-07-2021","09:30AM");
        Booking booking3 = new Booking("Niroshan","Electronic Waste", "Kandy Town", "08-06-2021","10:30AM");
        Booking booking4 = new Booking("Kusal","Domestic Waste", "Malabe Town", "08-08-2021","08:30AM");
        Booking booking5 = new Booking("Ramesh","Medical Waste", "Matale Town", "08-07-2021","09:30AM");
        Booking booking6 = new Booking("Niroshan","Electronic Waste", "Kandy Town", "08-06-2021","10:30AM");

list = new ArrayList<>();
        list.add(booking1);
        list.add(booking2);
        list.add(booking3);
        list.add(booking4);
        list.add(booking5);
        list.add(booking6);

        myBookingsView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, list);
        myBookingsView.setAdapter(adapter);
    }
}