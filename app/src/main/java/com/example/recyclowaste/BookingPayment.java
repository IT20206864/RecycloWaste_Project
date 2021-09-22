package com.example.recyclowaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclowaste.model.Booking;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookingPayment extends AppCompatActivity {
    DatabaseReference dbref;
    Booking booking;
    TextView payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_payment);
        payment = findViewById(R.id.payment);
        Intent i = getIntent();

        booking = (Booking)i.getSerializableExtra("booking");
        payment.setText(String.valueOf("LKR " + booking.getPayment() + "0"));

    }

    public void clickConfirm(View view) {
        dbref = FirebaseDatabase.getInstance().getReference().child("Booking");
        try{
            dbref.push().setValue(booking);
            /*Intent details = new Intent(this, BookingDetails.class);
            startActivity(details);*/
            Intent success = new Intent(this, Successful.class);
            startActivity(success);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}