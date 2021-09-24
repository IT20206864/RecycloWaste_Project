package com.example.recyclowaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclowaste.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    User user;
    TextView tv_name;
    TextView userEmail;
    TextView userTelno;
    DatabaseReference dbref;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        tv_name = findViewById(R.id.tv_name);
        userEmail = findViewById(R.id.userEmail);
        userTelno = findViewById(R.id.userTelno);

        username = "acanta69";

        Loader loader = new Loader(this);

        loader.showLoadingDialog();
        dbref = FirebaseDatabase.getInstance().getReference().child("User").child(username);
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if(snapshot.hasChildren()) {
                    user = new  User(snapshot.child("fname").getValue().toString(), snapshot.child("lname").getValue().toString(),
                            snapshot.child("email").getValue().toString(),snapshot.child("telno").getValue().toString(),
                            snapshot.child("username").getValue().toString(), snapshot.child("password").getValue().toString(), snapshot.child("type").getValue().toString());
                    tv_name.setText(user.getFname() + " " + user.getLname());
                    userEmail.setText(user.getEmail());
                    userTelno.setText(user.getTelno());
                }
                loader.dismissLoadingDialog();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);
            }
        });
    }

    public void openEditProfile(View view){
        Intent editProfile = new Intent(this, EditProfile.class);
        editProfile.putExtra("user", user);
        startActivity(editProfile);
    }
}