package com.example.recyclowaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recyclowaste.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText fname,username,email,telNo,pass,cpass;
    Button submit;
    DatabaseReference dbRef;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fname = findViewById(R.id.etFname);
        username = findViewById(R.id.etUsername);
        email = findViewById(R.id.etEmail);
        telNo = findViewById(R.id.etTelno);
        pass = findViewById(R.id.etPwd);
        cpass = findViewById(R.id.etCnfrmPwd);
        submit = findViewById(R.id.SignUp_btn);
        user = new User();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = username.getText().toString();
                dbRef = FirebaseDatabase.getInstance().getReference().child("User");
               
                validations(uName);
                
                if(pass.getText().toString() != cpass.getText().toString()){
                    Toast.makeText(getApplicationContext(), "Passwords not matching!", Toast.LENGTH_SHORT).show();
                    pass.setText("");
                    cpass.setText("");
                    validations(uName);
                }
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(uName)){
                            Toast.makeText(getApplicationContext(), "Username Already Exists!", Toast.LENGTH_SHORT).show();
                            username.setText("");
                        }
                        else{
                            user.setUsername(uName.trim());
                            user.setEmail(email.getText().toString().trim());
                            user.setFname(fname.getText().toString().trim());
                            user.setPassword(pass.getText().toString().trim());
                            user.setTelno(telNo.getText().toString().trim());
                            
                            dbRef.push().setValue(user);

                            Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                            


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    
    public void validations(String uName){
        if(TextUtils.isEmpty(fname.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(uName))
            Toast.makeText(getApplicationContext(), "Please enter a username", Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(email.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(telNo.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter your Telephone Number", Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(pass.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(cpass.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please confirm pass", Toast.LENGTH_SHORT).show();
    }



}