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
    TextView tv_tipView;
    private EditText nd_customamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        addTip = findViewById(R.id.bt_done);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Tips");

        tv_tipView = (TextView)findViewById(R.id.tv_tipView);
        nd_customamount = (EditText) findViewById(R.id.nd_customamount);
        tv_tipView.setText(Review.getTip());

        Button submitButtonBooking = (Button) findViewById(R.id.bt_forbooking);
        Button submitButtonDriver = (Button) findViewById(R.id.bt_fordriver);
        Button submitButtonHundred = (Button) findViewById(R.id.bt_hundred);
        Button submitButtonFifty = (Button) findViewById(R.id.bt_fifty);
        Button submitButtonFivehundred = (Button) findViewById(R.id.bt_fhundred);
        Button submitForm = (Button) findViewById(R.id.bt_done);

        /*submitButtonHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tip = nd_customamount.getText().toString();
                int currenttip = 100;
                tv_tipView.setText("Tip :"+currenttip+tip);
                courseID = cID + 1;
            }
        });

        submitButtonFifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tip = nd_customamount.getText().toString();
                int currenttip = 50;
                tv_tipView.setText("Tip :"+currenttip+tip);
                courseID = cID + 1;
            }
        });

        submitButtonFivehundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tip = nd_customamount.getText().toString();
                int currenttip = 500;
                tv_tipView.setText("Tip :"+currenttip+tip);
                courseID = cID + 1;
            }
        });

        submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get values from texteditor to textviewer
                String tip = nd_customamount.getText().toString();
                tv_tipView.setText("Tip :"+tip);
                courseID = cID + 1;
                Review review = new Review(tip, courseID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(String.valueOf(courseID)).setValue(review);
                        Toast.makeText(Reviews.this,"Tip Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Reviews.this, Review.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Reviews.this, "Error is"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/

//////////////////////////////////////////////////////////////////////////////////////////////////////////

        // perform click event on button booking
        submitButtonBooking.setOnClickListener(new View.OnClickListener() {
            /** Called booking page when the user touches the button */
            @Override
            public void onClick(View view) {
                Intent intentb = new Intent(view.getContext(), reviews_bookuser.class);
                startActivity(intentb);
            }
        });

        // perform click event on button driver
        submitButtonDriver.setOnClickListener(new View.OnClickListener() {
            /** Called driver page when the user touches the button */
            @Override
            public void onClick(View view) {
                Intent intentd = new Intent(view.getContext(), Reviews_Driver.class);
                startActivity(intentd);
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    public void openBookUser(View view) {
        Intent intentb = new Intent(view.getContext(), reviews_bookuser.class);
        startActivity(intentb);
    }
}