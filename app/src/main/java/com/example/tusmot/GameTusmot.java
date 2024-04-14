package com.example.tusmot;


import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.example.tusmot.api.ApiFindWord;

public class GameTusmot extends AppCompatActivity implements  KeyboardListener {

    private Grid grid;
    private Clavier clavier;
    private static String word;

    public static void setWord(String word) {
        GameTusmot.word = word;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: GameTusmot activity created");
        setContentView(R.layout.game_tusmot);

        ImageButton home = findViewById(R.id.homePage);

        clavier = new Clavier(this);
        grid = new Grid(findViewById(R.id.grid_words), clavier);

        addKeyboardButtons();
        new ApiFindWord() {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                grid.createGrid(GameTusmot.this, word.length());

                grid.addLetter(GameTusmot.this, String.valueOf(word.charAt(0)));
            }
        }.execute();

        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setContentView(R.layout.home_page);
            }
        });
    }

    @Override
    public void onLetterClicked(String letter) {
        grid.addLetter(this, letter);
    }

    @Override
    public void onDeleteClicked(ImageButton imageButton) {
        grid.deleteLetter(this);
    }

    @Override
    public void onEnterClicked(ImageButton imageButton) {
        try {
            if (word.length() == grid.getWord(word.length()).length()) {
                String mot = grid.getWord(word.length());

                TestWordVariations testWordVariations = new TestWordVariations();

                boolean exist = testWordVariations.testVariations(mot);

                if (exist) {
                    grid.addWord(this, String.valueOf(word));
                    grid.addLetter(this, String.valueOf(word.charAt(0)));
                } else {

                }
            } else {

            }

        } catch (Exception e) {
            Log.e(TAG, "Une erreur s'est produite : ", e);
            e.printStackTrace();
        }
    }

    private void addKeyboardButtons() {
        int[] buttonIds = {
                R.id.A, R.id.B, R.id.C, R.id.D, R.id.E, R.id.F, R.id.G, R.id.H,
                R.id.I, R.id.J, R.id.K, R.id.L, R.id.M, R.id.N, R.id.O, R.id.P,
                R.id.Q, R.id.R, R.id.S, R.id.T, R.id.U, R.id.V, R.id.W, R.id.X,
                R.id.Y, R.id.Z, R.id.deleteButton, R.id.enterButton
        };

        for (int id : buttonIds) {
            View view = findViewById(id);
            if (view instanceof Button || view instanceof ImageButton) {
                clavier.addButton(view);
            }
        }
    }
}