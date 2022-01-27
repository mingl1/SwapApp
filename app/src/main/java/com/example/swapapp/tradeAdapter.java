package com.example.swapapp;


import static java.lang.Boolean.parseBoolean;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class tradeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private String OSIS, itemID;
    private ArrayList<MarketplaceNote> notes;
    private FragmentManager fragmentManager;
    private MarketplaceDBHelper helper;
    private ViewGroup parent;
    private ArrayList<String> dupeCheck;
    public tradeAdapter(Context context, ArrayList<MarketplaceNote> notes, String OSIS,String ID, FragmentManager fragmentManager)
    {
        this.fragmentManager=fragmentManager;
        this.context=context;
        this.OSIS=OSIS;
        this.dupeCheck=new ArrayList<String>();
        this.notes=notes;
        this.itemID=ID;
        helper = MarketplaceDBHelper.instanceOfDatabase(context);
    }

    @Override
    public MyTradeViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.inventory_cell, parent, false);
        MyTradeViewHolder vh = new MyTradeViewHolder(v);
        vh.setIsRecyclable(false);

        return vh;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MarketplaceDBHelper sqLiteManager = MarketplaceDBHelper.instanceOfDatabase(context);
        try {
            MyTradeViewHolder vh = (MyTradeViewHolder) holder;
            MarketplaceNote note = notes.get(position);
            vh.list.setVisibility(View.INVISIBLE);
            Boolean x = false;
            for(String i: dupeCheck){
                if (i.equals(note.getID())){
                    x=true;
                }
            }
            if(x){
            }
            else {
                dupeCheck.add(note.getID());
                vh.list.setVisibility(View.INVISIBLE);
                vh.title.setText(note.getName());
                vh.desc.setText(note.getDesc());
                vh.osis.setText("" + note.getOSIS());
                vh.timeCreated.setText(note.getTimeStamp());
                vh.Swap.setText("trade");
                vh.Swap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int o = note.getOSIS();
                        note.setOSIS(Integer.parseInt(OSIS));
                        note.setVisability("FALSE");

                        for (MarketplaceNote i : helper.populateInventory(Integer.parseInt(OSIS.trim()))){
                            if (i.getID().equals(itemID)){
                                System.out.println("SUCCESS");
                                i.setOSIS(o);
                                i.setVisability("FALSE");
                                helper.updateNoteInDB(i);
                            }
                        }
                        helper.updateNoteInDB(note);
                        Fragment inv = new inventoryFragment();
                        fragmentManager.beginTransaction().replace(R.id.fragment_nav_host,inv).commit();
                    }
                });

// if user1 is interested in user2's item and user2 is intersted in user1's item, the swap will happen,
// its kind of like a random swap fest, a tab where the user's listings are sorted from most recent to oldest so that
// the user can see any items that may have swapped
                if (note.getImage().equals("")) {
                    vh.item.setImageResource(R.drawable.addimage);
                } else {
                    vh.item.setImageURI(Uri.parse(note.getImage()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    public class MyTradeViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        TextView osis;
        ImageView item;
        TextView timeCreated;
        Button Swap;
        ToggleButton list;
        public MyTradeViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.cellTitle);
            desc = (TextView)itemView.findViewById(R.id.cellDesc);
            osis = (TextView) itemView.findViewById(R.id.osis);
            timeCreated=itemView.findViewById(R.id.timeCreated);
            Swap= itemView.findViewById(R.id.swaps);
            item =itemView.findViewById(R.id.photo);
            list = itemView.findViewById(R.id.show);
        }

    }}
