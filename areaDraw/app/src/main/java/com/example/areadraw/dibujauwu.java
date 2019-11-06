package com.example.areadraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;

public class dibujauwu extends View {

    public dibujauwu(Context contexto){
        super(contexto);
    }

    protected void onDraw(Canvas canvas){
        Paint pincel = new Paint();

        int width = getWidth();
        int height = getHeight();

        pincel.setColor(Color.RED);


        Point a = new Point(30,0);
        Point b = new Point(0,100);
        Point c = new Point(87,50);


        Path path = new Path();

        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(a.x, a.y);

        canvas.drawPath(path, pincel);







    }

}
