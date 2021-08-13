package com.example.recyclowaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBookPickup(View view) {
        Intent bookPickup = new Intent(this, BookPickup.class);

        startActivity(bookPickup);

    }
    public void openMyBookings(View view) {
        Intent myBookings = new Intent(this, MyBookings.class);

        startActivity(myBookings);

    }

    public void openUserProfile(View view) {
        Intent userProfile = new Intent(this, UserProfile.class);

        startActivity(userProfile);
    }
}