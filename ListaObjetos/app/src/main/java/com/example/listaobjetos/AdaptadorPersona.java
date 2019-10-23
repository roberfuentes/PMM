package com.example.listaobjetos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class AdaptadorPersona extends ArrayAdapter<Persona> {
    public Activity activity;


    public AdaptadorPersona(Activity actividad){
        super(actividad, R.layout.desplegado_nombre_apellido, MainActivity.personas);
        this.activity = actividad; //Le pasas el constructor al contexto de la activity

    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater =  activity.getLayoutInflater(); //Una vez pasado el contexto pillará el layout inflater y a partir de ahí aplicas el XML
        View item = inflater.inflate(R.layout.desplegado_nombre_apellido, null);

        TextView lblName = (TextView)item.findViewById(R.id.fieldName);
        TextView lblSurname = (TextView)item.findViewById(R.id.fieldSurname);

        lblName.setText(MainActivity.personas[position].getNombre());
        lblSurname.setText(MainActivity.personas[position].getApellido());

        return item;
    }
}
