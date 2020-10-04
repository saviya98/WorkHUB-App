package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class workerRegister extends AppCompatActivity {
    private Toolbar toolbar;
    EditText txtName,txtEmail,txtPhone,txtPass,txtIndusType;
    RadioButton individual,company;
    Button workerSignUp,workerLogInS;
    FirebaseAuth fbAuth;
    DatabaseReference database;
    WorkerDetails worker;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_register);

        toolbar = findViewById(R.id.signUpToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign Up on WorkHUB");

        workerSignUp = findViewById(R.id.btnWorkerSignUp);
        workerLogInS = findViewById(R.id.btnWorkersmallLogin);
        txtName = findViewById(R.id.nameSignUp);
        txtEmail = findViewById(R.id.emailSignUp);
        txtPhone = findViewById(R.id.phoneSignUp);
        txtPass = findViewById(R.id.passwordSignUp);
        txtIndusType = findViewById(R.id.etIndustry);

        progressBar = findViewById(R.id.progressBar);

        fbAuth = FirebaseAuth.getInstance();

        worker = new WorkerDetails();

        workerSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = txtEmail.getText().toString().trim();
                String password = txtPass.getText().toString().trim();
                final String name = txtName.getText().toString().trim();
                final String phone = txtPhone.getText().toString().trim();
                final String indus = txtIndusType.getText().toString().trim();


                if(TextUtils.isEmpty(txtEmail.getText().toString()) || !email.contains("@")){
                    Toast.makeText(workerRegister.this, "Email is Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(txtPass.getText().toString())){
                    Toast.makeText(workerRegister.this, "Password is Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length() < 6){
                    Toast.makeText(workerRegister.this, "Need min 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(txtName.getText().toString())){
                    Toast.makeText(workerRegister.this, "Name is Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(txtPhone.getText().toString())){
                    Toast.makeText(workerRegister.this, "Phone is Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(phone.length()<10){
                    txtPhone.setError("Invalid Number");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fbAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Signing in...", Toast.LENGTH_SHORT).show();

                            database = FirebaseDatabase.getInstance().getReference().child("Worker_Details");
                            worker.setName(name);
                            worker.setEmail(email);
                            worker.setPhone(phone);
                            worker.setIndustryType(indus);

                            database.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(worker).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(workerRegister.this, Worker_Profile.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(workerRegister.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                        }else{
                            Toast.makeText(workerRegister.this, "Error while registering"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });

        workerLogInS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(workerRegister.this,workerSignIn.class);
                startActivity(i);
            }
        });
    }
}