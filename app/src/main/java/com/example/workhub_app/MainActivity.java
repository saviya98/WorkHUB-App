package com.example.workhub_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = findViewById(R.id.signUpToolBar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Sign Up on WorkHUB");

    }

    Button btnlog;

    @Override
    protected void onResume() {
        super.onResume();

        btnlog = findViewById(R.id.login);
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);

            }
        });


    }
}