package com.example.swapapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginTestActivity extends AppCompatActivity
{
    private EditText osis, pw;
    private DBHelper x;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        initWidgets();
        x=DBHelper.instanceOfDatabase(this);
    }

    private void initWidgets()
    {
        osis = findViewById(R.id.userOsis);
        pw = findViewById(R.id.userPassword);
    }


    public void tryLogin(View view){
        String data= x.getData();
        String[] splited = data.split("\\s+");
        boolean yes=true;
        for(int i=0; i<splited.length;i+=3){
            if (splited[i].equals(osis.getText().toString())&&splited[i+2].equals(pw.getText().toString())){
                Message.message(this, "WELCOME "+splited[i+1]);
                yes=false;
            }
        }
        if (yes){
            Message.message(this, "Wrong osis/PW");

        }
    }

    public void trySignup(View view){
        Intent newNoteIntent = new Intent(this, NoteDetailActivity.class);
        startActivity(newNoteIntent);
    }
}
