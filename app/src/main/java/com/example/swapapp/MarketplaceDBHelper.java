package com.example.swapapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MarketplaceDBHelper extends SQLiteOpenHelper
{
    private static MarketplaceDBHelper sqLiteManager;


    private static final String DATABASE_NAME = "Marketplace";
    private static final int DATABASE_VERSION = 8;
    private static final String TABLE_NAME = "Listings";
    private static final String OSIS = "OSIS";
    private static final String TimeStamp = "timeCreated";
    private static final String Image = "image";
    private static final String interested = "interested";
    private static final String desc = "desc";
    private static final String NAME = "itemName";
    private static final String visibility = "visibility";
    private static final String ID="ID";
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @SuppressLint("SimpleDateFormat")
    public MarketplaceDBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    public static MarketplaceDBHelper instanceOfDatabase(Context context)
    {
        if(sqLiteManager == null)
            sqLiteManager = new MarketplaceDBHelper(context);

        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(OSIS)
                .append(" INT, ")
                .append(NAME)
                .append(" TEXT, ")
                .append(TimeStamp)
                .append(" TEXT, ")
                .append(interested)
                .append(" TEXT, ")
                .append(desc)
                .append(" TEXT, ")
                .append(visibility)
                .append(" TEXT, ")
                .append(ID)
                .append(" TEXT , ")
                .append(Image)
                .append(" TEXT ) ");


        db.execSQL(sql.toString());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Listings");
        onCreate(db);
    }

    public void addNoteToDatabase(MarketplaceNote note)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OSIS, note.getOSIS());
        contentValues.put(NAME, note.getName());
        contentValues.put(TimeStamp, note.getTimeStamp());
       // contentValues.put(Image, note.getImage());
        contentValues.put(interested, note.getInterested());
        contentValues.put(desc, note.getDesc());
        contentValues.put(visibility, note.getVisability() );
        contentValues.put(ID, note.getID());
        contentValues.put(Image, note.getImage());
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }


    public ArrayList<MarketplaceNote> populateMarketPlace(int userOSIS)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<MarketplaceNote> list= new ArrayList<MarketplaceNote>();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME +" ORDER BY timeCreated DESC ", null))
        {
            System.out.println(result.getCount());
            if(result.getCount() != 0)
            {
                while (result.moveToNext())
                {
                    int OSIS = result.getInt(0);
                    String name = result.getString(1);
                    String TimeStamp = result.getString(2);
                    String interested = result.getString(3);
                    String desc = result.getString(4);
                    String vis = result.getString(5);
                    String itemID = result.getString(6);
                    String image = result.getString(7);

                    if((!(OSIS==userOSIS))&&vis.equals("TRUE")){
                        MarketplaceNote note = new MarketplaceNote(OSIS,name,TimeStamp, interested, desc, vis, itemID, image);
                        list.add(note);

                    }
                }

            }
        }
        return list;
    }
    public ArrayList<MarketplaceNote>  populateInventory(int OSIS)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<MarketplaceNote> list= new ArrayList<MarketplaceNote>();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME+" ORDER BY timeCreated DESC ", null))
        {
            if(result.getCount() != 0)
            {
                while (result.moveToNext())
                {
                    int id = result.getInt(0);
                    String name = result.getString(1);
                    String TimeStamp = result.getString(2);
                    String interested = result.getString(3);
                    String desc = result.getString(4);
                    String vis = result.getString(5);
                    String itemID = result.getString(6);
                    String image = result.getString(7);
                    if((""+id).equals(""+OSIS)) {
                        MarketplaceNote note = new MarketplaceNote(id, name, TimeStamp, interested, desc, vis, itemID,image);
                        list.add(note);
                    }
                }
            }
        }
        return list;
    }
    public void updateNoteInDB(MarketplaceNote Note)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OSIS, Note.getOSIS());
        contentValues.put(NAME, Note.getName());
        contentValues.put(desc, Note.getDesc());
        contentValues.put(TimeStamp, Note.getTimeStamp());
        contentValues.put(interested,Note.getInterested());
        contentValues.put(visibility, Note.getVisability() );
        contentValues.put(ID, Note.getID());
        contentValues.put(Image, Note.getImage());

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + " =? ", new String[]{String.valueOf(Note.getID())});
    }

    public ArrayList<MarketplaceNote> search( String s ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ArrayList<MarketplaceNote> x= new ArrayList<MarketplaceNote>();
        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+ NAME+" LIKE "+s, null)){
            if(result.getCount() != 0)
            {
                while (result.moveToNext())
                {
                    int id = result.getInt(0);
                    String name = result.getString(1);
                    String TimeStamp = result.getString(2);
                    String interested = result.getString(3);
                    String desc = result.getString(4);
                    String vis = result.getString(5);
                    String itemID = result.getString(6);
                    String image = result.getString(7);
                    if(!(""+id).equals(""+OSIS)) {
                        MarketplaceNote note = new MarketplaceNote(id, name, TimeStamp, interested, desc, vis, itemID,image);
                        x.add(note);
                    }
                }
            }
        }
        return x;
    }


}

