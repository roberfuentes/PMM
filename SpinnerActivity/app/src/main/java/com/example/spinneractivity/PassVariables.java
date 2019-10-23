package com.example.spinneractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PassVariables extends AppCompatActivity {

    Persona pInfo;
    TextView viewAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_variables);

        Bundle pickBundle = new Bundle();
        pInfo = (Persona)getIntent().getSerializableExtra("persona");
        viewAll = (TextView)findViewById(R.id.fieldAll);
        viewAll.setText(pInfo.toString());

    }
}
