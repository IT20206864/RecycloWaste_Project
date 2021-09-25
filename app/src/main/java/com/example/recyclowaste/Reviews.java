package com.example.recyclowaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.recyclowaste.model.Review;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reviews extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Button addTip;
    private int cID = 0;
    private int courseID = 0;

    Review review;
    TextView tipView;
    EditText customamount;
    Button hundred, fifty, fivehundred, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        //review = new Review(100);
        tipView = (TextView) findViewById(R.id.tv_tipView);
        customamount = (EditText) findViewById(R.id.nd_customamount);
        hundred = (Button) findViewById(R.id.bt_hundred);
        fifty = (Button) findViewById(R.id.bt_fifty);
        fivehundred = (Button) findViewById(R.id.bt_fhundred);
        add = (Button) findViewById(R.id.bt_addpay);
        //tv_tipView.setText(review.getTip());

        Button submitButtonBooking = (Button) findViewById(R.id.bt_forbooking);
        Button submitButtonDriver = (Button) findViewById(R.id.bt_fordriver);

        hundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tip = customamount.getText().toString();
                String currenttip = 100+tip;
                tipView.setText(currenttip);
            }
        });

        fifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tip = customamount.getText().toString();
                String currenttip = 50+tip;
                tipView.setText(currenttip);
            }
        });

        fivehundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tip = customamount.getText().toString();
                String currenttip = 500+tip;
                tipView.setText(currenttip);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get values from texteditor to textviewer
                String tip = customamount.getText().toString();
                String currenttip = tip;
                tipView.setText(currenttip);
            }
        });


        // perform click event on button booking
        submitButtonBooking.setOnClickListener(new View.OnClickListener() {
            /** Called booking page when the user touches the button */
            @Override
            public void onClick(View view) {
                openBookUser();
            }
        });

        // perform click event on button driver
        submitButtonDriver.setOnClickListener(new View.OnClickListener() {
            /** Called driver page when the user touches the button */
            @Override
            public void onClick(View view) {
                openDriver();
            }
        });

    }

    private void openBookUser() {
        Intent intentb = new Intent(this, reviews_bookuser.class);
        startActivity(intentb);
    }

    private void openDriver() {
        Intent intentd = new Intent(this, Reviews_Driver.class);
        startActivity(intentd);
    }


}
