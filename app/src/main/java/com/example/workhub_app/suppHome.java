package com.example.workhub_app;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link suppHome#} factory method to
 * create an instance of this fragment.
 */
public class suppHome extends Fragment {


    Button btnViewDet;
    TextView txt1;
    DatabaseReference dbR;
    RecyclerView recyclerView;
    myAdapter myadapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getActivity().findViewById(R.id.recview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<Item> options =
                new FirebaseRecyclerOptions.Builder<Item>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Item").child(FirebaseAuth.getInstance().getCurrentUser().getUid()), Item.class)
                .build();

        myadapter = new myAdapter(options);
        recyclerView.setAdapter(myadapter);


   }

   public void onStart(){
        super.onStart();
        myadapter.startListening();
   }

    @Override
    public void onStop() {
        super.onStop();
        myadapter.stopListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supp_home, container, false);
    }
}