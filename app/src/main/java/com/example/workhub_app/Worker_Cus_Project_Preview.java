package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Worker_Cus_Project_Preview extends AppCompatActivity {
    TextView descrip,phone,dailyWage,date;
    Button acceptJob;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker__cus__project__preview);

        descrip = findViewById(R.id.WtxtProjDescrip);
        phone = findViewById(R.id.wPhoneProjtxt);
        dailyWage = findViewById(R.id.wDailyWagetxt);
        date = findViewById(R.id.wApprxDatetxt);

        acceptJob = findViewById(R.id.btnWProfAccept);


        ref = FirebaseDatabase.getInstance().getReference().child("Customer_Add_Projects");
        String key = getIntent().getStringExtra("KEY2");


        ref.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               final String des = snapshot.child("cust_project_description").getValue().toString().trim();
//                String phon = snapshot.child("cust_project_telnumber").getValue().toString();
//                int dailyW = Integer.parseInt(snapshot.child("cust_project_dailywage").getValue().toString());
//                int apDate = Integer.parseInt(snapshot.child("cust_project_aprox_date").getValue().toString());

                descrip.setText(des);
                //descrip.setText(snapshot.child("cust_project_description").getValue().toString());
               //phone.setText(phon);
//                dailyWage.setText(dailyW);
//                date.setText(apDate);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}