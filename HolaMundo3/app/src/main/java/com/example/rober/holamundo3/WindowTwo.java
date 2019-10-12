package com.example.rober.holamundo3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WindowTwo extends AppCompatActivity
{
    TextView mTxtView;
    Button mBtnToOne;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_two);

        mTxtView = (TextView)findViewById(R.id.txtView);
        mBtnToOne = (Button)findViewById(R.id.btnToOne);
        Bundle extras = getIntent().getExtras();
        String text = extras.getString("textKey");

        mTxtView.setText(text);

        mBtnToOne.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intentActivity = new Intent(getApplicationContext(), WindowOne.class);
                startActivity(intentActivity);
            }
        });


    }
}
