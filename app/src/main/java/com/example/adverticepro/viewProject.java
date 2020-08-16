package com.example.adverticepro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class viewProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

        Spinner theSpinner = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> theAdapter = new ArrayAdapter<String>(viewProject.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.namess));
        theAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        theSpinner.setAdapter(theAdapter);

    }
}