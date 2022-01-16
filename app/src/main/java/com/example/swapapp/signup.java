package com.example.swapapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class signup extends Fragment {
    private EditText titleEditText, descEditText, ID;
    private Button deleteButton;
    private LoginNote selectedNote;

    public signup() {
        // Required empty public constructor
    }

//    // TODO: Rename and change types and number of parameters
//    public static marketplace newInstance(String param1, String param2) {
//        marketplace fragment = new marketplace();
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
        super.onCreate(savedInstanceState);
        View v= inflater.inflate(R.layout.fragment_marketplace, container, false);
        initWidgets(v);
        return v;
    }
    private void initWidgets(View v)
    {
        titleEditText = v.findViewById(R.id.titleEditText);
        descEditText = v.findViewById(R.id.descriptionEditText);
        deleteButton = v.findViewById(R.id.deleteNoteButton);
        ID = v.findViewById(R.id.OSIS);


    }

    public void signUp(View view)
    {
        DBHelper sqLiteManager = DBHelper.instanceOfDatabase(getContext());
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());
        String OSIS = String.valueOf(ID.getText());

        if(selectedNote == null)
        {
            int id = Integer.parseInt(OSIS);
            LoginNote newNote = new LoginNote(id, title, desc);
            LoginNote.noteArrayList.add(newNote);
            sqLiteManager.addNoteToDatabase(newNote);
        }
        else
        {
            selectedNote.setName(title);
            selectedNote.setPassword(desc);
            sqLiteManager.updateNoteInDB(selectedNote);
        }

        this.getActivity().recreate();
    }

}
