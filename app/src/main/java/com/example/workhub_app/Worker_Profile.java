package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
//import androidx.navigation.NavController;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Worker_Profile extends AppCompatActivity {
    private Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker__profile);

        toolbar = findViewById(R.id.signUpToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");

        bottomNavigationView = findViewById(R.id.bottom_wnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new wProfile()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            Toolbar toolbar1 = findViewById(R.id.signUpToolBar);
            setSupportActionBar(toolbar1);

            switch (item.getItemId())
            {
                case R.id.wHome:
                    fragment = new wHome();
                    getSupportActionBar().setTitle("Home");
                    break;
                case R.id.wProfile:
                    fragment=new wProfile();
                    getSupportActionBar().setTitle("Profile");
                    break;
                case R.id.wRequests:
                    fragment = new wRequests();
                    getSupportActionBar().setTitle("Requests");
                    break;
                case R.id.wSearch:
                    fragment=new wSearch();
                    getSupportActionBar().setTitle("Search");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            return true;
        }
    };

}