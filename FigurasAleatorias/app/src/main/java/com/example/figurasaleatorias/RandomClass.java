package com.example.figurasaleatorias;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;



public class RandomClass extends View {
    private Integer[] mBackgrounds = {Color.CYAN, Color.GRAY, Color.LTGRAY, Color.MAGENTA, Color.YELLOW, Color.WHITE};

    private Paint[] mForegrounds = {makePaint(Color.BLACK), makePaint(Color.BLUE), makePaint(Color.GREEN), makePaint(Color.RED)};

    private Bitmap[] mPics = {makeBitmap(R.drawable.emo_im_angel), makeBitmap(R.drawable.emo_im_cool), makeBitmap(R.drawable.emo_im_crying), makeBitmap(R.drawable.emo_im_happy), makeBitmap(R.drawable.emo_im_yelling)};

    public RandomClass(Context context){
        super(context);
    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawColor(RandomUtils.randomElement(mBackgrounds));
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int avgShapeWidth = viewWidth/5;
        for(int i = 0;i<20;i++){
            drawRandomCircle(canvas,viewWidth, viewHeight, avgShapeWidth);
        }



    }

    private Paint makePaint(int color){
        Paint p = new Paint();
        p.setColor(color);
        return(p);
    }

    private Bitmap makeBitmap(int bitmapId){
        return(BitmapFactory.decodeResource(getResources(), bitmapId));
    }

    private void drawRandomCircle(Canvas canvas, int viewWidth, int viewHeight, int avgWidth){
        float x = RandomUtils.randomFloat(viewWidth);
        float y = RandomUtils.randomFloat(viewHeight);
        float radius = RandomUtils.randomFloat(avgWidth/2);
        Paint circleColor = RandomUtils.randomElement(mForegrounds);
        canvas.drawCircle(x,y,radius, circleColor);
    }





}
