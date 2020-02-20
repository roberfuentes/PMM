package com.example.finalprojectpmm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectpmm.DatabaseHelper.DBHelper;
import com.example.finalprojectpmm.R;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity
{

    TextView mIntentRegister;
    DBHelper dbHelper;
    Button mButtonLogin;

    EditText mEntryUsername, mEntryPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEntryUsername = findViewById(R.id.loginEntryUsername);
        mEntryPassword =  findViewById(R.id.loginEntryPassword);
        mButtonLogin = findViewById(R.id.buttonLogin);
        mIntentRegister = findViewById(R.id.intentRegister);
        dbHelper = new DBHelper(LoginActivity.this);

        mButtonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = mEntryUsername.getText().toString();
                String password = mEntryPassword.getText().toString();
                int id = dbHelper.getCustomerId(username, password);
                if (id != -1)
                {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("CustomerID", id);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else
                {
                    Toast.makeText(LoginActivity.this, "Wrong username or password, please try again", Toast.LENGTH_SHORT).show();
                }

            }
        });


        mIntentRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }





}
