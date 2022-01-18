package com.example.swapapp;

import java.util.ArrayList;
import java.util.Date;

public class LoginNote
{
    public static ArrayList<LoginNote> noteArrayList = new ArrayList<>();
    public static String NOTE_EDIT_EXTRA =  "noteEdit";

    private int OSIS;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String interests;

    public LoginNote(int OSIS, String name, String phone, String email, String password) {
        this.OSIS = OSIS;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;

    }

    public LoginNote(int OSIS, String name, String password) {
        this.OSIS = OSIS;
        this.name = name;
        this.phone = null;
        this.email = null;
        this.password = password;
        this.interests = "";
    }

    public void addInterest(String ownerOSIS){
        interests+=ownerOSIS+"   ";
    }
    public int getOSIS() {
        return OSIS;
    }

    public void setOSIS (int OSIS) {
        this.OSIS = OSIS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInterests(){ return interests;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void removeInterest(int x ){
        String s = ""+x;
        String[] splited = this.interests.split("\\s+");
        String newInterest ="";
        for (int i=0; i<splited.length;i++){
            if (!splited[i].equals(s)){
                newInterest+=splited[i]+" ";
            }
        }
        this.interests=newInterest;
    }
    public static LoginNote getNoteForID(int passedNoteID)
    {

        for (LoginNote note : noteArrayList)
        {
            if(note.getOSIS() == passedNoteID)
                return note;
        }

        return null;
    }
    public static ArrayList<LoginNote> populateLogin()
    {
        ArrayList<LoginNote> nonDeleted = new ArrayList<>();
        for(LoginNote note : noteArrayList)
        {
                nonDeleted.add(note);
        }

        return nonDeleted;
    }
}
