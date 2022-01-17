package com.example.swapapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class mainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private int userOSIS;
    private FloatingActionButton addListing;
    private MarketplaceDBHelper helper;
    private DBHelper userHelper;
    private Bundle bundle;
  //  private RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        bundle = new Bundle();
        userHelper=DBHelper.instanceOfDatabase(this);
        userOSIS=getIntent().getExtras().getInt("OSIS");
        bundle.putInt("OSIS", userOSIS);
        helper=MarketplaceDBHelper.instanceOfDatabase(this);
        helper.populateInventory(userOSIS);
        helper.populateMarketPlace(userOSIS);
        userHelper.populateNoteListArray();
//        search.setSubmitButtonEnabled(true);
//        search.setOnQueryTextListener(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        recyclerView = findViewById(R.id.recyclerView);
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
                    selected_fragment = new searchFragment();
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (query != null){
            searchDatabase(query);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if (query !=null){
            searchDatabase(query);
        }
        return true;
    }

    private void searchDatabase(String query){
        String search = "%"+query+"%";
//        MarketplaceNoteAdapter noteAdapter = new MarketplaceNoteAdapter(this, helper.search(search), userOSIS);
//        recyclerView.setAdapter(noteAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
