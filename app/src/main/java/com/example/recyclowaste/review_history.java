package com.example.recyclowaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclowaste.model.Review;
import com.example.recyclowaste.model.ReviewTwo;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class review_history extends AppCompatActivity {

    private static final String TAG = "review_history";
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClassReview> userList;
    AdapterTwo adaptertwo;
    FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_history);
        setTitle("Review History");
        Log.d(TAG, "onCreate: started");

        recyclerView=(RecyclerView) findViewById(R.id.recyclerviewReviews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ReviewTwo> options =
                new FirebaseRecyclerOptions.Builder<ReviewTwo>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Reviews"), ReviewTwo.class)
                        .build();

        adaptertwo = new AdapterTwo(options);
        recyclerView.setAdapter(adaptertwo);

        fb=(FloatingActionButton) findViewById(R.id.fabAdd);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),reviewAdd.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adaptertwo.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adaptertwo.stopListening();
    }


    /*private void initData() {

        userList = new ArrayList<>();

        Log.d(TAG, "initData: started");
        userList.add(new ModelClassReview(R.drawable.user,"Angel", "The Service is very good", "10.35 am"));
        userList.add(new ModelClassReview(R.drawable.user,"Sanath", "The Service is very good", "10.38 am"));
        userList.add(new ModelClassReview(R.drawable.user,"Roshan", "The Service is very good", "10.38 am"));
        userList.add(new ModelClassReview(R.drawable.user,"Amasha", "The Service is very good", "10.37 am"));
        userList.add(new ModelClassReview(R.drawable.user,"Oshan", "The Service is very good", "10.38 am"));
        userList.add(new ModelClassReview(R.drawable.user,"Shehan", "The Service is very good", "10.34 am"));
        userList.add(new ModelClassReview(R.drawable.user,"Dilini", "The Service is very good", "10.31 am"));
        userList.add(new ModelClassReview(R.drawable.user,"Anjula", "The Service is very good", "10.39 am"));
        userList.add(new ModelClassReview(R.drawable.user,"Saman", "The Service is very good", "10.40 am"));
        userList.add(new ModelClassReview(R.drawable.user,"Hashini", "The Service is very good", "10.20 am"));

    }

    private void initRecyclerView() {

        Log.d(TAG, "initRecyclerView: started");
        recyclerView = findViewById(R.id.recyclerviewReviews);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //adaptertwo = new AdapterTwo(userList);
        recyclerView.setAdapter(adaptertwo);
        adaptertwo.notifyDataSetChanged();
    }*/
}