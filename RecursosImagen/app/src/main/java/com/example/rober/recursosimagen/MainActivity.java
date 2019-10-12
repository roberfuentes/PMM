package com.example.rober.recursosimagen;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
{
    MediaPlayer player;
    ToggleButton mBtnToggle;
    Button mBtnImage;
    ImageView mImage;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(this, R.raw.desigual);
        mBtnToggle = (ToggleButton)findViewById(R.id.btnToggle);
        mBtnImage = (Button)findViewById(R.id.btnImage);
        mImage = (ImageView)findViewById(R.id.imageView);

        mBtnToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(mBtnToggle.isChecked())
                    player.start();
                else
                    player.pause();
            }
        });

        mBtnImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mImage.setImageResource(R.drawable.marcadesigual);
            }
        });




    }

}
