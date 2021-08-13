package com.example.recyclowaste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class HomeMarketplace extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_marketplace);

        Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.quantity_array,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

    }

    public void onItemSelected(AdapterView<?> parent, View view,int pos,long id){
        parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent){

    }
}