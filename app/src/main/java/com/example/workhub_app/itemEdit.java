package com.example.workhub_app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link itemEdit#} factory method to
 * create an instance of this fragment.
 */
public class itemEdit extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_edit, container, false);
    }

    Button editItemSaveBtn;
    EditText txt1,txt2,txt3;

    Item itmedit = new Item();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        txt1 = getActivity().findViewById(R.id.itemeditname);
        txt2 = getActivity().findViewById(R.id.itemEditdes);
        txt3 = getActivity().findViewById(R.id.editPrice);

        editItemSaveBtn = getActivity().findViewById(R.id.editsaveItm);

        editItemSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

    }
}