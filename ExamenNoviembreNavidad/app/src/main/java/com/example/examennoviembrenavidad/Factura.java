package com.example.examennoviembrenavidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Factura extends AppCompatActivity
{

    ImageView mFacImagen;
    TextView mFacModelo, mFacPrecio, mFacExtras, mFacDias, mFacInsurance, mFacTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        mFacImagen = findViewById(R.id.facImagen);
        mFacModelo = findViewById(R.id.facModelo);
        mFacPrecio = findViewById(R.id.facPrecioHora);
        mFacExtras = findViewById(R.id.facTotalExtra);
        mFacDias = findViewById(R.id.facDias);
        mFacInsurance = findViewById(R.id.facSeguro);
        mFacTotal = findViewById(R.id.facCosteTotal);



        Bundle bundle = getIntent().getExtras();
        MedioTransporte transporte = (MedioTransporte)getIntent().getSerializableExtra("VehicleDetails");
        int totalExtras = bundle.getInt("Extras");
        System.out.println("extras: "+totalExtras);
        int daysAlquiler = bundle.getInt("Dias");
        int total = bundle.getInt("Total");
        boolean insurance = bundle.getBoolean("Insurance");



        mFacImagen.setImageResource(transporte.getFoto());
        mFacModelo.setText(transporte.getModelo());
        mFacPrecio.setText(transporte.getAlquiler());
        mFacExtras.setText("Extras:" + totalExtras);
        mFacDias.setText("Dias:"+daysAlquiler);
        if(insurance){
            mFacInsurance.setText("Seguro: Con seguro");
        }else{
            mFacInsurance.setText("Seguro: Sin seguro");
        }
        mFacTotal.setText("Coste"+total);



    }
}
