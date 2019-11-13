package com.example.examennoviembre;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterCoches extends ArrayAdapter<MedioTransporte> {

    Activity context;

    public AdapterCoches(Activity context){
        super(context, R.layout.adapter_electricos_bicis_coches, MainActivity.coches);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.adapter_electricos_bicis_coches, null);


        TextView mModelo = (TextView)view.findViewById(R.id.modeloElectrico);
        TextView mMarca = (TextView)view.findViewById(R.id.marcaElectrico);
        TextView mAlquiler= (TextView)view.findViewById(R.id.alquilerElectrico);
        ImageView mFoto = (ImageView)view.findViewById(R.id.fotoElectrico);

        mModelo.setText(MainActivity.coches[position].getModelo());
        mMarca.setText(MainActivity.coches[position].getMarca());
        mAlquiler.setText(MainActivity.coches[position].getAlquiler());
        mFoto.setImageResource(MainActivity.coches[position].getFoto());

        mFoto.getLayoutParams().height = 80;
        mFoto.getLayoutParams().width = 100;


        return view;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent){
        View view = getView(position, convertView, parent);
        return view;

    }



}
