package com.example.workhub_app;

import android.content.Intent;
import android.icu.text.Transliterator;
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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class wRequests extends Fragment {

    RecyclerView reqRecView;
    wRequestRecAdapter wRequestRecAdapter;

    //ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_w_requests, container, false);
//        final String Requests[] = {"Paint Job","House Paint","Door Color"};
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.single_request_raw,R.id.reqHead,Requests);
//
//        listView = view.findViewById(R.id.list_req_view);
//        listView.setAdapter(arrayAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if(i==0){
//                    Toast.makeText(getContext(),"Loading",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(view.getContext(),Worker_Request_Preview.class);
//                    startActivity(intent);
//
//
//                }
//            }
//        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        reqRecView = (RecyclerView)getActivity().findViewById(R.id.wReqRecView);
        reqRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<wReqLocationModel> options =
                new FirebaseRecyclerOptions.Builder<wReqLocationModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Customer Details"),wReqLocationModel.class).build();

        wRequestRecAdapter = new wRequestRecAdapter(options);
        reqRecView.setAdapter(wRequestRecAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        wRequestRecAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        wRequestRecAdapter.stopListening();
    }
}