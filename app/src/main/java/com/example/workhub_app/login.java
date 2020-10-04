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

public class login extends AppCompatActivity {

   Button btnlogin;
  EditText unlog , pw;
  FirebaseAuth fbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin = findViewById(R.id.btnlogin1);
        unlog = findViewById(R.id.un);
        pw = findViewById(R.id.pwenter);
        fbd = FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate email & password
                String email = unlog.getText().toString().trim();
                String pw1 = pw.getText().toString().trim();

                if(TextUtils.isEmpty(unlog.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please Enter an E-mail", Toast.LENGTH_SHORT).show();
                    unlog.setError("User-Name/E-mail is required");
                    return;
                }

                if(TextUtils.isEmpty(pw.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please Enter a password", Toast.LENGTH_SHORT).show();
                    pw.setError("Password is required");
                    return;
                }

                if(pw1.length() < 6) {
                    pw.setError("Password must be >= 6 characters");
                    return;
                }

                //authenticate the user
                fbd.signInWithEmailAndPassword(email,pw1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checks login is successful or not
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Login is successful...!",Toast.LENGTH_SHORT).show();
                           // Intent logintent = new Intent(login.this,supplierHomeFrag.class);
                            //startActivity(logintent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Error...!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

}