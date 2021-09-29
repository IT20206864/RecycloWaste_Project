package com.example.recyclowaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recyclowaste.model.ReviewTwo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import java.util.ArrayList;

public class review_history extends AppCompatActivity {

    private static final String TAG = "review_history";
    RecyclerView recyclerView;
    DatabaseReference database;
    AdapterTwo adaptertwo;
    ArrayList<ReviewTwo> list;
    FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_history);
        setTitle("Review History");
        Log.d(TAG, "onCreate: started");

        recyclerView=(RecyclerView) findViewById(R.id.recyclerviewReviews);
        database = FirebaseDatabase.getInstance().getReference("Reviews");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adaptertwo = new AdapterTwo(this,list);
        recyclerView.setAdapter(adaptertwo);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ReviewTwo reviewTwo = dataSnapshot.getValue(ReviewTwo.class);
                    list.add(reviewTwo);
                }
                adaptertwo.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        fb=(FloatingActionButton) findViewById(R.id.fabAdd);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),reviewAdd.class));
            }
        });
    }
}