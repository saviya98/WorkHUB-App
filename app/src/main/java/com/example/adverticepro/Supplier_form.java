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


public class Supplier_form extends AppCompatActivity {

    EditText fName, fNumber, fDetails; //initialize variables
    Button sub;
    SupplierProjects supPro; //object
    DatabaseReference databaseReference; //database reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_form);

        fName = findViewById(R.id.formName);
        fNumber = findViewById(R.id.formPhone);
        fDetails = findViewById(R.id.formProDetails);
        sub = findViewById(R.id.btnSubmit);


        supPro = new SupplierProjects(); //creating object


        sub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = fName.getText().toString().trim();
                String phone = fNumber.getText().toString().trim();
                String proDiscrip = fDetails.getText().toString().trim();



                if(TextUtils.isEmpty(fName.getText().toString())){  //checking field is empty or not
                    Toast.makeText(Supplier_form.this, "Name is Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(TextUtils.isEmpty(fNumber.getText().toString())){  //checking field is empty or not
                    Toast.makeText(Supplier_form.this, "Number is Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(TextUtils.isEmpty(fDetails.getText().toString())){  //checking field is empty or not
                    Toast.makeText(Supplier_form.this, "Project Details are Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                else{


                    //database connection and sending data to database
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("SupplierProjects");
                    supPro.setSupName(name);
                    supPro.setSupNumber(phone);
                    supPro.setSupProDetails(proDiscrip);
                    databaseReference.push().setValue(supPro);



                    //successfully inserted message

                    Toast.makeText(Supplier_form.this, "Data inserted successfully !", Toast.LENGTH_SHORT).show();


                    //  });


                }
            }
        });


    }
}