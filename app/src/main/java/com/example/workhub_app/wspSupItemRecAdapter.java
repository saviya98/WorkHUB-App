package com.example.workhub_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class wspSupItemRecAdapter extends FirebaseRecyclerAdapter<wSupItemModel,wspSupItemRecAdapter.wSupItemRecViewHolder> {

    public wspSupItemRecAdapter(@NonNull FirebaseRecyclerOptions<wSupItemModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull wSupItemRecViewHolder holder, int position, @NonNull wSupItemModel model) {
        holder.itemName.setText(model.getName());
        Glide.with(holder.img.getContext());

    }

    @NonNull
    @Override
    public wSupItemRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_search_raw,parent,false);
        return new wSupItemRecViewHolder(view);
    }

    class wSupItemRecViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        CircleImageView img;
        View view;

        public wSupItemRecViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = (TextView)itemView.findViewById(R.id.saerHead);
            img = (CircleImageView) itemView.findViewById(R.id.searchView);
            view = itemView;
        }
    }
}
