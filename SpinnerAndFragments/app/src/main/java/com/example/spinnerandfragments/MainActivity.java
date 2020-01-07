package com.example.spinnerandfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    private ArrayList<Persona> mPersona;
    private AdapterSpinner adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        Spinner mSpinner = (Spinner) findViewById(R.id.spinner);
        adapter = new AdapterSpinner(this, mPersona);

        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                replaceFragment(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        if (savedInstanceState == null)
        {
            addFragment(0);

        }
    }
        private void initList () {
        mPersona = new ArrayList<Persona>();
        mPersona.add(new Persona("Rober", "Fuentes Romero", "22", "Grado Superior", "Programador", R.drawable.rober));
        mPersona.add(new Persona("Mew", "Mivi", "1", "Comer y dormir", "Superar retos diariamente", R.drawable.mewv3));
    }

        public void replaceFragment ( int position){
        Persona p = mPersona.get(position);
        Fragment fragment = new fragment_details();


        Bundle bundle = new Bundle();
        bundle.putSerializable("persona", p);
        fragment.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameBottom, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void addFragment(int position){
        Persona p = mPersona.get(position);
        Fragment fragment = new fragment_details();


        Bundle bundle = new Bundle();
        bundle.putSerializable("persona", p);
        fragment.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.frameBottom, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

}