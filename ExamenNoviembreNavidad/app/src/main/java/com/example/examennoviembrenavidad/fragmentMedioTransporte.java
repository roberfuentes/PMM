package com.example.examennoviembrenavidad;


import android.os.Bundle;

import androidx.annotation.Nullable;
import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentMedioTransporte extends Fragment
{


    int number;








    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            number = getArguments().getInt("num");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        int tiposMedio[] = {R.drawable.patinete, R.drawable.bicis, R.drawable.coches};

        View v = inflater.inflate(R.layout.fragment_medio_transporte, container, false);
        ImageView mImageResult = (ImageView)v.findViewById(R.id.imgResult);

        mImageResult.setImageResource(tiposMedio[number]);

        return v;

    }

}
