package com.example.tusmot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.title);
        TextView textView4 = findViewById(R.id.textView4);

        new Api(textView).execute();

        String mot = "exemple";

        try {
            boolean existe = WiktionnaireChecker.motExiste(mot);

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
}