package com.example.workhub_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class wSearch extends Fragment {

    RecyclerView recyclerView;
    wSupRecAdapter wSupRecAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_w_search, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.suplierWorkerRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<wSupModel> options =
                new FirebaseRecyclerOptions.Builder<wSupModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Supplier_Details"),wSupModel.class).build();

        wSupRecAdapter = new wSupRecAdapter(options);
        recyclerView.setAdapter(wSupRecAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        wSupRecAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        wSupRecAdapter.stopListening();
    }
}