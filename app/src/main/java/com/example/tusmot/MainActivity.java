package com.example.tusmot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.tusmot.api.ApiFindWord;
import com.example.tusmot.api.ApiWiktionnaireCheckerWord;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "coucou : ");

        GridLayout grid = (GridLayout) findViewById(R.id.grid_words);
        MainActivity.createGrid(this, grid);

        Log.i(TAG, "yo : ");

        TextView title = findViewById(R.id.title);
        TextView textView4 = findViewById(R.id.textView4);

        new ApiFindWord(title).execute();

        String mot = "exemple";

        try {
            boolean existe = ApiWiktionnaireCheckerWord.motExiste(mot);

            if (existe) {
                textView4.setText("Le mot \"" + mot + "\" existe dans le Wiktionnaire.");
            } else {
                textView4.setText("Le mot \"" + mot + "\" n'existe pas dans le Wiktionnaire.");
            }
        } catch (Exception e) {
            Log.e(TAG, "Une erreur s'est produite : ", e);
            e.printStackTrace();
        }
    }

    public static void createGrid(Context context, GridLayout grid){
        for (int i = 0; i < 6; i++){
            for (int j =0; j < 6; j++){
                TextView textView = new TextView(context);
                textView.setBackgroundColor(Color.RED);
                GridLayout.Spec rowSpec = GridLayout.spec(i, 1, 1f);
                GridLayout.Spec colSpec = GridLayout.spec(j, 1, 1f);
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, colSpec);
                textView.setLayoutParams(layoutParams);
                grid.addView(textView);
            }
        }
    }
}