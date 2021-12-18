package com.example.swapapp;


import static java.lang.Boolean.parseBoolean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MarketplaceNoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private ArrayList<MarketplaceNote> notes;
    public MarketplaceNoteAdapter(Context context, ArrayList<MarketplaceNote> notes)
    {
        this.context=context;
        this.notes=new ArrayList<>();
        this.notes.addAll(notes);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.note_cell, parent, false);

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MarketplaceDBHelper sqLiteManager = MarketplaceDBHelper.instanceOfDatabase(context);
        try {
            MyViewHolder vh = (MyViewHolder) holder;
            MarketplaceNote note = notes.get(position);
            vh.interest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        note.setInterested("true");
                        sqLiteManager.updateNoteInDB(note);
                    } else {
                        note.setInterested("false");
                        sqLiteManager.updateNoteInDB(note);
                    }
                }
            });
            vh.title.setText(note.getName());
            vh.desc.setText(note.getDesc());
            vh.osis.setText(""+note.getOSIS());
            vh.timeCreated.setText(note.getTimeStamp());
            vh.interest.setChecked(parseBoolean(note.getInterested()));
            vh.item.setImageResource(R.drawable.lock);


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


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        TextView osis;
        ImageView item;
        TextView timeCreated;
        ToggleButton interest;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.cellTitle);
            desc = (TextView)itemView.findViewById(R.id.cellDesc);
            osis = (TextView) itemView.findViewById(R.id.osis);
            timeCreated=itemView.findViewById(R.id.timeCreated);
            interest= itemView.findViewById(R.id.interested);
            item =itemView.findViewById(R.id.photo);
        }

    }}
