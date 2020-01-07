package com.example.spinnerandfragments;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterSpinner extends ArrayAdapter<Persona>
{

    public AdapterSpinner(Context context, ArrayList<Persona> array){
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

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_spinner, parent, false);
        }

            TextView nombre = (TextView)convertView.findViewById(R.id.nombre);
            TextView apellidos = (TextView)convertView.findViewById(R.id.apellidos);
            TextView edad = (TextView)convertView.findViewById(R.id.edad);
            ImageView imagen = (ImageView) convertView.findViewById(R.id.imagen);

            Persona currentPersona = getItem(position);

            if(currentPersona != null){
                nombre.setText(currentPersona.getNombre());
                apellidos.setText(currentPersona.getApellidos());
                edad.setText(currentPersona.getEdad());
                imagen.setImageResource(currentPersona.getImagen());
                imagen.getLayoutParams().height = 200;
                imagen.getLayoutParams().width= 150;
            }



            return convertView;

    }
}
