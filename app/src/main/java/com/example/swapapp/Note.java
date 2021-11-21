package com.example.swapapp;

import java.util.ArrayList;
import java.util.Date;

public class Note
{
    public static ArrayList<Note> noteArrayList = new ArrayList<>();
    public static String NOTE_EDIT_EXTRA =  "noteEdit";

    private int ID;
    private String name;
    private String description;
    private Date deleted;
    private Date created;

    public Note(int ID, String name, String description, Date deleted, Date created) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.deleted = deleted;
        this.created = created;
    }

    public Note(int id, String name, String description, Date created)
    {
        this.ID = id;
        this.name = name;
        this.description = description;
        this.created=created;
        deleted = null;
    }


    public static Note getNoteForID(int passedNoteID)
    {
        for (Note note : noteArrayList)
        {
            if(note.getId() == passedNoteID)
                return note;
        }

        return null;
    }

    public static ArrayList<Note> nonDeletedNotes()
    {
        ArrayList<Note> nonDeleted = new ArrayList<>();
        for(Note note : noteArrayList)
        {
            if(note.getDeleted() == null)
                nonDeleted.add(note);
        }

        return nonDeleted;
    }

    public int getId()
    {
        return ID;
    }

    public void setId(int id)
    {
        this.ID = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Date deleted)
    {
        this.deleted = deleted;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }
}
