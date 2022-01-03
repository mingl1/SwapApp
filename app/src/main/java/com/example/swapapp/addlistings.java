package com.example.swapapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;



public class addlistings extends Fragment {



    // TODO: Rename and change types of parameters
    private EditText titleEditText, descEditText, ID;
    private Button addItem, deleteButton;
    private MarketplaceNote selectedNote;

    public addlistings() {
        // Required empty public constructor
    }


//    // TODO: Rename and change types and number of parameters
//    public static addlistings newInstance(String param1, String param2) {
//        addlistings fragment = new addlistings();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_addlistings, container, false);
        initWidgets(v);
        checkForEditNote();

        return v;
    }

    private void initWidgets(View v)
    {
        titleEditText = v.findViewById(R.id.titleEditText);
        descEditText = v.findViewById(R.id.descriptionEditText);
        deleteButton = v.findViewById(R.id.deleteNoteButton);
        ID = v.findViewById(R.id.OSIS);
        addItem=v.findViewById(R.id.addItem);
        addItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveNote(v);
            }
        });

    }

    private void checkForEditNote()
    {
        deleteButton.setVisibility(View.INVISIBLE);
        // editing listing to be implemented

//        Intent previousIntent = getIntent();
//
//        int passedNoteID = previousIntent.getIntExtra(Note.NOTE_EDIT_EXTRA, -1);
//        selectedNote = MarketplaceNote.getNoteForID(passedNoteID);
//
//        if (selectedNote != null)
//        {
//            titleEditText.setText(selectedNote.getName());
//            descEditText.setText(selectedNote.getDesc());
//            ID.setText(selectedNote.getOSIS());
//        }
//        else
//        {
//            deleteButton.setVisibility(View.INVISIBLE);
//        }
    }

    public void saveNote(View view)
    {
        MarketplaceDBHelper sqLiteManager = MarketplaceDBHelper.instanceOfDatabase(this.getContext());
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());
        String OSIS = String.valueOf(ID.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        if(selectedNote == null)
        {
            int id = Integer.parseInt(OSIS);
            MarketplaceNote newNote = new MarketplaceNote(id, title ,sdf.format(new Date()), "false", desc);
            MarketplaceNote.noteArrayList.add(newNote);
            sqLiteManager.addNoteToDatabase(newNote);
        }
        else
        {
            selectedNote.setName(title);
            selectedNote.setDesc(desc);
            selectedNote.setOSIS(Integer.parseInt(OSIS));
            sqLiteManager.updateNoteInDB(selectedNote);
        }
        this.getActivity().recreate();
    }

    public void deleteNote(View view)
    {
        MarketplaceDBHelper sqLiteManager = MarketplaceDBHelper.instanceOfDatabase(this.getContext());
        sqLiteManager.updateNoteInDB(selectedNote);
        this.getActivity().recreate();
    }
}