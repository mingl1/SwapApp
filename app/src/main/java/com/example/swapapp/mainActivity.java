package com.example.swapapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class mainActivity extends AppCompatActivity {
    private int userOSIS;
    private FloatingActionButton addListing;
    private MarketplaceDBHelper helper;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        bundle = new Bundle();
        userOSIS=getIntent().getExtras().getInt("OSIS");
        System.out.println(userOSIS);
        bundle.putInt("OSIS", userOSIS);
        helper=MarketplaceDBHelper.instanceOfDatabase(this);
        helper.populateInventory(userOSIS);
        helper.populateMarketPlace(userOSIS);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setBackground(null);
      //  NavDestination orderDestination = navController.getGraph().findNode(R.id.my_nav);
//        orderDestination.addArgument("OSIS", argument);

        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        addListing=(FloatingActionButton)findViewById(R.id.floatingbutton);

        addListing.setOnClickListener(add);
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
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    MarketplaceNote.noteArrayList.clear();
                    MarketplaceNote.inventory.clear();
                    getApplicationContext().startActivity(i);
                    break;
                case R.id.Search:
                    //selected_fragment = new SearchFragment();
                    break;
                case R.id.Inventory:
                    selected_fragment = new inventoryFragment();
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

    private View.OnClickListener add = new View.OnClickListener() {
        public void onClick(View v) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_nav_host,new addlistings()).commit();}

        };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        String s = uri.toString();
        ImageView img = findViewById(R.id.addPhoto);
        img.setImageURI(uri);
        img.setTag(s);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
