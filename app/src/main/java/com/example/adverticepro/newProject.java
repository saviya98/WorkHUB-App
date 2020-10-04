package com.example.adverticepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class newProject extends AppCompatActivity {

    Button btnCustomer, btnWorker, btnSuplier; //initializing variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        btnCustomer = findViewById(R.id.btnNewCustomer);
        btnWorker = findViewById(R.id.btnNewWorker);
        btnSuplier = findViewById(R.id.btnNewSuplier);
    }




    public void newPro(View view){ //customer new project
        Intent cintent = new Intent(this, form.class);
        startActivity(cintent);
    }

    public void supNewPro(View view){ //supplier new project
        Intent sintent = new Intent(this, Supplier_form.class);
        startActivity(sintent);
    }

    public void workerNewPro(View view){ //worker new project
        Intent wintent = new Intent(this, Worker_form.class);
        startActivity(wintent);
    }


}