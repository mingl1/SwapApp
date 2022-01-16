package com.example.swapapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class mainActivity extends AppCompatActivity {
    private int userOSIS;
    private Button addListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        Bundle bundle = new Bundle();
        userOSIS=getIntent().getExtras().getInt("OSIS");
        System.out.println(userOSIS);
        bundle.putInt("OSIS", userOSIS);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_nav_host);
        navHostFragment.setArguments(bundle);
        NavArgument argument = new NavArgument.Builder().setDefaultValue(userOSIS).build();
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        bottomNavigationView.setBackground(null);
      //  NavDestination orderDestination = navController.getGraph().findNode(R.id.my_nav);
//        orderDestination.addArgument("OSIS", argument);

        bottomNavigationView.getMenu().getItem(2).setEnabled(false);


        bottomNavigationView.setOnItemSelectedListener(navListener);

    }

    private NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selected_fragment = null;

            switch(item.getItemId()){
                case R.id.marketplace:
                    selected_fragment = new marketplace();
                    break;
                case R.id.logout:
                    Intent i = new Intent(getApplicationContext(), loginScreen.class);
                    getApplicationContext().startActivity(i);
                    break;
                case R.id.Search:
                    //selected_fragment = new SearchFragment();
                    break;
                case R.id.chat:
                   // selected_fragment = new chatFragment();
                    break;


            }
            if (selected_fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_nav_host,selected_fragment).commit();}
            else{
                finish();
            }
            return true;
        }

    };
}