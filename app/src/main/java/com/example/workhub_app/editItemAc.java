package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class editItemAc extends AppCompatActivity {

    EditText txt1,txt2,txt3;
    Button save,del;
    Item item;

    DatabaseReference dbItemRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        txt1 = findViewById(R.id.itemeditname);
        txt2 = findViewById(R.id.itemEditdes);
        txt3 = findViewById(R.id.editPrice);

        item = new Item();

        save = findViewById(R.id.editsaveItm);
        del = findViewById(R.id.delItem);

        final String key = getIntent().getStringExtra("KEY");
        System.out.println(key);
        dbItemRead = FirebaseDatabase.getInstance().getReference().child("Item").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key);
        dbItemRead.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    txt1.setText(dataSnapshot.child("name").getValue().toString());
                    txt2.setText(dataSnapshot.child("description").getValue().toString());
                    txt3.setText(dataSnapshot.child("price").getValue().toString());
                }
                else {
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference[] delDb = {FirebaseDatabase.getInstance().getReference().child("Item").child(FirebaseAuth.getInstance().getCurrentUser().getUid())};
                delDb[0].addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(key)){
                            delDb[0] = FirebaseDatabase.getInstance().getReference().child("Item").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key);
                            delDb[0].removeValue();
                            Toast.makeText(getApplicationContext(),"Item is Deleted",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(editItemAc.this,supplierHomeFrag.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"No source to delete",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String iname = txt1.getText().toString().trim();
                final String des = txt2.getText().toString().trim();
                final String price = txt3.getText().toString().trim();
                DatabaseReference dbUpdate = FirebaseDatabase.getInstance().getReference().child("Item");
                item.setName(iname);
                item.setDescription(des);
                item.setPrice(price);
                dbUpdate.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key).setValue(item).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Changes Are Saved..!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(editItemAc.this,supplierHomeFrag.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Error in updating..."+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }


}