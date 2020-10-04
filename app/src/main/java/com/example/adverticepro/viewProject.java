package com.example.adverticepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class viewProject extends AppCompatActivity {

    Button viewCustomer, viewWorker, viewSuplier; //initializing variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

        viewCustomer = findViewById(R.id.viewCustomer);
        viewWorker = findViewById(R.id.viewWorker);
        viewSuplier = findViewById(R.id.viewSuplier);
    }

    public void projects(View view){
        Intent intent = new Intent(this, projects.class);
        startActivity(intent);
    }

}