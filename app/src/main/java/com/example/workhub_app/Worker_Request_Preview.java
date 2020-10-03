package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Worker_Request_Preview extends AppCompatActivity {

    TextView name,email;
    RecyclerView recyclerView;
    wrpProjectRecAdapter wrpAdapter;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker__request__preview);

        recyclerView = (RecyclerView) findViewById(R.id.wRecCusProjView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        name = findViewById(R.id.wReqRecName);
        email = findViewById(R.id.wReqRecEmail);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer Details");
        String key = getIntent().getStringExtra("KEY1");

        dbRef.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nm = snapshot.child("name").getValue().toString();
                String em = snapshot.child("email").getValue().toString();

                name.setText(nm);
                email.setText(em);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //get db for projects recycle view
        FirebaseRecyclerOptions<wReqModel> options =
                new FirebaseRecyclerOptions.Builder<wReqModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Customer_Add_Projects").child(key),wReqModel.class).build();


        wrpAdapter = new wrpProjectRecAdapter(options);
        recyclerView.setAdapter(wrpAdapter);


    }

    @Override
    public void onStart() {
        super.onStart();
        wrpAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        wrpAdapter.stopListening();
    }
}