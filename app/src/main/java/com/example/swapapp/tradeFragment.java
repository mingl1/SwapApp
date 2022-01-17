package com.example.swapapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class tradeFragment extends Fragment {
    private RecyclerView recyclerView;
    private String itemID, OSIS;

    private DBHelper users;
    private LoginNote user;
    private MarketplaceDBHelper a;
    public tradeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trade, container, false);
        itemID=getArguments().getString("itemID");
        users=DBHelper.instanceOfDatabase(getActivity().getApplicationContext());
        OSIS = ""+getActivity().getIntent().getExtras().getInt("OSIS");
        recyclerView= v.findViewById(R.id.tradeView);
        user=LoginNote.getNoteForID(getActivity().getIntent().getExtras().getInt("OSIS"));
        tradeAdapter noteAdapter = new tradeAdapter(this.getContext(), trades(itemID, OSIS), itemID, getParentFragmentManager());
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return v;

    }


    public ArrayList<MarketplaceNote> trades(String id, String osis) {
        ArrayList<MarketplaceNote> tradable = new ArrayList<>();
        ArrayList<Integer> show = new ArrayList<>();
        for (LoginNote i : LoginNote.noteArrayList){
            String [] split = i.getInterests().split("\\s+");
            System.out.println(i.getInterests());
            for (String j: split){
                if (j.equalsIgnoreCase(osis)){
                    show.add(i.getOSIS());
                }
            }
        }

        for (MarketplaceNote i: MarketplaceNote.noteArrayList){
            for (int j: show){
                System.out.println(i.getOSIS());
                if (j==i.getOSIS()){
                    System.out.println("WORKING TRADABLE");
                    tradable.add(i);
                }
            }
        }
        return tradable;
    }
}