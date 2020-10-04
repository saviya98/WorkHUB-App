package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Target_my_proj extends AppCompatActivity {
    cust_proj_model cust12;
    TextView textViewId1,textViewId2,textViewcId3,textViewId4,textViewId5;
    Button btn1,btn2;
    DatabaseReference dbrefcmypro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_my_proj);
        textViewId1 =findViewById(R.id.cus_proj_my);
        textViewId2 =findViewById(R.id.cust_proj_my_description);
        textViewcId3 =findViewById(R.id.cust_my_proj_contactno);
        textViewId4 =findViewById(R.id.cust_my_proj_wage);
        textViewId5 =findViewById(R.id.cust_my_proj_days);
        btn1 =findViewById(R.id.cust_my_proj_calc);
        btn2 =findViewById(R.id.cust_my_proj_add);


        cust12 = new cust_proj_model();
        String key13 = getIntent().getStringExtra("Key12");
        System.out.println(key13);

        dbrefcmypro = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("cust_proj_model").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key13);
        dbrefcmypro.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    textViewId1.setText(snapshot.child("cust_project_title").getValue().toString());
                    textViewId2.setText(snapshot.child("cust_project_description").getValue().toString());
                }
                else{
                    Toast.makeText(Target_my_proj.this,"Error",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}