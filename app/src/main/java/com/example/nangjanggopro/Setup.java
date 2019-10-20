package com.example.nangjanggopro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Setup extends AppCompatActivity {

    ImageView backImgView;
    LinearLayout myinfo, appsetup, applnfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        backImgView = findViewById(R.id.backImgView);
        backImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myinfo = findViewById(R.id.myinfo);
        appsetup = findViewById(R.id.appsetup);
        applnfo = findViewById(R.id.appinfo);

        myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MyInfo.class);
                startActivity(intent1);
            }
        });

        appsetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), Appsetup.class);
                startActivity(intent2);
            }
        });

        applnfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent3 = new Intent(getApplicationContext(), AppInfo.class);
                 startActivity(intent3);
            }
        });


    }
}
