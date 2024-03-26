package com.example.tusmot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Mot extends View {

    public Mot(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas c){
        Paint nothing = new Paint();
        nothing.setColor(Color.parseColor("#3A3A3C"));
        Paint wrong = new Paint();
        nothing.setColor(Color.parseColor("#3A3A3C"));
        Paint yellow = new Paint();
        nothing.setColor(Color.parseColor("#C59928"));
        Paint red = new Paint();
        nothing.setColor(Color.parseColor("#B70E0E"));
    }
}
