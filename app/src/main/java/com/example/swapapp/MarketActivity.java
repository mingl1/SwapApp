package com.example.swapapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MarketActivity extends AppCompatActivity

{
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadFromDBToMemory();
        setNoteAdapter();
        //setOnClickListener();
    }


    private void initWidgets()
    {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void loadFromDBToMemory()
    {
        MarketplaceDBHelper sqLiteManager = MarketplaceDBHelper.instanceOfDatabase(this);
        sqLiteManager.populateNoteListArray();
    }

    private void setNoteAdapter()
    {
        MarketplaceNoteAdapter noteAdapter = new MarketplaceNoteAdapter(getApplicationContext(), MarketplaceNote.freshListings());
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


//    private void setOnClickListener()
//    {
//        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
//            {
//                LoginNote selectedNote = (LoginNote) noteListView.getItemAtPosition(position);
//                Intent editNoteIntent = new Intent(getApplicationContext(), NoteDetailActivity.class);
//                editNoteIntent.putExtra(Note.NOTE_EDIT_EXTRA, selectedNote.getOSIS());
//                startActivity(editNoteIntent);
//            }
//        });
//    }


    public void addListing(View view)
    {
        Intent newNoteIntent = new Intent(this, addListing.class);
        startActivity(newNoteIntent);
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        setNoteAdapter();
    }
}
