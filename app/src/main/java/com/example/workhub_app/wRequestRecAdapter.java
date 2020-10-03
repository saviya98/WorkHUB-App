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

public class wRequestRecAdapter extends FirebaseRecyclerAdapter<wReqLocationModel,wRequestRecAdapter.wRequestRecHolder> {


    public wRequestRecAdapter(@NonNull FirebaseRecyclerOptions<wReqLocationModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull wRequestRecHolder holder, int position, @NonNull wReqLocationModel model) {
        holder.loc.setText(model.getLocation());

        final String key = getRef(position).getKey();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Worker_Request_Preview.class);
                intent.putExtra("KEY1",key);
                v.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public wRequestRecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_request_raw,parent,false);
        return  new wRequestRecHolder(view);
    }

    class wRequestRecHolder extends RecyclerView.ViewHolder{
        TextView loc;
        public wRequestRecHolder(@NonNull View itemView) {
            super(itemView);

            loc = (TextView)itemView.findViewById(R.id.reqHead);
        }
    }
}
