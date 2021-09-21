package com.example.recyclowaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.recyclowaste.model.Booking;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BookPickup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner dropdown;
    String domestic[] = new String[]{"Plastic", "Wood", "Paper", "Organic", "Glass", "Metal"};
    String medical[] = new String[]{"Syringes", "Gloves", "Needles", "Liquids", "Bandages", "Drugs"};
    ArrayList<String> includes;
    CheckBox waste1;
    CheckBox waste2;
    CheckBox waste3;
    CheckBox waste4;
    CheckBox waste5;
    CheckBox waste6;
    Button btnDate;
    Button btnTime;
    EditText inptlocation;
    Booking booking;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_pickup);
        waste1 = (CheckBox)findViewById(R.id.waste1);
        waste2 = (CheckBox)findViewById(R.id.waste2);
        waste3 = (CheckBox)findViewById(R.id.waste3);
        waste4 = (CheckBox)findViewById(R.id.waste4);
        waste5 = (CheckBox)findViewById(R.id.waste5);
        waste6 = (CheckBox)findViewById(R.id.waste6);
        inptlocation = findViewById(R.id.inputLocation);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        includes = new ArrayList<String>();

        //get the spinner from the xml.
        dropdown = findViewById(R.id.typeSpinner);
//create a list of items for the spinner.
        String[] items = new String[]{"Domestic Waste", "Medical Waste"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);




    }

    public void clickConfirm(View view) {
        try {
            if(TextUtils.isEmpty(inptlocation.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please Give Your Location", Toast.LENGTH_SHORT).show();
            }
            else {
                String type = dropdown.getSelectedItem().toString();
                String driver = "Mr. Kusal Mendis";
                String date = btnDate.getText().toString();
                String time = btnTime.getText().toString();
                String location = inptlocation.getText().toString();

                if(waste1.isChecked()) {
                    includes.add(waste1.getText().toString());
                }
                if(waste2.isChecked()) {
                    includes.add(waste2.getText().toString());
                }
                if(waste3.isChecked()) {
                    includes.add(waste3.getText().toString());
                }
                if(waste4.isChecked()) {
                    includes.add(waste4.getText().toString());
                }
                if(waste5.isChecked()) {
                    includes.add(waste5.getText().toString());
                }
                if(waste6.isChecked()) {
                    includes.add(waste6.getText().toString());
                }

                String strIncludes = new String();

                for(String item : includes) {
                    strIncludes = strIncludes + item + ",";
                }

                booking = new Booking(driver,type, location, date, time, strIncludes);

                Intent payment = new Intent(this, BookingPayment.class);
                payment.putExtra("booking", booking);
                startActivity(payment);
            }


        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
        }

    }

    public void itemSelected(){
        if(dropdown.getSelectedItem() == "Domestic Waste"){
            waste1.setText(domestic[0]);
            waste2.setText(domestic[1]);
            waste3.setText(domestic[2]);
            waste4.setText(domestic[3]);
            waste5.setText(domestic[4]);
            waste6.setText(domestic[5]);
        }
        else if(dropdown.getSelectedItem() == "Medical Waste"){
            waste1.setText(medical[0]);
            waste2.setText(medical[1]);
            waste3.setText(medical[2]);
            waste4.setText(medical[3]);
            waste5.setText(medical[4]);
            waste6.setText(medical[5]);
        }

    }

    public void onClickLocation (View view) {


            getLocation();
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if(location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(BookPickup.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);

                        inptlocation.setText(addresses.get(0).getLocality());
                    }catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        itemSelected();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onClickDate (View view) {
        Calendar calendar =  Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                btnDate.setText(date + "/" + (month+1) + "/" + year);
            }
        }, YEAR, MONTH, DATE);
        datePickerDialog.show();
    }

    public void onClickTime(View view) {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                btnTime.setText(hour + ":" + min);
            }
        }, HOUR, MINUTE, true);

        timePickerDialog.show();
    }
}