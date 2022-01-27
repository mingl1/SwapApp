package com.example.swapapp;


import static com.example.swapapp.LoginNote.getNoteForID;
import static com.example.swapapp.LoginNote.noteArrayList;
import static java.lang.Boolean.parseBoolean;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
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
import java.util.Objects;

public class MarketplaceNoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private int OSIS;
    private DBHelper users;
    //loginnote of person logged in
    private LoginNote userz;
    private ArrayList<MarketplaceNote> notes;
    public MarketplaceNoteAdapter(Context context, ArrayList<MarketplaceNote> notes, int OSIS)
    {
        this.context=context;
        this.OSIS=OSIS;
        System.out.println("OSIS Is" + OSIS);
        this.notes=notes;
        this.users=DBHelper.instanceOfDatabase(context);

//
//        users.populateNoteListArray();

        for (LoginNote note : LoginNote.noteArrayList){

            if (note.getOSIS()==OSIS){
                System.out.println("User OSIS IS: "+note.getOSIS());
                userz=note;
            }
    }}

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.note_cell, parent, false);

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MarketplaceDBHelper sqLiteManager = MarketplaceDBHelper.instanceOfDatabase(context);
        DBHelper user = DBHelper.instanceOfDatabase(context);
        try {
            MyViewHolder vh = (MyViewHolder) holder;
            MarketplaceNote note = notes.get(position);
            vh.interest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //takeable by
                        note.setInterested(note.getInterested()+OSIS+"    ");
                        System.out.println("CURRENT INTEREST: "+note.getInterested());
                        //add osis that can trade with current user
//                        userz.addInterest(""+note.getOSIS());
                    }
                    else{
                        String interest[] = note.getInterested().split("\\s+");
                        String newInterest="";
                        for(int i=0; i<interest.length; i++){
                            if (!interest[i].equals(""+OSIS)){
                                newInterest+=interest[i]+"    ";
                            }
                        }
                        System.out.println("new interest :"+newInterest+";;;");
//                        userz.removeInterest(OSIS);
                        note.setInterested(newInterest);
                    }
//                    user.updateNoteInDB(userz);
                    sqLiteManager.updateNoteInDB(note);
                }

            });
            vh.title.setText(note.getName());
            vh.desc.setText(note.getDesc());
            vh.osis.setText(""+note.getOSIS());
            vh.timeCreated.setText(note.getTimeStamp());

// if user1 is interested in user2's item and user2 is intersted in user1's item, the swap will happen,
// its kind of like a random swap fest, a tab where the user's listings are sorted from most recent to oldest so that
// the user can see any items that may have swapped

            String interest[] = note.getInterested().split("\\s+");
            for(int i=0; i<interest.length; i++){
                if (interest[i].equals(""+OSIS)){
                    vh.interest.setChecked(true);
                }
            }
            if(note.getImage().equals("")){
                vh.item.setImageResource(R.drawable.addimage);
            }
            else{
            vh.item.setImageURI(Uri.parse(note.getImage()));
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
