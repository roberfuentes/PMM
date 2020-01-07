package com.example.examennoviembrenavidad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{

    /* Contenido de los spinner segun los medios elegidos */

    private MedioTransporte[] patinetes = new MedioTransporte[]{
            new MedioTransporte("skate", "Roxi", "12", R.drawable.skate),
            new MedioTransporte("patinete", "Roxi", "15", R.drawable.monociclo1),
            new MedioTransporte("monociclo", "Oneil", "18", R.drawable.monociclo2)};

    private MedioTransporte[] bicis = new MedioTransporte[]{
            new MedioTransporte("Paseo", "Orbea", "15", R.drawable.bici1),
            new MedioTransporte("Ciudad", "Cube", "20", R.drawable.bici2),
            new MedioTransporte("Montaña", "Bike", "25", R.drawable.bici3)};

    private MedioTransporte[] coches = new MedioTransporte[]{
            new MedioTransporte("Megane", "Renault", "60", R.drawable.megan1),
            new MedioTransporte("Leon", "Seat", "70", R.drawable.leon3),
            new MedioTransporte("Fiesta", "Ford", "75", R.drawable.fiesta2)};

    boolean activated;

    //Variables
    ImageView mPicPatinetes, mPicBicis, mPicCoches;
    ImageView mImageResult;
    Button mButtonContinue;

    int tiposMedio[] = {R.drawable.patinete, R.drawable.bicis, R.drawable.coches};
    private MedioTransporte[] selectedTransport;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPicPatinetes = (ImageView)findViewById(R.id.picPatinetes);
        mPicBicis = (ImageView)findViewById(R.id.picBicis);
        mPicCoches = (ImageView)findViewById(R.id.picCoches);
        mButtonContinue = (Button)findViewById(R.id.btn);
        activated = false;



        mPicPatinetes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                selectedTransport = patinetes;
                if(activated){
                    replaceFragment(0);
                }
                else{
                    addFragment(0);
                }


            }
        });

        mPicBicis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                selectedTransport = bicis;
                if(activated){
                    replaceFragment(1);
                }
                else{
                    addFragment(1);
                }

            }
        });

        mPicCoches.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                selectedTransport = coches;
                if(activated){
                    replaceFragment(2);
                }
                else{
                    addFragment(2);
                }
            }
        });

        mButtonContinue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nextScreen(selectedTransport);
            }
        });



    }


    public void nextScreen(MedioTransporte[] selectedTransport){
        Bundle bundle = new Bundle();
        bundle.putSerializable("array", selectedTransport);
        Intent intent = new Intent(MainActivity.this, SelectVehicle.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void addFragment(int num){
        Bundle bundle = new Bundle();
        bundle.putInt("num", num);
        Fragment fragment = new fragmentMedioTransporte();
        fragment.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragmentMedioTransporte, fragment);
        ft.commit();
    }
    public void replaceFragment(int num){
        Bundle bundle = new Bundle();
        bundle.putInt("num", num);
        Fragment fragment = new fragmentMedioTransporte();
        fragment.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentMedioTransporte, fragment);
        ft.commit();
    }
}
