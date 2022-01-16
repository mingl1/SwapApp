package com.example.swapapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class loginScreen extends AppCompatActivity {

    private EditText osis, pw;
    private DBHelper x;
    private Button login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login_screen);
        initWidgets();
        x=DBHelper.instanceOfDatabase(this);
    }


    private void initWidgets()
    {
        osis = findViewById(R.id.userOsis);
        pw = findViewById(R.id.userPassword);
        login=findViewById(R.id.button2);
        signup=findViewById(R.id.button3);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                tryLogin(v);
            }
        });

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                trySignup(v);
            }
        });


    }

    public void tryLogin(View view) {
        SQLiteDatabase u = x.getReadableDatabase();
//        String[] splited = data.split("\\s+");
//        boolean yes=true;
//        for(int i=0; i<splited.length;i+=3){
//            if (splited[i].equals(osis.getText().toString())&&splited[i+2].equals(pw.getText().toString())){
//                Message.message(this.getContext(), "WELCOME "+splited[i+1]);
//                yes=false;
//                Bundle bundle= new Bundle();
//                bundle.putInt("OSIS", Integer.parseInt(([i-1]));
//            }
//        }
//        if (yes){
//            Message.message(this.getContext(), "Wrong osis/PW");
//
//        }
        try (Cursor result = u.rawQuery("SELECT OSIS FROM Users WHERE OSIS = " + osis.getText().toString() + " AND password = " + pw.getText().toString(), null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    Message.message(this, "WELCOME " + result.getInt(0));
                    Intent intent = new Intent(this, mainActivity.class);
                    intent.putExtra("OSIS",result.getInt(0));
                    startActivity(intent);
                }
            }
            else{
                    Message.message(this, "Wrong osis/PW");
                }
            }


        }


    public void trySignup(View view){
        Intent newNoteIntent = new Intent(this, NoteDetailActivity.class);
        startActivity(newNoteIntent);
    }
}