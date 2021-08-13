package com.example.recyclowaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class EditProfile extends AppCompatActivity {
    EditText fname;
    EditText lname;
    EditText email;
    EditText telno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Intent intent = getIntent();

        fname = findViewById(R.id.inptFname);
        lname = findViewById(R.id.inptLname);
        email = findViewById(R.id.inptEmail);
        telno = findViewById(R.id.inptTelno);

        fname.setText(intent.getStringExtra("fname"));
        lname.setText(intent.getStringExtra("lname"));
        email.setText(intent.getStringExtra("email"));
        telno.setText(intent.getStringExtra("telno"));

    }
}