package com.example.nangjanggopro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class TodayBestRecipe extends AppCompatActivity {

    LinearLayout recipe1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_best_recipe);

        recipe1 = (LinearLayout) findViewById(R.id.recipe1);
        recipe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Recipe.class);
                startActivity(intent);
            }
        });
    }
}
