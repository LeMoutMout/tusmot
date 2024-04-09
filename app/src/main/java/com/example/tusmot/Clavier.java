package com.example.tusmot;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clavier implements View.OnClickListener{

    private KeyboardListener listener;
    private List<Button> listButtons = new ArrayList<Button>();
    private List<ImageButton> listImageButtons = new ArrayList<ImageButton>();

    public Clavier(KeyboardListener listener) {
        this.listener = listener;
    }

    public void addButton(View v) {
        v.setOnClickListener(this);
        if(v instanceof Button){
            listButtons.add((Button) v);
        } else if (v instanceof ImageButton) {
            listImageButtons.add((ImageButton) v);
        }

    }

    @Override
    public void onClick(View v) {
        if(v instanceof Button){
            Button button = (Button) v;
            String letter = button.getText().toString();
            if (listener != null) {
                listener.onLetterClicked(letter);
            }
        } else if (v instanceof ImageButton){
            int id = v.getId();

            if(id == R.id.deleteButton){
                ImageButton imageButton = (ImageButton) v;
                if(listener != null){
                    listener.onDeleteClicked(imageButton);
                }
            } else if (id == R.id.enterButton) {
                ImageButton imageButton = (ImageButton) v;
                if(listener != null){
                    listener.onEnterClicked(imageButton);
                }
            }
        }
    }

    public void addGoodKey(String letter){
        for (int i = 0; i < listButtons.size();i++){
            Button button = listButtons.get(i);
            String textButton = button.getText().toString();
            if(letter.equalsIgnoreCase(textButton)){
                button.setBackgroundColor(Color.parseColor("#B70E0E"));
            }
        }
    }

    public void addWrongPlaceKey(String letter){
        for (int i = 0; i < listButtons.size();i++){
            Button button = listButtons.get(i);
            String textButton = button.getText().toString();
            if(letter.equalsIgnoreCase(textButton)){
                button.setBackgroundColor(Color.parseColor("#C59928"));
            }
        }
    }

    public void addWrongKey(String letter){
        for (int i = 0; i < listButtons.size();i++){
            Button button = listButtons.get(i);
            String textButton = button.getText().toString();
            if(letter.equalsIgnoreCase(textButton)){
                button.setBackgroundColor(Color.parseColor("#2A2A2C"));
                button.setTextColor(Color.parseColor("#909090"));
            }
        }
    }
}
