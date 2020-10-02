package com.example.adverticepro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class form extends AppCompatActivity {

    EditText fName, fNumber, fDetails;
    Button sub;
    CustomerProjects cusPro; //object
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        fName = findViewById(R.id.formName);
        fNumber = findViewById(R.id.formPhone);
        fDetails = findViewById(R.id.formProDetails);
        sub = findViewById(R.id.btnSubmit);


        cusPro = new CustomerProjects();


         sub.setOnClickListener(new OnClickListener() {
             @Override
            public void onClick(View view) {

                 String name = fName.getText().toString().trim();
                 String phone = fNumber.getText().toString().trim();
                 String proDiscrip = fDetails.getText().toString().trim();



                if(TextUtils.isEmpty(fName.getText().toString())){
                    Toast.makeText(form.this, "Name is Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(TextUtils.isEmpty(fNumber.getText().toString())){
                    Toast.makeText(form.this, "Number is Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(TextUtils.isEmpty(fDetails.getText().toString())){
                    Toast.makeText(form.this, "Project Details are Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                else{



                    databaseReference = FirebaseDatabase.getInstance().getReference().child("CustomerProjects");
                    cusPro.setCusName(name);
                    cusPro.setCusNumber(phone);
                    cusPro.setProDetails(proDiscrip);
                    databaseReference.push().setValue(cusPro);



                    //dbref.child("cus1").setValue(cusPro);

                    Toast.makeText(form.this, "Data inserted successfully !", Toast.LENGTH_SHORT).show();


                    //  });


                }
            }
        });


    }
}