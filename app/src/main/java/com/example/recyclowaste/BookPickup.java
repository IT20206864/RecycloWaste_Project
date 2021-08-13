package com.example.recyclowaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

public class BookPickup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner dropdown;
    String domestic[] = new String[]{"Plastic", "Wood", "Paper", "Organic", "Glass", "Metal"};
    String medical[] = new String[]{"Syringes", "Gloves", "Needles", "Liquids", "Bandages", "Drugs"};
    CheckBox waste1;
    CheckBox waste2;
    CheckBox waste3;
    CheckBox waste4;
    CheckBox waste5;
    CheckBox waste6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_pickup);
        waste1 = (CheckBox)findViewById(R.id.waste1);
        waste2 = (CheckBox)findViewById(R.id.waste2);
        waste3 = (CheckBox)findViewById(R.id.waste3);
        waste4 = (CheckBox)findViewById(R.id.waste4);
        waste5 = (CheckBox)findViewById(R.id.waste5);
        waste6 = (CheckBox)findViewById(R.id.waste6);

        //get the spinner from the xml.
        dropdown = findViewById(R.id.typeSpinner);
//create a list of items for the spinner.
        String[] items = new String[]{"Domestic Waste", "Medical Waste"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);


    }

    public void clickConfirm(View view) {
        Intent payment = new Intent(this, BookingPayment.class);
        startActivity(payment);
    }

    public void itemSelected(){
        if(dropdown.getSelectedItem() == "Domestic Waste"){
            waste1.setText(domestic[0]);
            waste2.setText(domestic[1]);
            waste3.setText(domestic[2]);
            waste4.setText(domestic[3]);
            waste5.setText(domestic[4]);
            waste6.setText(domestic[5]);
        }
        else if(dropdown.getSelectedItem() == "Medical Waste"){
            waste1.setText(medical[0]);
            waste2.setText(medical[1]);
            waste3.setText(medical[2]);
            waste4.setText(medical[3]);
            waste5.setText(medical[4]);
            waste6.setText(medical[5]);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        itemSelected();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}