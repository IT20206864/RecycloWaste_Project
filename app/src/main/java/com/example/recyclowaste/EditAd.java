package com.example.recyclowaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.example.recyclowaste.model.Advertisment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class EditAd extends AppCompatActivity {

    TextView et_price;
    TextView et_quantity;
    TextView et_title;
    TextView et_description;
    ImageView imgAd;
    Advertisment ad;
    DatabaseReference dbRef;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ad);


        et_price = findViewById(R.id.post_prdtPrice);
        et_quantity = findViewById(R.id.et_post_prdtQuantity);
        et_title = findViewById(R.id.et_post_prdtTitle);
        et_description = findViewById(R.id.et_post_prdt_Desc);
        imgAd = findViewById(R.id.img_displayProductEdit);

        Intent intent = getIntent();

        key = intent.getStringExtra("key");

        Log.d("ADebugTag", "Value: " + key);
        //imgAd.setImageURI();

       dbRef = FirebaseDatabase.getInstance().getReference().child("Advertisment").child("user1").child(key);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    et_title.setText(snapshot.child("title").getValue().toString());
                    et_quantity.setText(snapshot.child("quantity").getValue().toString());
                    et_description.setText(snapshot.child("description").getValue().toString());
                    et_price.setText(snapshot.child("price").getValue().toString());
                    Picasso.get().load(snapshot.child("image").getValue().toString()).into(imgAd);

                }
                else{
                    Toast.makeText(getApplicationContext(),"Error in fetching data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(),"Database Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editAd(View view){

        DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Advertisment").child("user1").child(key);
        updRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    ad.setTitle(et_title.getText().toString().trim());
                    ad.setQuantity(Integer.parseInt(et_quantity.getText().toString().trim()));
                    ad.setPrice(Float.parseFloat(et_price.getText().toString().trim()));
                    ad.setDescription(et_description.getText().toString().trim());
                }
                else{
                    Toast.makeText(getApplicationContext(), "Data can not be fetched!", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(),"Advertisment Updated!",Toast.LENGTH_SHORT).show();
                dbRef = FirebaseDatabase.getInstance().getReference().child("Advertisment").child("user1").child(key);
                dbRef.setValue(ad);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });













        /*Query query = FirebaseDatabase.getInstance().getReference().child("Advertisment").orderByChild("ad_id").equalTo("AD1");*/

/*        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    ad.setTitle(et_title.getText().toString().trim());
                    ad.setQuantity(Integer.parseInt(et_quantity.getText().toString().trim()));
                    ad.setPrice(Float.parseFloat(et_price.getText().toString().trim()));
                    ad.setDescription(et_description.getText().toString().trim());

                    dbRef = FirebaseDatabase.getInstance().getReference().child("Advertisment");
                    dbRef.setValue(ad);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }

}