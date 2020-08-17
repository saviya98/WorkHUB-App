package com.example.workhub_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
    }


    Button btnaccb;
    @Override
    protected void onResume() {
        super.onResume();

        btnaccb = findViewById(R.id.home4);
        btnaccb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accIntent = new Intent(addItem.this,supaccount.class);
                startActivity(accIntent);
            }
        });

    }
}