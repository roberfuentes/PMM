package com.example.rober.holamundo3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WindowOne extends AppCompatActivity
{
    EditText mEditTxt;
    Button mBtnToSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_one);

        mEditTxt = (EditText)findViewById(R.id.editTxt);
        mBtnToSecond = (Button)findViewById(R.id.btnToSecond);

        mBtnToSecond.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String text = mEditTxt.getText().toString();
                Intent intent = new Intent(WindowOne.this, WindowTwo.class);
                intent.putExtra("textKey", text);
                startActivity(intent);
            }
        });

    }
}
