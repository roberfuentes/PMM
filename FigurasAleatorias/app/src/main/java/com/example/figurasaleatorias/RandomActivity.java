package com.example.figurasaleatorias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class RandomActivity extends AppCompatActivity {


    RandomClass mDrawingArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        mDrawingArea = (RandomClass)findViewById(R.id.drawing_area);
    }

    public void redraw(View btnClick){
        mDrawingArea.invalidate();
    }
}
