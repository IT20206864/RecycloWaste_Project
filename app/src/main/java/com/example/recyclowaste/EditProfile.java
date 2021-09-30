/*
package com.example.recyclowaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recyclowaste.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class EditProfile extends AppCompatActivity {
    EditText fname;
    EditText email;
    EditText telno;
    User user;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Intent intent = getIntent();

        fname = findViewById(R.id.inptFname);
        email = findViewById(R.id.inptEmail);
        telno = findViewById(R.id.inptTelno);

        user = (User) intent.getSerializableExtra("user");

        fname.setText(user.getFname());
        email.setText(user.getEmail());
        telno.setText(user.getTelno());

    }

    public void onSave(View view) {
        if(!TextUtils.isEmpty(fname.getText()) && !TextUtils.isEmpty(email.getText())
        && !TextUtils.isEmpty(telno.getText())){
            user.setFname(fname.getText().toString());
            user.setEmail(email.getText().toString());
            user.setTelno(telno.getText().toString());

            DatabaseReference dbref  = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUsername());
            dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChildren()){
                        dbref.child("fname").setValue(user.getFname());
                        dbref.child("email").setValue(user.getEmail());
                        dbref.child("telno").setValue(user.getTelno());

                        Toast.makeText(getApplicationContext(), "Successfull!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "Please give all the details!", Toast.LENGTH_SHORT).show();
        }

    }
}*/
