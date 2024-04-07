package com.example.tusmot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;
import com.example.tusmot.api.ApiFindWord;
import com.example.tusmot.api.ApiWiktionnaireCheckerWord;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TextView title = findViewById(R.id.title);
        TextView word = findViewById(R.id.word);
        GridLayout grid = (GridLayout) findViewById(R.id.grid_words);

        new ApiFindWord(word) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Log.i("longueur du mot :", String.valueOf(word.getText().toString().length()));

                Grid.createGrid(MainActivity.this, grid, word.getText().length());

                Grid.addLetter(MainActivity.this,grid, (String) String.valueOf(word.getText().charAt(0)), 1);
            }
        }.execute();





        String mot = "exemple";

        try {
            boolean existe = ApiWiktionnaireCheckerWord.motExiste(mot);

            if (existe) {
                title.setText("Le mot \"" + mot + "\" existe dans le Wiktionnaire.");
            } else {
                title.setText("Le mot \"" + mot + "\" n'existe pas dans le Wiktionnaire.");
            }
        } catch (Exception e) {
            Log.e(TAG, "Une erreur s'est produite : ", e);
            e.printStackTrace();
        }
    }
}