package com.example.examen;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterEnvio extends ArrayAdapter<Zonas>
{

    Activity context;
    public AdapterEnvio(Activity context){
        super(context,R.layout.dropdown_zonas, MainActivity.dataZonas);
        this.context = context;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent){
        View viewDesplegado = getView(position, convertView, parent);
        return viewDesplegado;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.dropdown_zonas,null);

        TextView mTxtContinent = (TextView)view.findViewById(R.id.txtContinente);
        TextView mTxtPeso = (TextView)view.findViewById(R.id.txtPeso);

        mTxtContinent.setText(MainActivity.dataZonas[position].getContinente());
        mTxtPeso.setText(String.valueOf(MainActivity.dataZonas[position].getPeso()));

        return view;
    }



}
