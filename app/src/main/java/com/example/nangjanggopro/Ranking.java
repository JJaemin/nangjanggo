package com.example.nangjanggopro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Ranking extends AppCompatActivity {

    LinearLayout todayBestRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        todayBestRecipe = (LinearLayout) findViewById(R.id.todayBestRecipe);

        todayBestRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TodayBestRecipe.class);
                startActivity(intent);
            }
        });
    }
}
