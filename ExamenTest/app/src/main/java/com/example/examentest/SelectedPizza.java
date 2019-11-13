package com.example.examentest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectedPizza extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_pizza);


        Intent intent = getIntent();

        ImageView mImageFoto = (ImageView)findViewById(R.id.fieldFoto);
        TextView mIngrediente1 = (TextView)findViewById(R.id.fieldIngrediente1);
        TextView mIngrediente2 = (TextView)findViewById(R.id.fieldIngrediente2);
        TextView mTamano= (TextView)findViewById(R.id.fieldTamano);

        Pizza pizza;
        pizza = (Pizza)getIntent().getSerializableExtra("pizza");

        mImageFoto.setImageResource(pizza.getFoto());
        mIngrediente1.setText(pizza.getIngrediente1());
        mIngrediente2.setText(pizza.getIngrediente2());
        mTamano.setText(pizza.getTamano());
        mImageFoto.getLayoutParams().width=200;
        mImageFoto.getLayoutParams().height=150;

    }
}
