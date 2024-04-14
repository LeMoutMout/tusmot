package com.example.tusmot;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        Button dayWordButton = findViewById(R.id.dayWordButton);
        Button randomWordButton = findViewById(R.id.randomWordButton);

        dayWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, GameTusmot.class);
                intent.putExtra("search_type", "dayWord");
                startActivity(intent);
            }
        });

        randomWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, GameTusmot.class);
                intent.putExtra("search_type", "randomWord");
                startActivity(intent);
            }
        });

    }

}
