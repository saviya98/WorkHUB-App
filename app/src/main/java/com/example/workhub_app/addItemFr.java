package com.example.workhub_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class addItemFr extends Fragment {

    private static  final  int CAMERA_REQUEST = 1888;

    EditText iname, ides,iprice , discount;
    Button addbtn,addimage;
    DatabaseReference dbf;
    ImageView imageView;

    Uri imgUrl;
    StorageReference reference;

    Item item = new Item();

    private void clearControlls(){
        iname.setText("");
        ides.setText("");
        iprice.setText("");
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        reference = FirebaseStorage.getInstance().getReference();
        iname = getActivity().findViewById(R.id.itemName);
        ides = getActivity().findViewById(R.id.desI);
        iprice = getActivity().findViewById(R.id.priceI);
        addbtn = getActivity().findViewById(R.id.addit);
        addimage = getActivity().findViewById(R.id.picinsert);
        imageView = getActivity().findViewById(R.id.imageButton);


        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbf = FirebaseDatabase.getInstance().getReference().child("Item");
                 String itemN = iname.getText().toString().trim();
                 String description = ides.getText().toString().trim();
                 String  price = iprice.getText().toString().trim();

                if(TextUtils.isEmpty(iname.getText().toString()))
                    Toast.makeText(getActivity(),"Please Enter a Item Name", Toast.LENGTH_SHORT).show();
                if(TextUtils.isEmpty(ides.getText().toString()))
                    Toast.makeText(getActivity(),"Please Enter a description", Toast.LENGTH_SHORT).show();
                if(TextUtils.isEmpty(iprice.getText().toString()))
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST ){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_item, container, false);
        return v;
    }
}