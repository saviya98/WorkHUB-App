package com.example.workhub_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class supaccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supaccount);
    }

    Button btnedit;
    Button btnitemE;

    @Override
    protected void onResume() {
        super.onResume();

        btnedit = findViewById(R.id.editAcc);
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(supaccount.this,editprofS.class);
                startActivity(editIntent);
            }
        });

        btnitemE = findViewById(R.id.viewitem);

        btnitemE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itmemIntent = new Intent(supaccount.this,editItems.class);
                startActivity(itmemIntent);
            }
        });
    }
}