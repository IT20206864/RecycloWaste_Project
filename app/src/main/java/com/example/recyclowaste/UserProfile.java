package com.example.recyclowaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.recyclowaste.model.User;

public class UserProfile extends AppCompatActivity {
    User user;
    TextView tv_name;
    TextView userEmail;
    TextView userTelno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        user = new User("Sahan", "Perera", "sahanp@gmail.com","+94773554123","pass");
        tv_name = findViewById(R.id.tv_name);
        userEmail = findViewById(R.id.userEmail);
        userTelno = findViewById(R.id.userTelno);

     //   tv_name.setText(user.getFname() + " " + user.getLname());
        userEmail.setText(user.getEmail());
        userTelno.setText(user.getTelno());
    }

    public void openEditProfile(View view){
        Intent editProfile = new Intent(this, EditProfile.class);
        editProfile.putExtra("fname", user.getFname());
   //     editProfile.putExtra("lname", user.getLname());
        editProfile.putExtra("email", user.getEmail());
        editProfile.putExtra("telno", user.getTelno());
        startActivity(editProfile);
    }
}