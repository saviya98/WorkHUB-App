package com.example.workhub_app;

import android.content.Intent;
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

public class cus_proj_adapter extends FirebaseRecyclerAdapter<cust_proj_model,cus_proj_adapter.my_proj_viewholder_1> {
  public cus_proj_adapter(@NonNull FirebaseRecyclerOptions<cust_proj_model> options) {
    super(options);
  }

  @Override
  protected void onBindViewHolder(@NonNull my_proj_viewholder_1 holder, int position, @NonNull cust_proj_model model) {

    final String key1 =getRef(position).getKey();
    holder.view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      Intent intent = new Intent(view.getContext(),Target_my_proj.class);
      intent.putExtra("Key12",key1);
      view.getContext().startActivity(intent);

    }
  });

    holder.cp1title.setText(model.getCust_project_title());
    holder.cp1description.setText(model.getCust_project_description());

  }

  @NonNull
  @Override
  public my_proj_viewholder_1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view12 = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_proj1,parent,false);
    return new my_proj_viewholder_1(view12);
  }

  class my_proj_viewholder_1 extends RecyclerView.ViewHolder{
    View view;
    ImageView img;
    TextView cp1title,cp1description,cp1contNo;
    public my_proj_viewholder_1(@NonNull View itemView) {
      super(itemView);
      img = (ImageView)itemView.findViewById(R.id.cus_wrk_img11);
      cp1title =(TextView)itemView.findViewById(R.id.projtitle1);
      cp1description =(TextView)itemView.findViewById(R.id.projdescription1);
      cp1contNo =(TextView)itemView.findViewById(R.id.cust_proj_contactno);
      view =itemView;

    }




  }

}
