package com.example.workhub_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class editAcc extends Fragment {

    private static final int CAMERA_REQUEST = 1888;

    ImageView proPic;
    EditText txt1,txt2,txt3,txt4,txt5;
    Button save,uploadPro;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST){
            Bitmap pho = (Bitmap) data.getExtras().get("data");
            proPic.setImageBitmap(pho);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        txt1 = getActivity().findViewById(R.id.editcom);
        txt2 = getActivity().findViewById(R.id.editowner);
        txt3 = getActivity().findViewById(R.id.editadd);
        txt4 = getActivity().findViewById(R.id.editmail);
        txt5 = getActivity().findViewById(R.id.editNumber);
        proPic = getActivity().findViewById(R.id.propic);
        uploadPro = getActivity().findViewById(R.id.inspic);

        final Supplier supEdit;

        supEdit =  new Supplier();

        save = getActivity().findViewById(R.id.editbtnsave);

        uploadPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });



        DatabaseReference readdb = FirebaseDatabase.getInstance().getReference().child("Supplier_Details").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        readdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    txt1.setText(dataSnapshot.child("companyName").getValue().toString());
                    txt2.setText(dataSnapshot.child("ownerName").getValue().toString());
                    txt3.setText(dataSnapshot.child("address").getValue().toString());
                    txt5.setText(dataSnapshot.child("phone").getValue().toString());
                    txt4.setText(dataSnapshot.child("email").getValue().toString());
                }
                else
                    Toast.makeText(getActivity(),"No Source to Display",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String cname = txt1.getText().toString().trim();
                final String ownerName = txt2.getText().toString().trim();
                final String address = txt3.getText().toString().trim();
                final String email = txt4.getText().toString().trim();
                final String telNo = txt5.getText().toString().trim();
                DatabaseReference dbUpdate = FirebaseDatabase.getInstance().getReference().child("Supplier_Details");
                supEdit.setCompanyName(cname);
                supEdit.setOwnerName(ownerName);
                supEdit.setAddress(address);
                supEdit.setEmail(email);
                supEdit.setPhone(telNo);
                dbUpdate.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(supEdit).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(),"Changes Are Saved..!",Toast.LENGTH_SHORT).show();
                            FragmentTransaction frtMyAcc = getFragmentManager().beginTransaction();
                            frtMyAcc.replace(R.id.ediAcc, new myAccount()).commit();
                        }
                        else{
                            Toast.makeText(getActivity(),"Error in updating..."+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
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
        return inflater.inflate(R.layout.fragment_edit_acc, container, false);
    }
}