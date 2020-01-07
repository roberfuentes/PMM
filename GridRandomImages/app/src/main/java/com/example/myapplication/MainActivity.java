package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    GridView mGridView;
    ImageView mImageView;
    Button mBtnShuffle;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //View to Controller variables
        mGridView = (GridView)findViewById(R.id.gridView);
        mImageView = (ImageView)findViewById(R.id.selectedImage);
        mBtnShuffle = (Button)findViewById(R.id.btnShuffle);


        mGridView.setAdapter(new ImageAdapter(this));



        //FUNCTIONS ON CLICK LISTENER
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Drawable d = getResources().getDrawable(ImageAdapter.array[position]);
                mImageView.setImageDrawable(d);
                mImageView.getLayoutParams().height = 400;
                mImageView.getLayoutParams().height = 350;
                //mImageView.setImageResource(R.drawable.img1);
                System.out.println("HOLAAA: " + d);
            }
        });

        mBtnShuffle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mGridView.setAdapter(new ImageAdapter(MainActivity.this));

            }
        });
    }
}
