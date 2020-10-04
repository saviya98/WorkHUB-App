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

import de.hdodenhof.circleimageview.CircleImageView;

public class wSupRecAdapter extends FirebaseRecyclerAdapter<wSupModel,wSupRecAdapter.wSupRecViewHolder> {

    public wSupRecAdapter(@NonNull FirebaseRecyclerOptions<wSupModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull wSupRecViewHolder holder, int position, @NonNull wSupModel model) {
        holder.comName.setText(model.getCompanyName());
        Glide.with(holder.img.getContext());

        final String key =  getRef(position).getKey();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Worker_Search_Preview.class);
                intent.putExtra("KEY",key);
                v.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public wSupRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_search_raw,parent,false);
        return new wSupRecViewHolder(view);
    }

    class wSupRecViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView comName;
        public wSupRecViewHolder(@NonNull View itemView) {
            super(itemView);

           img = (ImageView) itemView.findViewById(R.id.searchView);
           comName = (TextView)itemView.findViewById(R.id.saerHead);
        }


    }
}
