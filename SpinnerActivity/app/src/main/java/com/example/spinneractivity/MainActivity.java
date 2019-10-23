package com.example.spinneractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner mSpinner;

    public static Persona[] personas = new Persona[]{
            new Persona("Rober", "Fuentes", 21),
            new Persona("Ferran", "Fuentes", 14),
            new Persona("Mei", "Mew", 1)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = (Spinner)findViewById(R.id.spinner1);
        AdapterPersona myAdapter = new AdapterPersona(this);
        mSpinner.setAdapter(myAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Persona p = personas[position];
                Intent intent = new Intent(getApplicationContext(), PassVariables.class);
                Bundle myBundle = new Bundle();
                myBundle.putSerializable("persona", p);
                intent.putExtras(myBundle);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



}
