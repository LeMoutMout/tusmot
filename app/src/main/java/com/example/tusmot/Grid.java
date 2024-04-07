package com.example.tusmot;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import org.w3c.dom.Text;

public class Grid {

    public static void createGrid(Context context, GridLayout grid, int wordLength){
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

    public static void addLetter(Context context, GridLayout grid, String letter, int tryNumber) {
        int i = 1;

        String textViewId = String.valueOf(tryNumber) + String.valueOf(i);

        TextView textView = grid.findViewById(Integer.parseInt(textViewId));

        while(textView.getText() == null){
            i++;
            textViewId = String.valueOf(tryNumber + String.valueOf(i));
            textView = grid.findViewById(Integer.parseInt(textViewId));
        }

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
