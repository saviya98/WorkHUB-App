package com.example.workhub_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
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

public class workInfo extends Fragment {

    EditText cName,location,services,hRate;
    Button add,update;

    WorkerDetails workerDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }// added to remove the previous fragment
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cName = getActivity().findViewById(R.id.comWorkername);
        location = getActivity().findViewById(R.id.workInfoCity);
        services = getActivity().findViewById(R.id.workInfoServices);
        hRate = getActivity().findViewById(R.id.workInfoHrate);

        add = getActivity().findViewById(R.id.workInfoAddDetail);
        update = getActivity().findViewById(R.id.workInfoUpdateDetail);

        workerDetails = new WorkerDetails();

        //retrieve data from database
        DatabaseReference showDB = FirebaseDatabase.getInstance().getReference().child("Worker_Details").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        showDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).hasChildren()) {
                    cName.setText(dataSnapshot.child("companyName").getValue().toString());
                    location.setText(dataSnapshot.child("location").getValue().toString());
                    services.setText(dataSnapshot.child("services").getValue().toString());
                    hRate.setText(dataSnapshot.child("hRate").getValue().toString());
                }else{
                    Toast.makeText(getActivity(), "No Data to show", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference addDB  = FirebaseDatabase.getInstance().getReference().child("Worker_Details");

                String comName = cName.getText().toString().trim();
                String loc = location.getText().toString().trim();
                String servi = services.getText().toString().trim();
                String rate = hRate.getText().toString().trim();

                if(TextUtils.isEmpty(comName)){
                    Toast.makeText(getActivity(), "Company Name Required", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(loc)){
                    Toast.makeText(getActivity(), "Location Required", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(servi)){
                    Toast.makeText(getActivity(), "Services Required", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(rate)){
                    Toast.makeText(getActivity(), "Hourly Rate Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    workerDetails.setCompanyName(comName);
                    workerDetails.setLocation(loc);
                    workerDetails.setServices(servi);
                    workerDetails.sethRate(rate);

                    addDB.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(workerDetails);

                    Toast.makeText(getActivity(), "Work Info Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String comName = cName.getText().toString().trim();
                final String loc = location.getText().toString().trim();
                final String servi = services.getText().toString().trim();
                final String rate = hRate.getText().toString().trim();

                final DatabaseReference[] updateDB = {FirebaseDatabase.getInstance().getReference().child("Worker_Details").child(FirebaseAuth.getInstance().getCurrentUser().getUid())};
                updateDB[0].addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                            workerDetails.setCompanyName(comName);
                            workerDetails.setLocation(loc);
                            workerDetails.setServices(servi);
                            workerDetails.sethRate(rate);

                            updateDB[0] = FirebaseDatabase.getInstance().getReference().child("Worker_Details").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            updateDB[0].setValue(workerDetails);

                            Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(), "Error while updating", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });



    }
}