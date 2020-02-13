package com.example.finalprojectpmm.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

        mEntryUsername = (EditText) findViewById(R.id.loginEntryUsername);
        mEntryPassword = (EditText) findViewById(R.id.loginEntryPassword);
        mButtonLogin = (Button) findViewById(R.id.buttonLogin);
        mIntentRegister = (TextView) findViewById(R.id.intentRegister);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.aboutApp:
                Toast.makeText(LoginActivity.this, "About App", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.aboutUs:
                Toast.makeText(LoginActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }


}
