package com.example.fragmentosdiversos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Random;

import androidx.annotation.Nullable;

public class DynamicFragment extends Fragment
{
    //private static final String ARG_PARAM1 = "param1";
    int numero;

    public static DynamicFragment newInstance(int number){
        DynamicFragment d = new DynamicFragment();
        Bundle args = new Bundle();
        args.putInt("num", number);
        d.setArguments(args);
        return d;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        numero = getArguments().getInt("num");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        int pics[] = {R.drawable.air, R.drawable.fire, R.drawable.hielo};
        Random r = new Random();
        View v = null;
        TextView tv = null;
        ImageView iv = null;
        if(numero % 2 == 0)
        {
            v = inflater.inflate(R.layout.fram_simple, container,false);
            tv = v.findViewById(R.id.frase1);
            iv = v.findViewById(R.id.imagen);
            iv.setImageResource(pics[r.nextInt(3)]);
        }else{
            v = inflater.inflate(R.layout.fram_simple_2, container, false);
            tv = v.findViewById(R.id.frase2);
        }

        tv.setText("Fragmento numero: " + numero);

        return v;

    }
}
