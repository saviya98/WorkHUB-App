package com.example.workhub_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link editItem#} factory method to
 * create an instance of this fragment.
 */
public class editItem extends Fragment {

    EditText txt1,txt2,txt3;
    Button save;
    Item item;

    DatabaseReference dbItemRead;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        txt1 = getActivity().findViewById(R.id.itemeditname);
        txt2 = getActivity().findViewById(R.id.itemEditdes);
        txt3 = getActivity().findViewById(R.id.editPrice);



        item = new Item();

        save = getActivity().findViewById(R.id.editsaveItm);

        String key = getActivity().getIntent().getStringExtra("KEY");

        dbItemRead = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Item").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key);
        dbItemRead.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    txt1.setText(dataSnapshot.child("name").getValue().toString());
                    txt2.setText(dataSnapshot.child("description").getValue().toString());
                    txt3.setText(dataSnapshot.child("price").getValue().toString());
                }
                else {
                    Toast.makeText(getActivity(),"No Source to Display",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_item, container, false);
    }
}