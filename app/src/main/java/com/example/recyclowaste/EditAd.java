package com.example.recyclowaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclowaste.model.Advertisment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        imgAd = findViewById(R.id.img_displayProduct);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("Description");
        String price = intent.getStringExtra("price");
        String quantity = intent.getStringExtra("quantity");
        key = intent.getStringExtra("key");
        et_price.setText(price);
        et_description.setText(description);
        et_quantity.setText(quantity);
        et_title.setText(title);
        //imgAd.setImageURI();
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