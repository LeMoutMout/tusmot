package com.example.tusmot;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

public class Grid{

    private static int tryNumber;
    private static GridLayout grid;

    private Clavier clavier;

    public Grid(GridLayout grid, Clavier clavier) {
        tryNumber = 1;
        this.grid = grid;
        this.clavier = clavier;
    }

    public void incrementTryNumber() {
        this.tryNumber += 1;
    }

    private static TextView getTextView(int column){
        String textViewId = String.valueOf(tryNumber) + String.valueOf(column);

        return grid.findViewById(Integer.parseInt(textViewId));
    }

    private static String getLetter(TextView textView){
        return textView.getText().toString().toLowerCase();
    }

    public String getWord(int wordLength){
        String word = "";

        for (int i = 1; i < wordLength + 1; i++){
            word += getTextView(i).getText();
        }

        return word;
    }

    public static void createGrid(Context context, int wordLength){
        for (int i = 1; i < 7; i++){
            for (int j = 1; j < wordLength+1; j++){
                TextView textView = new TextView(context);
                textView.setId(Integer.parseInt(String.valueOf(i) + String.valueOf(j)));
                textView.setBackgroundColor(Color.RED);
                textView.setBackgroundResource(R.drawable.textview_border);
                GridLayout.Spec rowSpec = GridLayout.spec(i, 1, 1f);
                GridLayout.Spec colSpec = GridLayout.spec(j, 1, 1f);
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, colSpec);
                textView.setLayoutParams(layoutParams);
                grid.addView(textView);
            }
        }
    }

    public static void addLetter(Context context, String letter) {
        int i = 1;

        TextView textView = getTextView(i);

        while(textView != null && !(((String) textView.getText()).isEmpty())){
            i++;
            textView = getTextView(i);
        }

        if(textView != null){
            String upperLetter = letter.toUpperCase();

            textView.setText(String.valueOf(upperLetter));
            textView.setTextColor(Color.parseColor("#FFFFFF"));
            textView.setGravity(Gravity.CENTER);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setTextSize(15);

            if(i == 1) {
                textView.setBackgroundColor(Color.parseColor("#B70E0E"));
            }
        }
    }

    public static void deleteLetter(Context context){
        int i = 1;

        TextView textView = getTextView(i);

        while(textView != null  && !(((String) textView.getText()).isEmpty())){
            i++;
            textView = getTextView(i);
        }

        if(i != 2) {
            textView = getTextView(i-1);
            textView.setText("");
        }
    }

    public void addWord(Context context, String word){

        for (int i = 1; i < grid.getColumnCount(); i++){

            TextView textView = getTextView(i);
            String letter = getLetter(textView);
            if(String.valueOf(word.charAt(i - 1)).equals(letter)){
                textView.setBackgroundColor(Color.parseColor("#B70E0E"));
                clavier.addGoodKey(letter);
            } else if(word.contains(letter)){
                textView.setBackgroundColor(Color.parseColor("#C59928"));
                clavier.addWrongPlaceKey(letter);
            } else {
                clavier.addWrongKey(letter);
            }
        }
        incrementTryNumber();
    }
}
