package com.example.nangjanggopro;

import java.util.ArrayList;

public class WordItemData {
    public  String Material_title;
    public String Material_now;
    public String Material_after;
    public String Material_Alarm;

    public WordItemData(String Material_title, String Material_now, String Material_after, String Material_Alarm){
        this.Material_title = Material_title;
        this.Material_now = Material_now;
        this.Material_after = Material_after;
        this.Material_Alarm = Material_Alarm;
    }

    public static ArrayList<WordItemData> createContactsList(int numContacts) {
        ArrayList<WordItemData> contacts = new ArrayList<WordItemData>();

        for (int i = 1; i <= numContacts; i++) {

        }
        return contacts;
    }
}
