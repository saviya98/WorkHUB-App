package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class supplierHomeFrag extends AppCompatActivity {


    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_supplier_home_frag);


        bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) bottomNaviMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new suppHome()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNaviMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment fr = null;

                    switch (item.getItemId()){

                        case R.id.suppHome:
                            fr = new suppHome();
                            break;
                        case R.id.addItemFr:
                            fr = new addItemFr();
                            break;
                        case R.id.myAccount:
                            fr = new myAccount();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fr).commit();
                    return true;
                }
            };


}