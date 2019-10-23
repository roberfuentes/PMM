package com.example.spinnerpersona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner mSpinner;

    public static Persona[] personas = new Persona[]{
            new Persona("Rober", "Fuentes"),
            new Persona("Ferran", "Fuentes"),
            new Persona("Mei", "Mew")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSpinner = (Spinner)findViewById(R.id.spinner1);
        AdapterPersona myAdapter = new AdapterPersona(this);
        mSpinner.setAdapter(myAdapter);



    }
}
