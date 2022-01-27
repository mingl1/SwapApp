package com.example.swapapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class tradeFragment extends Fragment {
    private RecyclerView recyclerView;
    private String itemID, OSIS;
    private MarketplaceNote item;
    private DBHelper users;
    private LoginNote user;
    private MarketplaceDBHelper helper;
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
        helper = MarketplaceDBHelper.instanceOfDatabase(getActivity().getApplicationContext());
        itemID=getArguments().getString("itemID");
        users=DBHelper.instanceOfDatabase(getActivity().getApplicationContext());
        OSIS = ""+getActivity().getIntent().getExtras().getInt("OSIS");
        recyclerView= v.findViewById(R.id.tradeView);
        user=LoginNote.getNoteForID(getActivity().getIntent().getExtras().getInt("OSIS"));
        tradeAdapter noteAdapter = new tradeAdapter(this.getContext(), trades(itemID, OSIS), OSIS,itemID, getParentFragmentManager());
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return v;

    }


    public ArrayList<MarketplaceNote> trades(String id, String osis) {
        ArrayList<MarketplaceNote> tradable = new ArrayList<>();
        for (MarketplaceNote i: helper.populateInventory(Integer.parseInt(osis))){
            if (i.getID().equals(id)){
                item=i;
                break;
            }
        }
//        ArrayList<LoginNote> s = users.populateNoteListArray();
//        ArrayList<LoginNote> z = new ArrayList<>();
//        ArrayList<MarketplaceNote> list = a.populateMarketPlace(Integer.parseInt(OSIS));
//        for (LoginNote i : s){
//            String [] split = i.getInterests().split("\\s+");
//            System.out.println(i.getInterests());
//            for (String j: split){
//                if (j.equalsIgnoreCase(osis)){
//                    z.add(i);
//                    break;
//                }
//            }
//        }
//
//        for (MarketplaceNote i: list){
//            for (LoginNote j :z){
//                if (j.getInterests().contains(""+i.getOSIS())){
//                    System.out.println("WORKING TRADABLE");
//                    tradable.add(i);
//                }
//            }
//        }
        String [] splitted = item.getInterested().trim().split("\\s+");
        for(MarketplaceNote i: helper.populateMarketPlace(Integer.parseInt(OSIS))){
            for(String j: splitted){
                if((""+i.getOSIS()).equals(j)){
                    tradable.add(i);
                    break;
                }
            }
        }
        return tradable;
    }
}