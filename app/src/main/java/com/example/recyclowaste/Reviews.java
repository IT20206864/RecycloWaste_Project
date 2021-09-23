package com.example.recyclowaste;

import androidx.appcompat.app.AppCompatActivity;
import com.example.recyclowaste.model.Review;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Reviews extends AppCompatActivity {
    Review review;
    TextView tv_tipView;
    TextView nd_customamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        review = new Review(100);
        tv_tipView = (TextView)findViewById(R.id.tv_tipView);
        nd_customamount = (EditText) findViewById(R.id.nd_customamount);
        tv_tipView.setText(review.getTip());

        Button submitButtonBooking = (Button) findViewById(R.id.bt_forbooking);
        Button submitButtonDriver = (Button) findViewById(R.id.bt_fordriver);
        Button submitButtonHundred = (Button) findViewById(R.id.bt_hundred);
        Button submitButtonFifty = (Button) findViewById(R.id.bt_fifty);
        Button submitButtonFivehundred = (Button) findViewById(R.id.bt_fhundred);
        Button submitForm = (Button) findViewById(R.id.bt_done);

        submitButtonHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tip = nd_customamount.getText().toString();
                int currenttip = 100;
                tv_tipView.setText(currenttip+tip);
            }
        });

        submitButtonFifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tip = nd_customamount.getText().toString();
                int currenttip = 50;
                tv_tipView.setText(currenttip+tip);
            }
        });

        submitButtonFivehundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tip = nd_customamount.getText().toString();
                int currenttip = 500;
                tv_tipView.setText(currenttip+tip);
            }
        });

        submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get values from texteditor to textviewer
                String tip = nd_customamount.getText().toString();
                tv_tipView.setText("Tip :"+tip);
            }
        });

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