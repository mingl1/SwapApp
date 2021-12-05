package com.example.swapapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private ArrayList<LoginNote> notes;
    public NoteAdapter(Context context, ArrayList<LoginNote> notes)
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

        try {
            MyViewHolder vh = (MyViewHolder) holder;
            LoginNote note = notes.get(position);
            vh.title.setText(note.getName());
            vh.desc.setText(note.getPassword());
            vh.osis.setText(""+note.getOSIS());
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
        public MyViewHolder(View itemView) {
            super(itemView);
             title = (TextView) itemView.findViewById(R.id.cellTitle);
             desc = (TextView)itemView.findViewById(R.id.cellDesc);
             osis = (TextView) itemView.findViewById(R.id.osis);
        }

    }}
