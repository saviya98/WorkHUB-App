package com.example.workhub_app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cust_Addprojects_Fragment extends Fragment {


EditText cust_pro_title,cust_pro_description,cust_pro_wage,cust_pro_days,cust_pro_tel;
Button cust_add_proj,cust_cal_apro;
DatabaseReference cust_proj_dbref;
Customer_Add_Projects custadd;
private void clear_cust_proj_controls(){

    cust_pro_days.setText("");
    cust_pro_description.setText("");
    cust_pro_title.setText("");
    cust_pro_wage.setText("");
    cust_pro_tel.setText("");



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cust_frag_addprojects,container,false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cust_pro_title =getActivity().findViewById(R.id.cus_proj_name);
        cust_pro_description =getActivity().findViewById(R.id.cust_proj_description);
        cust_pro_tel =getActivity().findViewById(R.id.cust_proj_contactno);
        cust_pro_wage =getActivity().findViewById(R.id.cust_proj_wage);
        cust_pro_days =getActivity().findViewById(R.id.cust_proj_days);
        cust_add_proj =getActivity().findViewById(R.id.cust_proj_add);
        cust_cal_apro =getActivity().findViewById(R.id.cust_proj_calc);
        custadd =new Customer_Add_Projects();


        cust_add_proj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cprotitle = cust_pro_title.getText().toString().trim();
                String cprodescr =cust_pro_description.getText().toString().trim();
                String ctelpro =cust_pro_tel.getText().toString().trim();
                int custprojwage = Integer.parseInt(cust_pro_wage.getText().toString().trim());
                int custprojdays = Integer.parseInt(cust_pro_days.getText().toString().trim());

                cust_proj_dbref = FirebaseDatabase.getInstance().getReference().child("Customer_Add_Projects");

                if(TextUtils.isEmpty(cprotitle)){
                    Toast.makeText(getActivity(),"Please enter a title",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(cprodescr)){
                    Toast.makeText(getActivity(),"Please enter a description",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(ctelpro)){
                    Toast.makeText(getActivity(),"Please enter a telephone Number",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(cust_pro_wage.getText().toString().trim())){
                    Toast.makeText(getActivity(),"Please enter a daily wage",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(cust_pro_days.getText().toString().trim())){
                    Toast.makeText(getActivity(),"Please enter a title",Toast.LENGTH_SHORT).show();
                }
                else{
                    custadd.setCust_project_title(cprotitle);
                    custadd.setCust_project_description(cprodescr);
                    custadd.setCust_project_telnumber(ctelpro);
                    custadd.setCust_project_dailywage(custprojwage);
                    custadd.setCust_project_aprox_date(custprojdays);

                    cust_proj_dbref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(custadd);

                    Toast.makeText(getActivity(),"Project added Succesfully",Toast.LENGTH_SHORT).show();

                    clear_cust_proj_controls();
                }

            }
        });


    }



}
