package com.example.workhub_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class addedItems extends Fragment {

      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          View v = inflater.inflate(R.layout.fragment_added_items, container, false);

          if (container != null) {
              container.removeAllViews();
          }

          Button editItemD;
          editItemD = v.findViewById(R.id.viewDetills);

          editItemD.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                  fragmentTransaction.replace(R.id.editItem,new editItem()).commit();
              }
          });

        // Inflate the layout for this fragment
        return v;
    }
}