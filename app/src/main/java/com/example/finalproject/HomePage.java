package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomePage extends AppCompatActivity implements View.OnClickListener {
    Button homePageServicePlay,homePageServiceStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            homePageServicePlay = findViewById(R.id.homePageServicePlay);
            homePageServiceStop = findViewById(R.id.homePageServiceStop);
            homePageServiceStop.setOnClickListener(this);
            homePageServicePlay.setOnClickListener(this);

            return insets;
        });

    }

    @Override
    public void onClick(View view) {
        if(view == homePageServicePlay){
            Intent serviceIntent=new Intent(this,MusicService.class);
            startService(serviceIntent);
        }
        if(view == homePageServiceStop){
            Intent serviceIntent=new Intent(this,MusicService.class);
            stopService(serviceIntent);
        }

    }
}