package com.example.spinneractivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterPersona extends ArrayAdapter<Persona> {
    public Activity activity;


    public AdapterPersona(Activity myActivity){
        super(myActivity, R.layout.desplegado, MainActivity.personas);
        this.activity = myActivity;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent){
        View vistaDesplegada = getView(position, convertView, parent);
        return vistaDesplegada;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = activity.getLayoutInflater();
        View item = inflater.inflate(R.layout.desplegado, null);

        TextView lblName = (TextView)item.findViewById(R.id.fieldName);
        TextView lblSurname = (TextView)item.findViewById(R.id.fieldSurname);
        TextView lblAge = (TextView)item.findViewById(R.id.fieldAge);

        lblName.setText(MainActivity.personas[position].getNombre());
        lblSurname.setText(MainActivity.personas[position].getApellido());
        lblAge.setText(Integer.toString(MainActivity.personas[position].getEdad()));
        return item;
    }

}