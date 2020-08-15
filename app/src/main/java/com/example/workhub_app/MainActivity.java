package com.example.workhub_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.signUpToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign Up on WorkHUB");


    }

    public void navigateProfile(View view){
        Intent intent = new Intent(MainActivity.this,Worker_Profile.class);
        startActivity(intent);
    }


}