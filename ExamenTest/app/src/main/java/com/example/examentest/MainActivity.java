package com.example.examentest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity
{

    Spinner mSpnPizzas;
    Button mBtnCalculo;

    public static Pizza[] dataPizza = new Pizza[]{
            new Pizza("queso", "york", "mediana", R.drawable.pizza),
            new Pizza("queso", "jamon", "mediana", R.drawable.pizza),
            new Pizza("queso", "peperoni", "mediana", R.drawable.pizza)
};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpnPizzas = (Spinner)findViewById(R.id.spnPizzas);
        mBtnCalculo = (Button)findViewById(R.id.btnCalculo);

        AdapterPizzas adapterPizzas = new AdapterPizzas(this);

        mSpnPizzas.setAdapter(adapterPizzas);


        mBtnCalculo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SelectedPizza.class);
                Bundle myBundle = new Bundle();
                int indexPizza = mSpnPizzas.getSelectedItemPosition();
                Pizza pizza = dataPizza[indexPizza];

                myBundle.putSerializable("pizza", pizza);

                intent.putExtras(myBundle);

                startActivity(intent);



            }
        });







    }
}
