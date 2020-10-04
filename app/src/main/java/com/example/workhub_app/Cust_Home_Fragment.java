package com.example.workhub_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Cust_Home_Fragment extends Fragment {
    RecyclerView cust_recview;
    cusAdapter cusadapt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cust_frag_home,container,false);


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cust_recview = (RecyclerView)getActivity().findViewById(R.id.cust_recview);
        cust_recview.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<cus_model> cusoptions =
            new FirebaseRecyclerOptions.Builder<cus_model>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Worker_Details"),cus_model.class)
                    .build();

        cusadapt =new cusAdapter(cusoptions);
        cust_recview.setAdapter(cusadapt);


    }

    @Override
    public void onStart() {
        super.onStart();
        cusadapt.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        cusadapt.stopListening();
    }
}
