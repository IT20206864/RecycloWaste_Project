package com.example.recyclowaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclowaste.model.Booking;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookingDetails extends AppCompatActivity {
    DatabaseReference dbref;
    TextView driver;
    TextView type;
    TextView datetime;
    TextView location;
    TextView contains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        driver = findViewById(R.id.tv_driver);
        type = findViewById(R.id.tv_type);
        datetime = findViewById(R.id.tv_location2);
        location = findViewById(R.id.tv_location);
        contains = findViewById(R.id.tv_contains);
        dbref = FirebaseDatabase.getInstance().getReference().child("Booking").child("1");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.hasChildren()) {
                    driver.setText(snapshot.child("driver").getValue().toString());
                    type.setText(snapshot.child("type").getValue().toString());
                    location.setText(snapshot.child("location").getValue().toString());
                    datetime.setText(snapshot.child("date").getValue().toString() + " " + snapshot.child("time").getValue().toString());
                    contains.setText(snapshot.child("includes").getValue().toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);
            }
        });
    }


}