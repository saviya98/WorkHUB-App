package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Worker_Search_Preview extends AppCompatActivity {
    TextView phone,email,address;
    DatabaseReference ref;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker__search__preview);


        phone = findViewById(R.id.wstxtPhone);
        address = findViewById(R.id.wstxtAddress);
        email = findViewById(R.id.wstxtEmail);

        ref = FirebaseDatabase.getInstance().getReference().child("Supplier_Details");
        String key = getIntent().getStringExtra("KEY");

        ref.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String phon = snapshot.child("phone").getValue().toString();
                String addres = snapshot.child("address").getValue().toString();
                String emai = snapshot.child("email").getValue().toString();
                String comName = snapshot.child("companyName").getValue().toString();

                phone.setText(phon);
                address.setText(addres);
                email.setText(emai);

                toolbar = findViewById(R.id.signUpToolBar);
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle(comName);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}