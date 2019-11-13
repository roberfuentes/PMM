package com.example.examennoviembre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //Arrays
    public static MedioTransporte[] electricos = new MedioTransporte[]{
            new MedioTransporte("skate", "Roxi", "12", R.drawable.skate),
            new MedioTransporte("patinete", "Roxi", "15", R.drawable.patinete),
            new MedioTransporte("monociclo", "Oneil", "18", R.drawable.monociclo1)};

    public static MedioTransporte[] bicis = new MedioTransporte[]{
            new MedioTransporte("Paseo", "Orbea", "15", R.drawable.bici1),
            new MedioTransporte("Ciudad", "Cube", "20", R.drawable.bici2),
            new MedioTransporte("Montaña", "Bike", "25", R.drawable.bici3)};

    public static MedioTransporte[] coches = new MedioTransporte[]{
            new MedioTransporte("Megane", "Renault", "60", R.drawable.megan1),
            new MedioTransporte("Leon", "Seat", "70", R.drawable.leon3),
            new MedioTransporte("Fiesta", "Ford", "75", R.drawable.fiesta2)};


    //Variables
    ImageView mPicElectrico, mPicBicis, mPicCoches;
    Button mButtonNext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mPicElectrico = (ImageView)findViewById(R.id.picElectrico);
        mPicBicis = (ImageView)findViewById(R.id.picBici);
        mPicCoches = (ImageView)findViewById(R.id.picCoches);
        mButtonNext = (Button)findViewById(R.id.btn);


        mPicElectrico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPicBicis.setSelected(false);
                mPicElectrico.setSelected(true);
                mPicCoches.setSelected(false);

                /*
                If clickonlistener save a variable and if you click on button then check what varaible is saved
                Example:
                    if mpicElectrico
                        variable = 1
                    if button click listener
                        check variable
                        a lot of if else and go to the desired class
                 */


            }
        });
        mPicBicis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, Bicis.class);
                //startActivity(intent);
                mPicElectrico.setSelected(false);
                mPicBicis.setSelected(true);
                mPicCoches.setSelected(false);




            }
        });

        mPicCoches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPicElectrico.setSelected(false);
                mPicBicis.setSelected(false);
                mPicCoches.setSelected(true);



            }
        });



        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPicElectrico.isSelected()){
                    Intent intent = new Intent(MainActivity.this, Electrico.class);
                    startActivity(intent);
                }else if(mPicBicis.isSelected()){
                    Intent intent = new Intent(MainActivity.this, Bicis.class);
                    startActivity(intent);
                }else if(mPicCoches.isSelected()){
                    Intent intent = new Intent(MainActivity.this, Coches.class);
                    startActivity(intent);
                }
            }
        });



    }
}
