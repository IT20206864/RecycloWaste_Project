package com.example.recyclowaste;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBookPickup(View view) {
        Intent bookPickup = new Intent(this, BookPickup.class);

        startActivity(bookPickup);
        /*Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.payment_dialog_layout);
        Button btnPay = dialog.findViewById(R.id.btn_pay);
        EditText cardNo = dialog.findViewById(R.id.et_card_number);
        EditText expiry = dialog.findViewById(R.id.et_expiry);
        EditText code = dialog.findViewById(R.id.et_code);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), cardNo.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();*/

    }
    public void openMyBookings(View view) {
        Intent myBookings = new Intent(this, MyBookings.class);

        startActivity(myBookings);

    }

    public void openUserProfile(View view) {
        Intent userProfile = new Intent(this, UserProfile.class);

        startActivity(userProfile);
    }

    public void openSignUp(View view) {
        Intent signUp = new Intent(this, SignUp.class);

        startActivity(signUp);
    }

    public void openHomeMarketplace(View view) {
            Intent homeMarketplace = new Intent(this, MyAds.class);

        startActivity(homeMarketplace);
    }
}