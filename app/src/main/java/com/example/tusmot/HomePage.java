package com.example.tusmot;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.ContentValues.TAG;

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
                setContentView(R.layout.game_tusmot);
                Log.d(TAG, "onClick: Button clicked, changing to GameTusmot activity");
            }
        });

        randomWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.game_tusmot);
                Log.d(TAG, "onClick: Button clicked, changing to GameTusmot activity");
            }
        });

    }

}
