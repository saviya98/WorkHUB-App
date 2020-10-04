package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Worker_Cus_Project_Preview extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    TextView txtDescription,txtPhone,txtDayWage,txtDate;
    Button acceptJob;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker__cus__project__preview);


        txtDescription = findViewById(R.id.WtxtProjDescrip);
        txtPhone = findViewById(R.id.wPhoneProjtxt);
        txtDayWage = findViewById(R.id.wDailyWagetxt);
        txtDate =findViewById(R.id.wApprxDatetxt);

        acceptJob = findViewById(R.id.btnWProfAccept);


        ref = FirebaseDatabase.getInstance().getReference().child("Customer_Add_Projects");
        String key = getIntent().getStringExtra("KEY2");

        ref.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                txtDescription.setText((String)snapshot.child("cust_project_description").getValue());
                txtPhone.setText((String)snapshot.child("cust_project_telnumber").getValue());
                txtDayWage.setText((String) snapshot.child("cust_project_dailywage").getValue());
                txtDate.setText((String) snapshot.child("cust_project_aprox_date").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        acceptJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callJob();
            }
        });
    }

    private void callJob(){
        String number = txtPhone.getText().toString();
        if(number.trim().length()>0){
            if(ContextCompat.checkSelfPermission(Worker_Cus_Project_Preview.this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Worker_Cus_Project_Preview.this,new String[]
                        {Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }
            else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                callJob();
            }
            else {
                Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}