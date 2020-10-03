package com.example.workhub_app;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class wrpProjectRecAdapter extends FirebaseRecyclerAdapter<wReqModel,wrpProjectRecAdapter.wrpProjViewHolder> {

    public wrpProjectRecAdapter(@NonNull FirebaseRecyclerOptions<wReqModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull wrpProjViewHolder holder, int position, @NonNull wReqModel model) {
        holder.projTitle.setText(model.getCust_project_title());

        final String key = getRef(position).getKey();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Worker_Cus_Project_Preview.class);
                intent.putExtra("KEY2",key);
                v.getContext().startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public wrpProjViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_request_raw,parent,false);
        return new wrpProjViewHolder(view);
    }

    class wrpProjViewHolder extends RecyclerView.ViewHolder{
        TextView projTitle;
        View view;

        public wrpProjViewHolder(@NonNull View itemView) {
            super(itemView);

            projTitle = (TextView)itemView.findViewById(R.id.reqHead);
            view = itemView;
        }
    }
}
