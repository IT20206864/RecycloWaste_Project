package com.example.recyclowaste;

import androidx.appcompat.app.AppCompatActivity;
import com.example.recyclowaste.model.Review;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Reviews extends AppCompatActivity {
    Review review;
    TextView tv_tipView;
    Button bt_hundred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        review = new Review(100);
        tv_tipView = (TextView)findViewById(R.id.tv_tipView);

        //tv_tipView = findViewById(R.id.nd_customamount);

        tv_tipView.setText(review.getTip());

        Button submitButtonBooking = (Button) findViewById(R.id.bt_forbooking);
        Button submitButtonDriver = (Button) findViewById(R.id.bt_fordriver);
        Button submitButtonHundred = (Button) findViewById(R.id.bt_hundred);

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

        submitButtonHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_tipView.setText(review.getTip());
            }
        });
    }

    public void openBookUser(View view) {
        Intent intentb = new Intent(view.getContext(), reviews_bookuser.class);
        startActivity(intentb);
    }
}