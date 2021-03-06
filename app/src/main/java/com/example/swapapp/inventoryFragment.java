package com.example.swapapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class inventoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private int userOSIS;
    private MarketplaceDBHelper helper;

    public inventoryFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_inventory, container, false);
        userOSIS=getActivity().getIntent().getExtras().getInt("OSIS");
        helper= MarketplaceDBHelper.instanceOfDatabase(getActivity().getApplicationContext());
        initWidgets(v);

        setNoteAdapter();


        return v;
    }

    private void initWidgets(View v)
    {
        recyclerView = v.findViewById(R.id.inventoryView);
    }

//    private void loadFromDBToMemory()
//    {
//        MarketplaceDBHelper sqLiteManager = MarketplaceDBHelper.instanceOfDatabase(requireContext());
//        sqLiteManager.populateInventory(userOSIS);
//    }

    private void setNoteAdapter()
    {
        InventoryAdapter noteAdapter = new InventoryAdapter(this.getContext(), helper.populateInventory(userOSIS), userOSIS, getParentFragmentManager());
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}