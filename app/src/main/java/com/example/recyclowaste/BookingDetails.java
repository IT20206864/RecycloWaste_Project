package com.example.recyclowaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Set;

public class BookingDetails extends AppCompatActivity {
    DatabaseReference dbref;
    String key;
    String keyreq;
    int reqcode;
    int reminderCount;
    boolean reminderwasset;
    TextView driver;
    TextView type;
    TextView tv_date;
    TextView tv_time;
    TextView payment;
    TextView location;
    TextView contains;
    TextView nah;
    Switch reminder;
    Button reschedule;
    SharedPreferences sharedPreferences;
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
        reminder = findViewById(R.id.remider_switch);
        nah = findViewById(R.id.nah);

        builder = new AlertDialog.Builder(this);

        Intent i = getIntent();
        key = i.getStringExtra("key");
        keyreq = key + "reqcode";
        sharedPreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        reminder.setChecked(sharedPreferences.getBoolean(key, false));
        reqcode = sharedPreferences.getInt(keyreq, 0);
        reminderCount = sharedPreferences.getInt("ReminderCount", 0);
        reminderwasset = sharedPreferences.getBoolean((key+"wasset"), false);
        /*SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, false);
        editor.putInt(keyreq, 0);
        editor.putInt("ReminderCount", 0);
        editor.putBoolean((key + "wasset"), false);
        editor.commit();*/
        nah.setText(String.valueOf(reqcode) + ", " + String.valueOf(reminderCount));

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
                                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                                    editor.remove(key);
                                                    editor.remove(keyreq);
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

    public void setReminder (View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(reminder.isChecked()) {
            if(reminderwasset ==  false) {
                //create request code
                reqcode = reminderCount + 1;

                /*set alarm
                Intent intent = new Intent(this, AlarmReciever.class);
                intent.putExtra("notificationId", 1);
                intent.putExtra("message" , "");
                PendingIntent alarmIntent =  PendingIntent.getBroadcast(this, reqcode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.YEAR, Integer.parseInt(date[2]));
                startTime.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
                startTime.set(Calendar.DATE, Integer.parseInt(date[0]));
                startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
                startTime.set(Calendar.MINUTE, Integer.parseInt(time[1]));
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();
                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);*/

                //update shared preferences
                editor.putBoolean(key, true);
                editor.putInt(keyreq, reqcode);
                editor.putInt("ReminderCount", ++reminderCount);
                editor.putBoolean((key + "wasset"), true);
                editor.commit();

                Toast.makeText(getApplicationContext(), "Reminder is ON", Toast.LENGTH_LONG).show();
            }
            else {
                /*set alarm
                Intent intent = new Intent(this, AlarmReciever.class);
                intent.putExtra("notificationId", 1);
                intent.putExtra("message" , "");
                PendingIntent alarmIntent =  PendingIntent.getBroadcast(this, reqcode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.YEAR, Integer.parseInt(date[2]));
                startTime.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
                startTime.set(Calendar.DATE, Integer.parseInt(date[0]));
                startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
                startTime.set(Calendar.MINUTE, Integer.parseInt(time[1]));
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();
                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);*/

                //update shared preferences
                editor.putBoolean(key, true);
                editor.commit();
                Toast.makeText(getApplicationContext(), "Reminder is ON", Toast.LENGTH_LONG).show();
            }

        }
        else {
            /*cancel alarm
            Intent intent = new Intent(this, AlarmReciever.class);
            intent.putExtra("notificationId", 1);
            intent.putExtra("message" , "");
            PendingIntent alarmIntent =  PendingIntent.getBroadcast(this, reqcode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarm.cancel(alarmIntent);*/

            //update shared preferences
            editor.putBoolean(key, false);
            editor.commit();
            Toast.makeText(getApplicationContext(), "Reminder is OFF", Toast.LENGTH_LONG).show();
        }

    }


}