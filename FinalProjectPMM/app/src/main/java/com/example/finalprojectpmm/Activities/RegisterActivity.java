package com.example.finalprojectpmm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalprojectpmm.DatabaseHelper.DBHelper;
import com.example.finalprojectpmm.R;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity
{

    Button mButtonRegister;
    EditText mEntryUsername, mEntryPassword1, mEntryPassword2;

    DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mButtonRegister = (Button)findViewById(R.id.buttonRegister);
        mEntryUsername = (EditText)findViewById(R.id.registerEntryUsername);
        mEntryPassword1 = (EditText)findViewById(R.id.registerEntryPassword1);
        mEntryPassword2 = (EditText)findViewById(R.id.registerEntryPassword2);
        dbHelper = new DBHelper(RegisterActivity.this);


        mButtonRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String username = mEntryUsername.getText().toString();
                String password1 = mEntryPassword1.getText().toString();
                String password2 = mEntryPassword2.getText().toString();

                if(password1.equals(password2)){
                    long id =dbHelper.registerCustomer(username, password1);
                    if(id!=-1){
                        Toast.makeText(RegisterActivity.this, "Your account has been registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Your account hasn't been registered", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Passwords are incorrects, please write them correctly", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
