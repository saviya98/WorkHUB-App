package com.example.workhub_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class wProfile extends Fragment {
    EditText name,email,phone,category;
    Button update,delete,addWork,btnLogout;
    WorkerDetails worker;
    FirebaseAuth fbA;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }// added to remove the previous fragment
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_w_profile, container, false);

        addWork = v.findViewById(R.id.btnAddWorkInfo);

        addWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.relativeLayout,new workInfo()).commit();
            }
        });

        return  v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        name = getActivity().findViewById(R.id.etNameW);
        email = getActivity().findViewById(R.id.etEmailW);
        phone = getActivity().findViewById(R.id.etPhoneW);
        category = getActivity().findViewById(R.id.etCategoryW);


        update = getActivity().findViewById(R.id.btnWorkerProfUpdate);
        delete = getActivity().findViewById(R.id.btnWorkerProfDelete);
        btnLogout = getActivity().findViewById(R.id.btnWorkerLogOut);

        fbA = FirebaseAuth.getInstance();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbA.signOut();
                getActivity().finish();

                Intent intent = new Intent();
                intent.setClass(getActivity(),workerRegister.class);
                getActivity().startActivity(intent);
            }
        });


        worker = new WorkerDetails();

        //retrieve data from database
        DatabaseReference showDB = FirebaseDatabase.getInstance().getReference().child("Worker_Details").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        showDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    name.setText(dataSnapshot.child("name").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    phone.setText(dataSnapshot.child("phone").getValue().toString());
                    category.setText(dataSnapshot.child("industryType").getValue().toString());
                }else{
                    Toast.makeText(getActivity(), "No Data to show", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference updateDB = FirebaseDatabase.getInstance().getReference().child("Worker_Details");
                worker.setName(name.getText().toString().trim());
                worker.setPhone(phone.getText().toString().trim());
                worker.setIndustryType(category.getText().toString().trim());
                worker.setEmail(email.getText().toString().trim());

                updateDB.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(worker).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                             FragmentTransaction ft = getFragmentManager().beginTransaction();
                             ft.replace(R.id.relativeLayout,new wProfile()).commit();
                        }
                        else {
                            Toast.makeText(getActivity(), "Error....", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DatabaseReference deleteDB = FirebaseDatabase.getInstance().getReference().child("Worker_Details");
               deleteDB.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if(snapshot.hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                           DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Worker_Details").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                           dbref.removeValue();
                           Toast.makeText(getActivity(), "Account Deleted", Toast.LENGTH_SHORT).show();
                       }
                       else {
                           Toast.makeText(getActivity(), "No data to delete", Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
            }
        });








    }
}