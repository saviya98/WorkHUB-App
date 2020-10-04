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


public class Worker_form extends AppCompatActivity {

    EditText fName, fNumber, fDetails; //initializing variables
    Button sub;
    WorkerProjects workerPro; //object
    DatabaseReference databaseReference; //database reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_form);

        fName = findViewById(R.id.formName);
        fNumber = findViewById(R.id.formPhone);
        fDetails = findViewById(R.id.formProDetails);
        sub = findViewById(R.id.btnSubmit);


        workerPro = new WorkerProjects();  //creating object


        sub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = fName.getText().toString().trim(); //getting values from the form
                String phone = fNumber.getText().toString().trim();
                String proDiscrip = fDetails.getText().toString().trim();



                if(TextUtils.isEmpty(fName.getText().toString())){  //checking the field empty or not
                    Toast.makeText(Worker_form.this, "Name is Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(TextUtils.isEmpty(fNumber.getText().toString())){  //checking the field empty or not
                    Toast.makeText(Worker_form.this, "Number is Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(TextUtils.isEmpty(fDetails.getText().toString())){  //checking the field empty or not
                    Toast.makeText(Worker_form.this, "Project Details are Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                else{


                    //database connecting and pass data to database
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("WorkerProjects");
                    workerPro.setWorkerName(name);
                    workerPro.setWorkerNumber(phone);
                    workerPro.setWorkerProDetails(proDiscrip);
                    databaseReference.push().setValue(workerPro);



                    //insert successful message

                    Toast.makeText(Worker_form.this, "Data inserted successfully !", Toast.LENGTH_SHORT).show();


                    //  });


                }
            }
        });


    }
}