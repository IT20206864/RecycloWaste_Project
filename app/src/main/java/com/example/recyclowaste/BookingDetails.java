package com.example.recyclowaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookingDetails extends AppCompatActivity {
    DatabaseReference dbref;
    String key;
    TextView driver;
    TextView type;
    TextView tv_date;
    TextView tv_time;
    TextView payment;
    TextView location;
    TextView contains;
    Button reschedule;
    AlertDialog.Builder builder;
    String[] date;
    String[] time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        driver = findViewById(R.id.tv_driver);
        type = findViewById(R.id.tv_type);
        tv_date = findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);
        payment = findViewById(R.id.tv_payment);
        location = findViewById(R.id.tv_location);
        contains = findViewById(R.id.tv_contains);
        reschedule = findViewById(R.id.btnReschedule);
        builder = new AlertDialog.Builder(this);

        Intent i = getIntent();
        key = i.getStringExtra("key");
        dbref = FirebaseDatabase.getInstance().getReference().child("Booking").child("acanta69").child(key);
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.hasChildren()) {
                    driver.setText(snapshot.child("driver").getValue().toString());
                    type.setText(snapshot.child("type").getValue().toString());
                    location.setText(snapshot.child("location").child("locality").getValue().toString());
                    tv_date.setText(snapshot.child("date").getValue().toString());
                    tv_time.setText(snapshot.child("time").getValue().toString());
                    payment.setText("LKR " + snapshot.child("payment").getValue().toString());
                    contains.setText(snapshot.child("includes").getValue().toString());
                    date = snapshot.child("date").getValue().toString().split("/");
                    time = snapshot.child("time").getValue().toString().split(":");
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);
            }
        });


    }

    public void reschedule (View view) {
        builder.setMessage("Do you want to reschedule pickup?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int YEAR = Integer.parseInt(date[2]);
                        int MONTH = Integer.parseInt(date[1]) - 1;
                        int DATE = Integer.parseInt(date[0]);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(BookingDetails.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                                int HOUR = Integer.parseInt(time[0]);
                                int MINUTE = Integer.parseInt(time[1]);

                                TimePickerDialog timePickerDialog = new TimePickerDialog(BookingDetails.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                                        DatabaseReference upref  = FirebaseDatabase.getInstance().getReference().child("Booking").child("acanta69");
                                        upref.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.hasChild(key)) {
                                                    dbref = FirebaseDatabase.getInstance().getReference().child("Booking").child("acanta69").child(key);
                                                    dbref.child("date").setValue(date + "/" + (month+1) + "/" + year);
                                                    tv_date.setText(date + "/" + (month+1) + "/" + year);
                                                    if(hour < 10) {
                                                        tv_time.setText("0" + hour + ":" + min);
                                                        dbref.child("time").setValue(("0" + hour + ":" + min));
                                                    }
                                                    else {
                                                        tv_time.setText(hour + ":" + min);
                                                        dbref.child("time").setValue(hour + ":" + min);
                                                    }

                                                    Toast.makeText(getApplicationContext(), "Successfull!", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                    }
                                }, HOUR, MINUTE, true);

                                timePickerDialog.show();
                            }
                        }, YEAR, MONTH, DATE);
                        datePickerDialog.show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();

                    }
                });

        AlertDialog alert = builder.create();

        alert.setTitle("Warning");
        alert.show();

    }

    public void cancel (View view) {
        builder.setMessage("Do you want to cancel the pickup?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                                        DatabaseReference upref  = FirebaseDatabase.getInstance().getReference().child("Booking").child("acanta69");
                                        upref.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.hasChild(key)) {
                                                    dbref = FirebaseDatabase.getInstance().getReference().child("Booking").child("acanta69").child(key);
                                                    dbref.removeValue();
                                                    Toast.makeText(getApplicationContext(), "Canceled!", Toast.LENGTH_SHORT).show();
                                                    Intent mybookings = new Intent(BookingDetails.this, MyBookings.class);
                                                    startActivity(mybookings);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();

                    }
                });

        AlertDialog alert = builder.create();

        alert.setTitle("Warning");
        alert.show();
    }


}