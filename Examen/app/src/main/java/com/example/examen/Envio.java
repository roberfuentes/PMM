package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Envio extends AppCompatActivity
{

    TextView mContinent, mTarifa, mPeso, mDecoracion, mCoste;
    ImageView mImageContinent;
    Zonas mZona;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio);


        mContinent = (TextView)findViewById(R.id.fieldContinent);
        mTarifa = (TextView)findViewById(R.id.fieldTarifa);
        mPeso = (TextView)findViewById(R.id.fieldPeso);
        mDecoracion = (TextView)findViewById(R.id.fieldDecoracion);
        mCoste = (TextView)findViewById(R.id.fieldCoste);
        mImageContinent = (ImageView)findViewById(R.id.imgContinent);

        Intent intento = getIntent();
        mZona = (Zonas)getIntent().getSerializableExtra("zona");
        String tarifa = intento.getStringExtra("tarifa");

        String peso = intento.getStringExtra("peso");
        String tarjeta = intento.getStringExtra("tarjeta");
        String regalo = intento.getStringExtra("regalo");


        mImageContinent.setImageResource(mZona.getFoto());
        mImageContinent.getLayoutParams().height = 200;
        mImageContinent.getLayoutParams().width= 250;
        mContinent.setText("Continent:" + mZona.getContinente());
        if(tarifa != null){
            mTarifa.setText("Tarifa" + tarifa);
        }

        mDecoracion.setText("Ha elegido la " + tarjeta + "y el " + regalo);
        mPeso.setText("pesa:"+peso);





    }
}
