package com.example.swapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LogIn extends AppCompatActivity {
    TextView username, password;
    EditText usernameEnter, passwordEnter;
    Button create;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        username = (TextView)findViewById(R.id.username);
        password = (TextView)findViewById(R.id.password);
        usernameEnter = (EditText)findViewById(R.id.usernameInput);
        passwordEnter = (EditText)findViewById(R.id.passwordInput);
        create = (Button)findViewById(R.id.button);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
