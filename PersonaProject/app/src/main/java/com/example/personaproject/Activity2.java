package com.example.personaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {


    TextView mAllInfo;
    TextView mName;
    TextView mSurname;
    TextView mAge;
    ImageView mPicture;
    Persona mPer;
    Button mbtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mAllInfo = (TextView)findViewById(R.id.allInfo);
        mName = (TextView)findViewById(R.id.fieldName);
        mSurname= (TextView)findViewById(R.id.fieldSurname);
        mAge = (TextView)findViewById(R.id.fieldAge);
        mPicture = (ImageView)findViewById(R.id.fieldPicture);

        mbtnBack = (Button)findViewById(R.id.btnBack);




        mPer = (Persona)getIntent().getSerializableExtra("object");

        mAllInfo.setText(mPer.toString());

        mName.setText("NAME: " + mPer.getNombre());
        mSurname.setText("SURNAME: " + mPer.getApellido());
        mAge.setText("AGE: " + String.valueOf(mPer.getEdad()));

        mPicture.setImageResource(mPer.getFoto());
        mPicture.getLayoutParams().height = 420;
        mPicture.getLayoutParams().width = 270;


        mbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
            }
        });
    }


}
