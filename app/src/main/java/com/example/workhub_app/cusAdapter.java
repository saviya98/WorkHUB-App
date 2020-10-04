package com.example.workhub_app;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class cusAdapter extends FirebaseRecyclerAdapter<cus_model,cusAdapter.cus_work_viewholder>
{

    public cusAdapter(@NonNull FirebaseRecyclerOptions<cus_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull cus_work_viewholder holder, int position, @NonNull cus_model model) {
        holder.cw_name.setText(model.getName());
        holder.cw_emai.setText(model.getEmail());
        holder.cw_industry.setText(model.getIndustrytype());

    }

    @NonNull
    @Override
    public cus_work_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_wrk_singlerow,parent,false);
        return new cus_work_viewholder(view);
    }

    class cus_work_viewholder extends RecyclerView.ViewHolder
{
    ImageView cw_img;
    TextView cw_name,cw_emai,cw_industry;
    public cus_work_viewholder(@NonNull View itemView) {
        super(itemView);
        cw_img =(ImageView)itemView.findViewById(R.id.cus_wrk_img1);
        cw_name =(TextView)itemView.findViewById(R.id.cus_work_name);
        cw_emai =(TextView)itemView.findViewById(R.id.cus_work_email);
        cw_industry =(TextView)itemView.findViewById(R.id.cus_work_itype);
    }
}



}
