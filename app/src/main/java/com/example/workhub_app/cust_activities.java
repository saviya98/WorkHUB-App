package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.workhub_app.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class cust_activities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_activities);

        BottomNavigationView cus_bottomNav = findViewById(R.id.cust_bottomnavigationbar);
        cus_bottomNav.setOnNavigationItemSelectedListener(cus_navListner);
    }
    BottomNavigationView.OnNavigationItemSelectedListener cus_navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch(item.getItemId()){


                case R.id.cust_my_profile:
                    selectedFragment = new Cust_Profile_Fragment();
                    break;

                case R.id.cust_nav_home:
                    selectedFragment = new Cust_Home_Fragment();
                    break;

                case R.id.cust_addproject:
                    selectedFragment = new Cust_Addprojects_Fragment();
                    break;


            }

                getSupportFragmentManager().beginTransaction().replace(R.id.cust_fragment_container,selectedFragment).commit();
                return true;
        }

    };


}