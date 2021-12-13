package com.example.swapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class addListing extends AppCompatActivity
{
    private EditText titleEditText, descEditText, ID;
    private Button deleteButton;
    private MarketplaceNote selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addlisting);
        initWidgets();
        checkForEditNote();
    }

    private void initWidgets()
    {
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descriptionEditText);
        deleteButton = findViewById(R.id.deleteNoteButton);
        ID = findViewById(R.id.OSIS);
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
        MarketplaceDBHelper sqLiteManager = MarketplaceDBHelper.instanceOfDatabase(this);
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());
        String OSIS = String.valueOf(ID.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        if(selectedNote == null)
        {
            int id = Integer.parseInt(OSIS);
            MarketplaceNote newNote = new MarketplaceNote(id, title ,sdf.format(new Date()), false, desc);
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

        finish();
    }

    public void deleteNote(View view)
    {
        MarketplaceDBHelper sqLiteManager = MarketplaceDBHelper.instanceOfDatabase(this);
        sqLiteManager.updateNoteInDB(selectedNote);
        finish();
    }
}