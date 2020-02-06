package com.example.finalprojectpmm.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.finalprojectpmm.DatabaseHelper.DBHelper;
import com.example.finalprojectpmm.R;

public class MainActivity extends AppCompatActivity
{

    Button mBtnBuy, mBtnList;

    DBHelper dbHelper;

    ListView mLview;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnBuy = (Button) findViewById(R.id.btnBuy);
        mBtnList = (Button)findViewById(R.id.btnList);


        dbHelper = new DBHelper(this);

        mBtnBuy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, BuyActivity.class);

                startActivity(intent);
            }
        });
        mBtnList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
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
        switch(item.getItemId()){
            case R.id.aboutApp:
                Toast.makeText(MainActivity.this, "About App", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.aboutUs:
                Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
