package com.example.workhub_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cust_proj_my_proj extends AppCompatActivity {

    RecyclerView cus_proj_recview;
    cus_proj_adapter cu1adapter;
    DatabaseReference cdbf1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_proj_my_proj);

            cus_proj_recview = (RecyclerView)findViewById(R.id.cust_recview_myproj);


            cus_proj_recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<cust_proj_model> cus_proj_options =
                new FirebaseRecyclerOptions.Builder<cust_proj_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Customer_Add_Projects").child(FirebaseAuth.getInstance().getCurrentUser().getUid()),cust_proj_model.class)
                        .build();


        cu1adapter = new cus_proj_adapter(cus_proj_options);
        cus_proj_recview.setAdapter(cu1adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cu1adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cu1adapter.startListening();
    }
}
