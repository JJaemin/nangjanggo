package com.example.nangjanggopro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PostDetailActivity extends AppCompatActivity {

    public static final String EXTRA_POST_KEY = "post_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
    }
}
