package com.example.workhub_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class wSearch extends Fragment {


    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> arrayAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_w_search, container, false);

        final String worker[] = {"Paint Bazar","A D P Kamal","Door Expertz"};

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.single_search_raw,R.id.saerHead,worker);

        listView = view.findViewById(R.id.workerList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    Intent intent = new Intent(view.getContext(),Worker_Search_Preview.class);
                    startActivity(intent);

                }
            }
        });

        return view;
    }
}