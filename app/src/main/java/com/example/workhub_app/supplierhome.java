package com.example.workhub_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class supplierhome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplierhome);
    }

    Button btnacc;
    Button btnacc1;
    @Override
    protected void onResume() {
        super.onResume();

        btnacc = findViewById(R.id.additems);
        btnacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itemIntent = new Intent(supplierhome.this,addItem.class);
                startActivity(itemIntent);
            }
        });

        btnacc1 = findViewById(R.id.home3);
        btnacc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itemIntent1 = new Intent(supplierhome.this,supaccount.class);
                startActivity(itemIntent1);
            }
        });
    }
}