package com.example.examennoviembrenavidad;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterSpinner extends ArrayAdapter<MedioTransporte>
{

    public AdapterSpinner(Context context, MedioTransporte[] array){
        super(context, 0, array);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    public View initView(int position, View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_spinner, parent, false);

        TextView modelo = (TextView)convertView.findViewById(R.id.spnModelo);
        TextView marca = (TextView)convertView.findViewById(R.id.spnMarca);
        TextView alquiler = (TextView)convertView.findViewById(R.id.spnAlquiler);
        ImageView image = (ImageView)convertView.findViewById(R.id.spnImage);


        MedioTransporte currentMedio = getItem(position);

        if(currentMedio!= null){
            modelo.setText(currentMedio.modelo);
            marca.setText(currentMedio.marca);
            alquiler.setText(currentMedio.alquiler);
            image.setImageResource(currentMedio.foto);
            image.getLayoutParams().height = 100;
            image.getLayoutParams().width= 150;


            return convertView;
        }
        else{
            return convertView;
        }



    }
}
