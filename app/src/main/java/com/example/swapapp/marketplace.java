package com.example.swapapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class marketplace extends Fragment {
    private RecyclerView recyclerView;
    private int userOSIS;
    private MarketplaceDBHelper helper;
    public marketplace() {
        // Required empty public constructor
    }

//    // TODO: Rename and change types and number of parameters
//    public static marketplace newInstance(String param1, String param2) {
//        marketplace fragment = new marketplace();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_marketplace, container, false);
        helper = MarketplaceDBHelper.instanceOfDatabase(getActivity().getApplicationContext());
        userOSIS=getActivity().getIntent().getExtras().getInt("OSIS");
        initWidgets(v);
        setNoteAdapter();


        return v;
    }

    private void initWidgets(View v)
    {
        recyclerView = v.findViewById(R.id.recyclerView);
    }

//    private void loadFromDBToMemory()
//    {
//        MarketplaceDBHelper sqLiteManager = MarketplaceDBHelper.instanceOfDatabase(getActivity().getApplicationContext());
//        sqLiteManager.populateNoteListArray(userOSIS);
//    }

    private void setNoteAdapter()
    {
        System.out.println("CURRENT OSIS:"+userOSIS);
        MarketplaceNoteAdapter noteAdapter = new MarketplaceNoteAdapter(getActivity().getApplicationContext(), helper.populateMarketPlace(userOSIS), getActivity().getIntent().getExtras().getInt("OSIS"));
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}