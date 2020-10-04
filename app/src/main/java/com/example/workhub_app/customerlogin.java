package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class customerlogin extends AppCompatActivity {

    Button cus_login;
    EditText cusemail,cusspswd;
    FirebaseAuth fibauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerlogin);

        cus_login = findViewById(R.id.cuslogbtn);
        cusemail = findViewById(R.id.cusun);
        cusspswd = findViewById(R.id.cuspwd);

        cus_login.setOnClickListener(view -> {
            final String cemail = cusemail.getText().toString().trim();
            final String cpwd = cusspswd.getText().toString().trim();

            if(TextUtils.isEmpty(cusemail.getText().toString())){
                Toast.makeText(customerlogin.this,"Please enter email",Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(cusspswd.getText().toString())){
                Toast.makeText(customerlogin.this,"Please enter password",Toast.LENGTH_SHORT).show();
                return;
            }

            fibauth = FirebaseAuth.getInstance();

            cus_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fibauth.signInWithEmailAndPassword(cemail,cpwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(customerlogin.this, "sign in", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(customerlogin.this,cust_activities.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(customerlogin.this,"error while sign in..",Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
            });


            });

    }


}