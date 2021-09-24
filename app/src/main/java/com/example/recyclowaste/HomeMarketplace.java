package com.example.recyclowaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recyclowaste.model.Advertisment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeMarketplace extends AppCompatActivity  {

    RecyclerView recyclerView;
    allAdsAdapter adsAdapter;
    private List<Advertisment> adsList;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_marketplace);

/*
        Spinner spinner2 = (Spinner)findViewById(R.id.marketplace_spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.quantity_array,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner2.setAdapter(adapter);


*/

        recyclerView = findViewById(R.id.recyclerView_allAds);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adsList = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Advertisment");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                getAds(snapshot);
                adsAdapter = new allAdsAdapter(getApplicationContext(),adsList);
                recyclerView.setAdapter(adsAdapter);
             //   adsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getAds(DataSnapshot snapshot){
        for(DataSnapshot snap : snapshot.getChildren()){
            adsList.add(new Advertisment(snap.child("title").getValue().toString(),snap.child("description").getValue().toString(),snap.child("image").getValue().toString()
                    ,Float.parseFloat(snap.child("price").getValue().toString()), Integer.parseInt(snap.child("quantity").getValue().toString())));
        }
    }

    /*public void onItemSelected(AdapterView<?> parent, View view,int pos,long id){
        parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent){

    }*/

}