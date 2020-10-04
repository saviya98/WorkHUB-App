package com.example.workhub_app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class addItemFr extends Fragment {

    EditText iname, ides,iprice;
    Button addbtn;
    DatabaseReference dbf;

    Item item;

    private void clearControlls(){
        iname.setText("");
        ides.setText("");
        iprice.setText("");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        iname = getActivity().findViewById(R.id.itemname);
        ides = getActivity().findViewById(R.id.des);
        iprice = getActivity().findViewById(R.id.price);
        addbtn = getActivity().findViewById(R.id.addit);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbf = FirebaseDatabase.getInstance().getReference().child("Item");

                if(TextUtils.isEmpty(iname.getText().toString()))
                    Toast.makeText(getActivity(),"Please Enter a Company Name", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(ides.getText().toString()))
                    Toast.makeText(getActivity(),"Please Enter a Owner Name", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(iprice.getText().toString()))
                    Toast.makeText(getActivity(),"Please Enter an Address of the supplier", Toast.LENGTH_SHORT).show();
                else{
                    //take inputs
                    item.setName(iname.getText().toString().trim());
                    item.setDescription(ides.getText().toString().trim());
                    item.setPrice(iprice.getText().toString().trim());

                    //insert to db
                    dbf.child("item").setValue(item);

                    //call success msg
                    Toast.makeText(getActivity(),"Item is added..!",Toast.LENGTH_SHORT).show();

                    clearControlls();

                }

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_item, container, false);

        return v;
    }

}