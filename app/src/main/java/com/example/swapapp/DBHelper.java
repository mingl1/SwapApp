package com.example.swapapp;


import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper sqLiteManager;


    private static final String DATABASE_NAME = "Users";
    private static final int DATABASE_VERSION = 5;
    private static final String TABLE_NAME = "Users";
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String PASSWORD = "password";
    private static final String ID_FIELD = "OSIS";


    @SuppressLint("SimpleDateFormat")
    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    public static DBHelper instanceOfDatabase(Context context)
    {
        if(sqLiteManager == null)
            sqLiteManager = new DBHelper(context);

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
                .append(ID_FIELD)
                .append(" INT, ")
                .append(NAME)
                .append(" TEXT, ")
                .append(PASSWORD)
                .append(" TEXT) ");


        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }

    public static String getTableName() {
        return TABLE_NAME;
    }


    public static String getEMAIL() {
        return EMAIL;
    }

    public static String getName() {
        return NAME;
    }

    public static String getIdField() {
        return ID_FIELD;
    }

    public static String getPhone() {
        return PHONE;
    }

    public static String getPassword() {
        return PASSWORD;
    }


    public void addNoteToDatabase(LoginNote note)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, note.getOSIS());
        contentValues.put(NAME, note.getName());
        contentValues.put(PASSWORD, note.getPassword());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }


//    public Cursor getData(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from contacts where OSIS="+id+"", null );
//        return res;
//    }


    public String getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {this.ID_FIELD,this.NAME,this.PASSWORD};
        Cursor cursor =db.query(this.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            @SuppressLint("Range") int cid =cursor.getInt(cursor.getColumnIndex(this.ID_FIELD));
            @SuppressLint("Range") String name =cursor.getString(cursor.getColumnIndex(this.NAME));
            @SuppressLint("Range") String  password =cursor.getString(cursor.getColumnIndex(this.PASSWORD));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }


    public void populateNoteListArray()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null))
        {
            if(result.getCount() != 0)
            {
                while (result.moveToNext())
                {
                    int id = result.getInt(0);
                    String name = result.getString(1);
                    String password = result.getString(2);
                    LoginNote note = new LoginNote(id,name,password);
                    LoginNote.noteArrayList.add(note);
                }
            }
        }
    }

    public void updateNoteInDB(LoginNote Note)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, Note.getOSIS());
        contentValues.put(NAME, Note.getName());
        contentValues.put(PASSWORD, Note.getPassword());

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(Note.getOSIS())});
    }




}

