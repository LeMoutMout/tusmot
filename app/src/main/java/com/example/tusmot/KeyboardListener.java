package com.example.tusmot;

import android.widget.ImageButton;

public interface KeyboardListener {

    void onLetterClicked(String letter);

    void onDeleteClicked(ImageButton imageButton);

    void onEnterClicked(ImageButton imageButton);
}
