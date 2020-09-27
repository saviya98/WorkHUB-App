package com.example.adverticepro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class form extends AppCompatActivity {

    EditText cusName, cusDiscription, cusPhone;
    Button btnSubmit;
    DatabaseReference dbRef;

    customerProjects cusPro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        cusName = findViewById(R.id.formName);
        cusPhone = findViewById(R.id.formPhone);
        cusDiscription = findViewById(R.id.formProDetails);
        btnSubmit = findViewById(R.id.btnSubmit);

        cusPro = new customerProjects();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("customPro");

                if(TextUtils.isEmpty(cusName.getText().toString())){
                    Toast.makeText(form.this, "Name is Required", Toast.LENGTH_SHORT).show();

                }

                else if(TextUtils.isEmpty(cusPhone.getText().toString())){
                    Toast.makeText(form.this, "Number is Required", Toast.LENGTH_SHORT).show();

                }

                else if(TextUtils.isEmpty(cusDiscription.getText().toString())){
                    Toast.makeText(form.this, "Project Details are Required", Toast.LENGTH_SHORT).show();

                }

                else{

                    cusPro.setCusName(cusName.getText().toString().trim());
                    cusPro.setCusNumber(Integer.parseInt(cusPhone.getText().toString().trim()));
                    cusPro.setProDetails(cusDiscription.getText().toString().trim());
                    dbRef.child("cus1").setValue(cusPro);
                    Toast.makeText(form.this, "Successfully insertd", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}