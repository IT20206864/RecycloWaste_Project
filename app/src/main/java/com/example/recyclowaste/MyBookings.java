package com.example.recyclowaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.recyclowaste.model.Booking;
import com.example.recyclowaste.model.UserLocation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyBookings extends AppCompatActivity {
    RecyclerView myBookingsView;
    Adapter adapter;
    ArrayList<Booking> list;
    ArrayList<String> keys;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        myBookingsView = findViewById(R.id.myBookingsView);
        myBookingsView.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<>();
        keys = new ArrayList<>();
        dbref = FirebaseDatabase.getInstance().getReference().child("Booking");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()) {
                    for(DataSnapshot snap : snapshot.getChildren()) {
                        UserLocation location = new UserLocation(snap.child("location").child("locality").getValue().toString(),
                                Double.parseDouble(snap.child("location").child("latitude").getValue().toString()), Double.parseDouble(snap.child("location").child("longitude").getValue().toString()));
                        list.add(new Booking(snap.child("driver").getValue().toString(), snap.child("type").getValue().toString(), location
                                , snap.child("date").getValue().toString(), snap.child("time").getValue().toString(), snap.child("includes").getValue().toString()));

                        keys.add(snap.getKey().toString());
                    }

                    adapter = new Adapter(MyBookings.this, list, keys);
                    myBookingsView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);
            }
        });


    }
}