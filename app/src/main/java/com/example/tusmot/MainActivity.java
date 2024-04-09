package com.example.tusmot;


import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.tusmot.api.ApiFindWord;
import com.example.tusmot.api.ApiWiktionnaireCheckerWord;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements  KeyboardListener {

    private static final String TAG = "MainActivity";
    private Grid grid;
    private Clavier clavier;
    private TextView word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonA = findViewById(R.id.A);
        Button buttonB = findViewById(R.id.B);
        Button buttonC = findViewById(R.id.C);
        Button buttonD = findViewById(R.id.D);
        Button buttonE = findViewById(R.id.E);
        Button buttonF = findViewById(R.id.F);
        Button buttonG = findViewById(R.id.G);
        Button buttonH = findViewById(R.id.H);
        Button buttonI = findViewById(R.id.I);
        Button buttonJ = findViewById(R.id.J);
        Button buttonK = findViewById(R.id.K);
        Button buttonL = findViewById(R.id.L);
        Button buttonM = findViewById(R.id.M);
        Button buttonN = findViewById(R.id.N);
        Button buttonO = findViewById(R.id.O);
        Button buttonP = findViewById(R.id.P);
        Button buttonQ = findViewById(R.id.Q);
        Button buttonR = findViewById(R.id.R);
        Button buttonS = findViewById(R.id.S);
        Button buttonT = findViewById(R.id.T);
        Button buttonU = findViewById(R.id.U);
        Button buttonV = findViewById(R.id.V);
        Button buttonW = findViewById(R.id.W);
        Button buttonX = findViewById(R.id.X);
        Button buttonY = findViewById(R.id.Y);
        Button buttonZ = findViewById(R.id.Z);
        ImageButton deleteButton = findViewById(R.id.deleteButton);
        ImageButton enterButton = findViewById(R.id.enterButton);

        clavier = new Clavier(this);

        clavier.addButton(buttonA);
        clavier.addButton(buttonB);
        clavier.addButton(buttonC);
        clavier.addButton(buttonD);
        clavier.addButton(buttonE);
        clavier.addButton(buttonF);
        clavier.addButton(buttonG);
        clavier.addButton(buttonH);
        clavier.addButton(buttonI);
        clavier.addButton(buttonJ);
        clavier.addButton(buttonK);
        clavier.addButton(buttonL);
        clavier.addButton(buttonM);
        clavier.addButton(buttonN);
        clavier.addButton(buttonO);
        clavier.addButton(buttonP);
        clavier.addButton(buttonQ);
        clavier.addButton(buttonR);
        clavier.addButton(buttonS);
        clavier.addButton(buttonT);
        clavier.addButton(buttonU);
        clavier.addButton(buttonV);
        clavier.addButton(buttonW);
        clavier.addButton(buttonX);
        clavier.addButton(buttonY);
        clavier.addButton(buttonZ);
        clavier.addButton(deleteButton);
        clavier.addButton(enterButton);

        grid = new Grid(findViewById(R.id.grid_words), clavier);

        TextView title = findViewById(R.id.title);
        word = findViewById(R.id.word);

        new ApiFindWord(word) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                grid.createGrid(MainActivity.this, word.getText().length());

                grid.addLetter(MainActivity.this, String.valueOf(word.getText().charAt(0)));
            }
        }.execute();
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


                boolean exist = testWord(mot);



                if (exist) {
                    grid.addWord(this, String.valueOf(word.getText()));
                    grid.addLetter(this, String.valueOf(word.getText().charAt(0)));
                } else {

                }
            } else {

            }

        } catch (Exception e) {
            Log.e(TAG, "Une erreur s'est produite : ", e);
            e.printStackTrace();
        }
    }

    public boolean testWord(String word){
        try {
            boolean exist = false;
            ApiWiktionnaireCheckerWord apiWiktionnaireCheckerWord = new ApiWiktionnaireCheckerWord();
            exist = apiWiktionnaireCheckerWord.execute(word).get();
            if(!exist) {
                if(word.contains("e")){
                    for(int i = 0; i < word.length(); i++){
                        if(String.valueOf(word.charAt(i)).equals("e")){
                            Character.valueOf('é');
                        }
                    }
                }
            }
            return exist;
        } catch (Exception e) {
            Log.e(TAG, "Une erreur s'est produite : ", e);
            e.printStackTrace();
        }
        return false;
    }

}