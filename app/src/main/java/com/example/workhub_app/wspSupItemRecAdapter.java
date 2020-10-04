package com.example.workhub_app;

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

import de.hdodenhof.circleimageview.CircleImageView;

public class wspSupItemRecAdapter extends FirebaseRecyclerAdapter<wSupItemModel,wspSupItemRecAdapter.wSupItemRecViewHolder> {

    public wspSupItemRecAdapter(@NonNull FirebaseRecyclerOptions<wSupItemModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull wSupItemRecViewHolder holder, int position, @NonNull wSupItemModel model) {
        holder.itemName.setText(model.getName());
        holder.itemPrice.setText(model.getPrice());
        Glide.with(holder.img.getContext());

    }

    @NonNull
    @Override
    public wSupItemRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_worker_item_recview,parent,false);
        return new wSupItemRecViewHolder(view);
    }

    class wSupItemRecViewHolder extends RecyclerView.ViewHolder{
        TextView itemName,itemPrice;
        ImageView img;
        View view;

        public wSupItemRecViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = (TextView)itemView.findViewById(R.id.wokitemrecnamesinraw);
            itemPrice = (TextView)itemView.findViewById(R.id.wokpriceTag);
            img = (ImageView) itemView.findViewById(R.id.wokitenrecsinraw);
            view = itemView;
        }
    }
}
