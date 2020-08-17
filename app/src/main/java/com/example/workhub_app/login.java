package com.example.workhub_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    Button btnlogin;
    TextView text1;
    TextView text2;

    @Override
    protected void onResume() {
        super.onResume();

        btnlogin = findViewById(R.id.btnlogin);
        text1 = findViewById(R.id.un);
        text2 = findViewById(R.id.pwenter);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logintent = new Intent(login.this,supplierhome.class);
                startActivity(logintent);
            }
        });
    }
}