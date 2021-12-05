package com.example.swapapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity

{
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_logins);
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
        DBHelper sqLiteManager = DBHelper.instanceOfDatabase(this);
        sqLiteManager.populateNoteListArray();
    }

    private void setNoteAdapter()
    {
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(), LoginNote.nonDeletedNotes());
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


    public void newNote(View view)
    {
        Intent newNoteIntent = new Intent(this, NoteDetailActivity.class);
        startActivity(newNoteIntent);
    }

    public void testLogin(View view)
    {
        Intent newNoteIntent = new Intent(this, LoginTestActivity.class);
        startActivity(newNoteIntent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setNoteAdapter();
    }
}
