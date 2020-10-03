package com.example.workhub_app;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class myAdapter extends FirebaseRecyclerAdapter<Item,myAdapter.myViewHolder>  {


    ArrayList<Item> items;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Item").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    public myAdapter(@NonNull FirebaseRecyclerOptions<Item> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Item model) {

        holder.txt.setText(model.getName());
        Glide.with(holder.img.getContext()).load(model.getImageUrl()).into(holder.img);

        final String keyV = getRef(position).getKey();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),editItemAc.class);
                intent.putExtra("KEY", keyV);
                view.getContext().startActivity(intent);

            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView txt;
        View view;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.imgViewRec);
            txt = (TextView)itemView.findViewById(R.id.diplayName);
            view = itemView;
        }
    }
}
