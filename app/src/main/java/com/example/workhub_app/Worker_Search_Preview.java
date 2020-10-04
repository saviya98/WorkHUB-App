package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Worker_Search_Preview extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    TextView phone,email,address;
    DatabaseReference ref;
    Button caller;
    private Toolbar toolbar;
    RecyclerView recItemView;
    wspSupItemRecAdapter wspAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker__search__preview);

        recItemView = (RecyclerView) findViewById(R.id.wSearchItemPrewRec);
        recItemView.setLayoutManager(new LinearLayoutManager(this));

        phone = findViewById(R.id.wstxtPhone);
        address = findViewById(R.id.wstxtAddress);
        email = findViewById(R.id.wstxtEmail);
        caller = findViewById(R.id.btnWorkerContact);

        ref = FirebaseDatabase.getInstance().getReference().child("Supplier_Details");
        String key = getIntent().getStringExtra("KEY");

        ref.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String phon = snapshot.child("phone").getValue().toString();
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

        caller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMe();
            }
        });

        //get db for item recycle view
        FirebaseRecyclerOptions<wSupItemModel> options =
                new FirebaseRecyclerOptions.Builder<wSupItemModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Item").child(key),wSupItemModel.class).build();

        wspAdapter = new wspSupItemRecAdapter(options);
        recItemView.setAdapter(wspAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        wspAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        wspAdapter.stopListening();
    }

    private void callMe(){
        String number = phone.getText().toString();
        if(number.trim().length()>0){
            if(ContextCompat.checkSelfPermission(Worker_Search_Preview.this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(Worker_Search_Preview.this,new String[]
                                    {Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }
            else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                callMe();
            }
            else {
                Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}