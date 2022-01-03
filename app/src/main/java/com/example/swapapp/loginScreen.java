package com.example.swapapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class loginScreen extends Fragment {

    private EditText osis, pw;
    private DBHelper x;
    private Button login, signup;

    public loginScreen() {
        // Required empty public constructor
    }

    // can maybe use to record login
//
//    // TODO: Rename and change types and number of parameters
//    public static loginScreen newInstance(String param1, String param2) {
//        loginScreen fragment = new loginScreen();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_login_screen, container, false);
        initWidgets(v);
        x=DBHelper.instanceOfDatabase(this.getContext());

        return v;
    }

    private void initWidgets(View v)
    {
        osis = v.findViewById(R.id.userOsis);
        pw = v.findViewById(R.id.userPassword);
        login=v.findViewById(R.id.button2);
        signup=v.findViewById(R.id.button3);

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

    public void tryLogin(View view){
        String data= x.getData();
        String[] splited = data.split("\\s+");
        boolean yes=true;
        for(int i=0; i<splited.length;i+=3){
            if (splited[i].equals(osis.getText().toString())&&splited[i+2].equals(pw.getText().toString())){
                Message.message(this.getContext(), "WELCOME "+splited[i+1]);
                yes=false;
            }
        }
        if (yes){
            Message.message(this.getContext(), "Wrong osis/PW");

        }
    }

    public void trySignup(View view){
//        Intent newNoteIntent = new Intent(this, NoteDetailActivity.class);
//        startActivity(newNoteIntent);
    }
}