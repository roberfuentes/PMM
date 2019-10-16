package com.example.personaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText mName;
    EditText mSurname;
    EditText mAge;
    ImageView mPic;
    Button mBtnGo;
    Persona p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName = (EditText)findViewById(R.id.edName);
        mSurname = (EditText)findViewById(R.id.edSurname);
        mAge = (EditText)findViewById(R.id.edAge);

        mPic = (ImageView)findViewById(R.id.myPicture);
        mBtnGo = (Button)findViewById(R.id.btnGo);


        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);

                String name = mName.getText().toString();
                String surname = mSurname.getText().toString();
                String age = mAge.getText().toString();

                if(TextUtils.isEmpty(age))
                    age = "0";
                p = new Persona(name, surname, Integer.parseInt(age), R.drawable.romanoaspas);
                showToast(p.toString());

                Bundle myBundle = new Bundle();
                myBundle.putSerializable("object", p);
                intent.putExtras(myBundle);
                startActivity(intent);


            }
        });
    }
    protected void showToast(CharSequence text) {
        Context context = getApplicationContext();
        //CharSequence text = getResources().getString(R.string.noNameMsg);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
