package com.example.workhub_app;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Cust_Profile_Fragment extends Fragment {
    Customer cust;
    TextView cus_emailplane;
    EditText cus_nameplane,cus_occuplane,cus_addressplane;
    Button cus_btn_myproj,cus_btn_updateprof,cus_delprof,cus_logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(container != null){

            container.removeAllViews();
        }
            View v = inflater.inflate(R.layout.cust_frag_profile,container,false);
            return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cus_nameplane = getActivity().findViewById(R.id.cusnameplane);
        cus_occuplane = getActivity().findViewById(R.id.cusoccplan);
        cus_addressplane =getActivity().findViewById(R.id.cusaddressplane);
        cus_emailplane =getActivity().findViewById(R.id.cusemailplane);
        cus_btn_updateprof = getActivity().findViewById(R.id.btneditacc);
        cus_logout =getActivity().findViewById(R.id.cuslogout);
        cus_delprof=getActivity().findViewById(R.id.btndelacc);
        cus_btn_myproj = getActivity().findViewById(R.id.btnupdateprof);
        cust = new Customer();

        DatabaseReference  cust_showDb= FirebaseDatabase.getInstance().getReference().child("Customer Details").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        cust_showDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    cus_nameplane.setText(snapshot.child("name").getValue().toString());
                    cus_emailplane.setText(snapshot.child("email").getValue().toString());
                    cus_addressplane.setText(snapshot.child("location").getValue().toString());
                    cus_occuplane.setText(snapshot.child("occupation").getValue().toString());

                }else{
                    Toast.makeText(getActivity(),"noData to show",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        cus_btn_updateprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference cus_updateDb =FirebaseDatabase.getInstance().getReference().child("Customer Details");
                cus_updateDb.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                          cust.setName(cus_nameplane.getText().toString().trim());
                          cust.setLocation(cus_addressplane.getText().toString().trim());
                          cust.setOccupation(cus_occuplane.getText().toString().trim());
                          cust.setEmail(cus_emailplane.getText().toString().trim());

                          DatabaseReference cus_up_db = FirebaseDatabase.getInstance().getReference().child("Customer Details").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        cus_up_db.setValue(cust);

                        Toast.makeText(getActivity(),"Profile Updated",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(),cust_activities.class);
                            startActivity(intent);

                        }else{

                            Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


       cus_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), customerlogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        cus_delprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference cust_delete_db =FirebaseDatabase.getInstance().getReference().child("Customer Details");
                cust_delete_db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                            DatabaseReference cust_del_Db =FirebaseDatabase.getInstance().getReference().child("Customer Details").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            cust_del_Db.removeValue();
                            Toast.makeText(getActivity(),"Your Account was deleted succesfully",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), customerlogin.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        cus_btn_myproj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),cust_proj_my_proj.class);
                startActivity(intent);
            }
        });
    }
}
