package com.example.swapapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MarketplaceDBHelper {
    private static MarketplaceDBHelper sqLiteManager;
    private static final String DATABASE_NAME = "Listings";
    private static final int DATABASE_VERSION = 5;
    private static final String TABLE_NAME = "Listings";
    private static final String TimeStamp = "timestamp";
    private static final String Inventory = "inventory";
    private static final String Wanted = "wanted";
    private static final String Image = "Image";
    private static final String ID_FIELD= "OSIS";


    @SuppressLint("SimpleDateFormat")
    public MarketplaceDBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static MarketplaceDBHelper instanceOfDatabase (Context context)
    {
        if (sqLiteManager == null)
            sqLiteManager = new MarketplaceDBHelper (context);

        return sqLiteManager;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        StringBuilder sql;
        sql = new StringBuilder ()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(TimeStamp)
                .append(" INT, ")
                .append(Inventory)
                .append(" TEXT, ")
                .append(Image)
                .append(" BLOB, ")
                .append(Wanted)
                .append(" TEXT) ")
                .append(ID_FIELD)
                .append(" INT ");


        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }

    public static String getTableName () {
        return TABLE_NAME;
    }


    public static String getTimeStamp () {
        return TimeStamp;
    }

    public static String getInventory () {
        return Inventory;
    }


    public static String getWanted () {
        return Wanted;
    }

    public static String getImage () {
        return Image;
    }


    public void addNoteToDatabase(LoginNote note)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TimeStamp, note.getTimeStamp());
        contentValues.put(Inventory, note.getInventory());
        contentValues.put(Wanted, note.getWanted());
        contentValues.put(Image, note.getImage());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }


    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db . rawQuery ("select * from contacts where OSIS=" + id + "", null);
        return res;
    }





    public void populateNoteListArray()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase . rawQuery ("SELECT * FROM " + TABLE_NAME, null))
        {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result . getInt (0);
                    String name = result . getString (1);
                    String password = result . getString (2);
                    LoginNote note = new LoginNote(id, name, password);
                    LoginNote.noteArrayList.add(note);
                }
            }
        }
    }

    public void updateNoteInDB(LoginNote Note) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TimeStamp, Note.getTimeStamp());
        contentValues.put(Inventory, Note.getInventory());
        contentValues.put(Wanted, Note.getWanted());
        contentValues.put(Image, Note.getImage());
    }
}
