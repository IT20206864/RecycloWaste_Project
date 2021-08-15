package com.example.recyclowaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class review_history extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClassReview> userList;
    AdapterTwo adaptertwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_history);

        initData();
        initRecyclerView();
    }

    private void initData() {

        userList = new ArrayList<>();

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

        recyclerView = findViewById(R.id.recyclerviewReviews);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adaptertwo = new AdapterTwo(userList);
        recyclerView.setAdapter(adaptertwo);
        adaptertwo.notifyDataSetChanged();
    }
}