package com.example.nangjanggopro.models;

import android.net.Uri;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class Post {

    public String uid;
    public String author;
    public String title;
    public String body;
    public String filepathString;
    public String numofperson;
    public String howlong;
    public String level;
    public String text;
    public String material;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(String uid, String author, String title, String body, String filepathString,String numofperson,String howlong,String material,String level,String  text) {
        this.uid = uid;
        this.author = author;
        this.title = title;
        this.body = body;
        this.filepathString = filepathString;
        this.numofperson = numofperson;
        this.howlong = howlong;
        this.level = level;
        this.text = text;
        this.material = material;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("title", title);
        result.put("body", body);
        result.put("starCount", starCount);
        result.put("stars", stars);
        result.put("filepathString", filepathString);
        result.put("numofperson", numofperson);
        result.put("howlong",howlong);
        result.put("level",level);
        result.put("text", text);
        result.put("material",material);


        return result;
    }
    // [END post_to_map]

}
// [END post_class]
