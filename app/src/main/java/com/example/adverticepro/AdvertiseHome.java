package com.example.adverticepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdvertiseHome extends AppCompatActivity {

    Button btnNewHome, btnViewHome, btnMyHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise_home);

        btnNewHome = findViewById(R.id.btnNewHome);
        btnViewHome = findViewById(R.id.btnViewHome);
        btnMyHome = findViewById(R.id.btnMyHome);
    }

    public void startNewHome(View view){
        Intent intent = new Intent(this, newProject.class);
        startActivity(intent);
    }

    public void startMyHome(View view){
        Intent intent = new Intent(this, myProjects.class);
        startActivity(intent);
    }

    public void startViewHome(View view){
        Intent intent = new Intent(this, viewProject.class);
        startActivity(intent);
    }


}