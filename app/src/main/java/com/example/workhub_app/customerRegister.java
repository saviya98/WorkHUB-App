package com.example.workhub_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class customerRegister extends AppCompatActivity {


    EditText cus_txt_name, cus_txt_email, cus_txt_occupation, cus_txt_address, cus_txt_password;
    Button cus_btn_register, cus_btn_login;
    FirebaseAuth firebaseAuth;
    DatabaseReference cus_dbref;
    Customer cus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);
        cus_btn_register = findViewById(R.id.cus_signupbtn);
        cus_btn_login = findViewById(R.id.cusloginbtn);
        cus_txt_name = findViewById(R.id.cusname);
        cus_txt_address = findViewById(R.id.cusaddress);
        cus_txt_email = findViewById(R.id.custmail);
        cus_txt_occupation = findViewById(R.id.cusoccupation);
        cus_txt_password = findViewById(R.id.cuspassword);

        firebaseAuth = FirebaseAuth.getInstance();

        cus = new Customer();

        cus_btn_register.setOnClickListener((v
        ) -> {
            final String cusemail = cus_txt_email.getText().toString().trim();
            String cuspassword = cus_txt_password.getText().toString().trim();
            final String cuslocation = cus_txt_address.getText().toString().trim();
            final String cusoccupation = cus_txt_occupation.getText().toString().trim();
            final String cusname = cus_txt_name.getText().toString().trim();


            if (TextUtils.isEmpty(cus_txt_email.getText().toString())) {

                Toast.makeText(customerRegister.this, "Email is required", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(cus_txt_password.getText().toString())) {

                Toast.makeText(customerRegister.this, "Password is required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (cuspassword.length() < 6) {

                Toast.makeText(customerRegister.this, "Password needs minimum 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(cus_txt_name.getText().toString())) {

                Toast.makeText(customerRegister.this, "Name is required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(cus_txt_address.getText().toString())) {

                Toast.makeText(customerRegister.this, "Address is required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(cus_txt_occupation.getText().toString())) {

                Toast.makeText(customerRegister.this, "Occupation is required", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.createUserWithEmailAndPassword(cusemail, cuspassword).addOnCompleteListener((task) -> {

                if (task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(), "Signing in", Toast.LENGTH_SHORT).show();


                    cus_dbref = FirebaseDatabase.getInstance().getReference().child("Customer Details");

                    cus.setName(cusname);
                    cus.setEmail(cusemail);
                    cus.setLocation(cuslocation);
                    cus.setOccupation(cusoccupation);

                    cus_dbref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(cus).addOnCompleteListener((task1) -> {

                        if (task1.isSuccessful()) {
                            Toast.makeText(customerRegister.this, "Login to continue", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(customerRegister.this, customerlogin.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(customerRegister.this, "Error!" + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    });



                }else{
                    Toast.makeText(customerRegister.this,"Error While Registering"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            });


        });

        cus_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(customerRegister.this,customerlogin.class));
            }
        });


        }


}


