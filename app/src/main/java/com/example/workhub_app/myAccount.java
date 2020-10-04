package com.example.workhub_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class myAccount extends Fragment {

    FirebaseAuth firebaseAuth;
    Button logout;


    private static final String TAG = "myAccount";
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final TextView txt1,txt2,txt3,txt4,txt5;

        firebaseAuth = FirebaseAuth.getInstance();
        logout = getActivity().findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                getActivity().finish();

               Intent intent = new Intent();
               intent.setClass(getActivity(),login.class);
               getActivity().startActivity(intent);

            }
        });


        txt1 = getActivity().findViewById(R.id.txtC6);
        txt2 = getActivity().findViewById(R.id.txtC7);
        txt3 = getActivity().findViewById(R.id.txtC8);
        txt4 = getActivity().findViewById(R.id.txtC9);
        txt5 = getActivity().findViewById(R.id.txtC10);



        final DatabaseReference readdb = FirebaseDatabase.getInstance().getReference().child("Supplier_Details").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        readdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    txt1.setText(dataSnapshot.child("companyName").getValue().toString());
                    txt2.setText(dataSnapshot.child("ownerName").getValue().toString());
                    txt3.setText(dataSnapshot.child("address").getValue().toString());
                    txt4.setText(dataSnapshot.child("phone").getValue().toString());
                    txt5.setText(dataSnapshot.child("email").getValue().toString());
                }
                else
                    Toast.makeText(getActivity(),"No Source to Display",Toast.LENGTH_SHORT).show();         }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        }); }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_account, container, false);
        Button editAcc = v.findViewById(R.id.editAcc);
        editAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               FragmentTransaction frt = getFragmentManager().beginTransaction();
               frt.replace(R.id.myAcc,new editAcc()).commit();
            }
        });

        return v;

    }



}