package com.example.swapapp;

import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MarketplaceNote {
    public static ArrayList<MarketplaceNote> noteArrayList = new ArrayList<>();

    private int OSIS;
    private String TimeStamp;
    private Blob Image;
    private String Interested;
    private String Desc;
    private String Name;
    private int ID;

    public int getOSIS() {
        return OSIS;
    }

    public void setOSIS(int OSIS) {
        this.OSIS = OSIS;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public Blob getImage() {
        return Image;
    }

    public void setImage(Blob image) {
        Image = image;
    }

    public String getInterested() {
        return Interested;
    }

    public void setInterested(String interested) {
        Interested = interested;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        this.Desc = desc;
    }

    public int getID(){return ID;}
    public MarketplaceNote(int OSIS, String Name, String TimeStamp, String Interested, String desc) {

        this.OSIS = OSIS;
        this.TimeStamp = TimeStamp;
        this.Desc = desc;
        //this.Image = Image;
        this.Name=Name;
        this.Interested = Interested;
    }

    public static MarketplaceNote search(String interest) {
        for (MarketplaceNote note : noteArrayList) {
            if (note.getName().toLowerCase().contains(interest.toLowerCase()))
                return note;
        }

        return null;
    }

    public static ArrayList<MarketplaceNote> freshListings() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        ArrayList<MarketplaceNote> freshListings = new ArrayList<>();
        Date d2 = new Date();

        if(d2 !=null) {
            for (MarketplaceNote note : noteArrayList) {
                // Try Block
                try {

                    // parse method is used to parse
                    // the text from a string to
                    // produce the date

                    Date d1 = sdf.parse(note.getTimeStamp());

                    // Calucalte time difference
                    // in milliseconds
                    long difference_In_Time
                            = d2.getTime() - d1.getTime();

                    // Calucalte time difference in
                    // seconds, minutes, hours, years,
                    // and days
                    long difference_In_Seconds
                            = (difference_In_Time
                            / 1000)
                            % 60;

                    long difference_In_Minutes
                            = (difference_In_Time
                            / (1000 * 60))
                            % 60;

                    long difference_In_Hours
                            = (difference_In_Time
                            / (1000 * 60 * 60))
                            % 24;

                    long difference_In_Years
                            = (difference_In_Time
                            / (1000l * 60 * 60 * 24 * 365));

                    long difference_In_Days
                            = (difference_In_Time
                            / (1000 * 60 * 60 * 24))
                            % 365;
                    if (difference_In_Days <= 1) {

                        freshListings.add(note);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return freshListings;
    }
}
