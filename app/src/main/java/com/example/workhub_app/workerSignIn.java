package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class workerSignIn extends AppCompatActivity {
    Button workSignIn;
    EditText txtEmailSIn,txtPassSIn;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_sign_in);

        workSignIn = findViewById(R.id.btnWorkerSignIn);
        txtEmailSIn = findViewById(R.id.etWorkSignInEmail);
        txtPassSIn = findViewById(R.id.etWorkPassSignIn);
        progressBar = findViewById(R.id.pbWorkSignIn);
        firebaseAuth = FirebaseAuth.getInstance();

        workSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmailSIn.getText().toString().trim();
                String password = txtPassSIn.getText().toString().trim();

                if(TextUtils.isEmpty(txtEmailSIn.getText().toString()) || !email.contains("@")){
                    Toast.makeText(workerSignIn.this, "Email is Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(txtPassSIn.getText().toString())){
                    Toast.makeText(workerSignIn.this, "Password is Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(txtPassSIn.length() < 6){
                    Toast.makeText(workerSignIn.this, "Need min 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(workerSignIn.this, "Signing in...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(workerSignIn.this,Worker_Profile.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(workerSignIn.this, "Error while signing in...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });
    }
}