package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerProfile extends AppCompatActivity {


    TextView custpname,custpaddress,custpemail,custpoccupation;
    Button cuslogout,cusaddprof,cuseditprof,cusdeleteprof,cbtnimageupload;

    FirebaseAuth cfbauth;
    FirebaseUser cfbuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        custpname = findViewById(R.id.cusnameplane);
        custpaddress =findViewById(R.id.cusaddressplane);
        custpemail =findViewById(R.id.cusemailplane);
        custpoccupation =findViewById(R.id.cusoccplan);
        cuslogout =findViewById(R.id.cuslogout);
        cusaddprof =findViewById(R.id.btnupdateprof);
        cuseditprof =findViewById(R.id.btneditacc);
        cusdeleteprof =findViewById(R.id.btndelacc);
        cbtnimageupload =findViewById(R.id.cusimgbtn);

        //cfbauth =FirebaseAuth.getInstance();
        //cfbuser =cfbauth.getCurrentUser();

        //custpname.setText(cfbuser.getEmail());

        Customer cus = new Customer();

        //retrievw data from database
        DatabaseReference cdbrf = FirebaseDatabase.getInstance().getReference().child("Customer").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        cdbrf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){

                    custpname.setText(snapshot.child("name").getValue().toString());
                    custpoccupation.setText(snapshot.child("occupation").getValue().toString());
                    custpaddress.setText(snapshot.child("location").getValue().toString());
                    //custpemail.setText(snapshot.child("email").getValue().toString());
                }else{
                   Toast.makeText(CustomerProfile.this,"no data to show",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        cuslogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(CustomerProfile.this,customerlogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


    }
}