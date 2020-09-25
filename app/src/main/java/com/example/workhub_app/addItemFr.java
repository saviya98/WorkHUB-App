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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class addItemFr extends Fragment {

    EditText iname, ides,iprice;
    Button addbtn,addimage;
    DatabaseReference dbf;
    ImageView imageView;


    Item item = new Item();

    private void clearControlls(){
        iname.setText("");
        ides.setText("");
        iprice.setText("");
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        iname = getActivity().findViewById(R.id.itemName);
        ides = getActivity().findViewById(R.id.desI);
        iprice = getActivity().findViewById(R.id.priceI);
        addbtn = getActivity().findViewById(R.id.addit);
        addimage = getActivity().findViewById(R.id.picinsert);
        imageView = getActivity().findViewById(R.id.imageButton);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbf = FirebaseDatabase.getInstance().getReference().child("Item");

                 String itemN = iname.getText().toString().trim();
                 String description = ides.getText().toString().trim();
                 String  price = iprice.getText().toString().trim();


                if(TextUtils.isEmpty(iname.getText().toString()))
                    Toast.makeText(getActivity(),"Please Enter a Item Name", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(ides.getText().toString()))
                    Toast.makeText(getActivity(),"Please Enter a description", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(iprice.getText().toString()))
                    Toast.makeText(getActivity(),"Please the price of the product", Toast.LENGTH_SHORT).show();
                else{
                    //take inputs
                    item.setName(itemN);
                    item.setDescription(description);
                    item.setPrice(price);

                    //insert to db
                    dbf.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(item);

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