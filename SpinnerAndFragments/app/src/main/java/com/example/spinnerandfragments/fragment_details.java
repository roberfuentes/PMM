package com.example.spinnerandfragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_details extends Fragment
{
    private static final String PERSON = "person";

    private Persona mPerson;


    public fragment_details()
    {
        // Required empty public constructor
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            mPerson =(Persona) bundle.getSerializable("persona");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_details,container, false);
        TextView nombre = v.findViewById(R.id.frag_name);
        TextView apellidos = (TextView) v.findViewById(R.id.frag_apellidos);
        TextView edad = (TextView) v.findViewById(R.id.frag_edad);
        TextView estudios = (TextView) v.findViewById(R.id.frag_estudios);
        TextView trabajo = (TextView) v.findViewById(R.id.frag_trabajo);
        ImageView imagen = (ImageView) v.findViewById(R.id.frag_imagen);

        System.out.println("nombre" + mPerson.getNombre());
        nombre.setText("Nombre:" + mPerson.getNombre());
        apellidos.setText("Apellidos" + mPerson.getApellidos());
        edad.setText("Edad:" + mPerson.getEdad());
        estudios.setText("Estudios:" + mPerson.getEstudios());
        trabajo.setText("Trabajo:" + mPerson.getTrabajos());
        imagen.setImageResource(mPerson.getImagen());
        imagen.getLayoutParams().height = 200;
        imagen.getLayoutParams().width= 150;

        return v;



    }

}
