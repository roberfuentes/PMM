package com.example.diferentestiposdeeventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mBtnOne;
    Button mBtnThree;
    Context ctx = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btnThree);



        mBtnOne = (Button)findViewById(R.id.btnOne);

        mBtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "BOTÓN UNO", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void btnClick(View v){
        Toast.makeText(this, "BOTÓN DOS", Toast.LENGTH_SHORT).show();
    }

}


