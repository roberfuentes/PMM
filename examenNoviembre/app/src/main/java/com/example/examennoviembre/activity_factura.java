package com.example.examennoviembre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_factura extends AppCompatActivity {


    ImageView mSelectedPicture;

    TextView mFieldModelo, mFieldTime, mFieldPrice, mFieldExtras, mFieldSeguro, mFieldAlquiler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

       Intent intento = new Intent();
       Bundle myBundle = new Bundle();


        //Fields
        mSelectedPicture = (ImageView)findViewById(R.id.fieldPic);
        mFieldModelo = (TextView)findViewById(R.id.fieldModelo);
        mFieldAlquiler = (TextView)findViewById(R.id.fieldAlquiler);

        mFieldExtras = (TextView)findViewById(R.id.fieldExtras);
        mFieldTime = (TextView)findViewById(R.id.fieldTime);
        mFieldSeguro = (TextView)findViewById(R.id.fieldSeguro);


        mFieldPrice = (TextView)findViewById(R.id.fieldPrice);






       String extras = myBundle.getString("Extras");
        String dias = myBundle.getString("Dias");
        String price = myBundle.getString("Price");


        MedioTransporte medio = (MedioTransporte)getIntent().getSerializableExtra("Electrico");




        mSelectedPicture.setImageResource(medio.getFoto());
        mSelectedPicture.getLayoutParams().width = 300;
        mSelectedPicture.getLayoutParams().height= 200;

        mFieldModelo.setText("Modelo:"+medio.getModelo());
        mFieldAlquiler.setText("Precio por horas:"+medio.getAlquiler());
        mFieldExtras.setText(extras);
        mFieldTime.setText("2");
        mFieldPrice.setText(price);


        System.out.println("testing out here:" + medio.getModelo());





    }
}
