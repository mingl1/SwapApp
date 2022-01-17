package com.example.swapapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.github.dhaval2404.imagepicker.ImagePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class addlistings extends Fragment {



    // TODO: Rename and change types of parameters
    private EditText titleEditText, descEditText;
    private Button addItem, deleteButton;
    private int userOSIS;
    private ImageView addPhoto;
    private MarketplaceNote selectedNote;

    public addlistings() {
        // Required empty public constructor
    }


//    // TODO: Rename and change types and number of parameters
//    public static addlistings newInstance(String param1, String param2) {
//        addlistings fragment = new addlistings();
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

        View v = inflater.inflate(R.layout.fragment_addlistings, container, false);
        initWidgets(v);
        checkForEditNote();
        userOSIS=getActivity().getIntent().getExtras().getInt("OSIS");
        return v;
    }

    private void initWidgets(View v)
    {
        titleEditText = v.findViewById(R.id.titleEditText);
        descEditText = v.findViewById(R.id.descriptionEditText);
        deleteButton = v.findViewById(R.id.deleteNoteButton);
        addItem=v.findViewById(R.id.addItem);
        addPhoto=v.findViewById(R.id.addPhoto);

        addPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ImagePicker.with(getActivity())
//                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        addItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveNote(v);
            }
        });
    }

    private void checkForEditNote()
    {
        deleteButton.setVisibility(View.INVISIBLE);
        // editing listing to be implemented

//        Intent previousIntent = getIntent();
//
//        int passedNoteID = previousIntent.getIntExtra(Note.NOTE_EDIT_EXTRA, -1);
//        selectedNote = MarketplaceNote.getNoteForID(passedNoteID);
//
//        if (selectedNote != null)
//        {
//            titleEditText.setText(selectedNote.getName());
//            descEditText.setText(selectedNote.getDesc());
//            ID.setText(selectedNote.getOSIS());
//        }
//        else
//        {
//            deleteButton.setVisibility(View.INVISIBLE);
//        }
    }

    public void saveNote(View view)
    {
        MarketplaceDBHelper sqLiteManager = MarketplaceDBHelper.instanceOfDatabase(getActivity().getApplicationContext());
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());
        String OSIS = ""+userOSIS;
        String ID = UUID.randomUUID().toString();
        String image="";
        try{
            image = addPhoto.getTag().toString();
        }
        catch (Exception e){
            Message.message(this.getContext(), "No photo");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        if(selectedNote == null)
        {
            int userOSIS = Integer.parseInt(OSIS);
            MarketplaceNote newNote = new MarketplaceNote(userOSIS, title ,sdf.format(new Date()), "", desc, "TRUE", ID, image);
            MarketplaceNote.inventory.add(newNote);
            sqLiteManager.addNoteToDatabase(newNote);
        }
        else
        {
            selectedNote.setName(title);
            selectedNote.setDesc(desc);
            selectedNote.setOSIS(Integer.parseInt(OSIS));
            selectedNote.setVisability("TRUE");
            selectedNote.setID(ID);
            selectedNote.setImage(image);
            sqLiteManager.updateNoteInDB(selectedNote);
        }
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_nav_host,new addlistings()).commit();
    }

    public void deleteNote(View view)
    {
        MarketplaceDBHelper sqLiteManager = MarketplaceDBHelper.instanceOfDatabase(this.getContext());
        sqLiteManager.updateNoteInDB(selectedNote);
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_nav_host,new addlistings()).commit();
    }

}