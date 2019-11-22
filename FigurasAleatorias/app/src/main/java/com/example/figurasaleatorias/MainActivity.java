package com.example.figurasaleatorias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button mBtnRandom, mBtnDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnRandom = (Button)findViewById(R.id.btnRandom);
        mBtnDrawable = (Button)findViewById(R.id.btnDrawable);

        mBtnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento  = new Intent(MainActivity.this, RandomActivity.class);
                startActivity(intento) ;
            }
        });

        mBtnDrawable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento  = new Intent(MainActivity.this, DrawableActivity.class);
                startActivity(intento);
            }
        });
    }
}
