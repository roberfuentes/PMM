package com.example.examentest;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterPizzas extends ArrayAdapter<Pizza>
{

    Activity context;

    public AdapterPizzas(Activity context){
        super(context, R.layout.pizza_spinner, MainActivity.dataPizza);
        this.context = context;

    }



    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.pizza_spinner, null);

        TextView mIngrediente1 = (TextView)view.findViewById(R.id.spnIngrediente1);
        TextView mIngrediente2 = (TextView)view.findViewById(R.id.spnIngrediente2);
        TextView mTamano = (TextView)view.findViewById(R.id.spnTamano);
        ImageView mFoto = (ImageView) view.findViewById(R.id.spnFoto);

        mIngrediente1.setText(MainActivity.dataPizza[position].getIngrediente1());
        mIngrediente2.setText(MainActivity.dataPizza[position].getIngrediente2());
        mTamano.setText(MainActivity.dataPizza[position].getTamano());
        mFoto.setImageResource(MainActivity.dataPizza[position].getFoto());
        mFoto.getLayoutParams().width=100;
        mFoto.getLayoutParams().height=80;

        return view;

    }

    public View getDropDownView(int position, View convertView, ViewGroup parent){
        View view = getView(position, convertView, parent);
        return view;

    }
}
