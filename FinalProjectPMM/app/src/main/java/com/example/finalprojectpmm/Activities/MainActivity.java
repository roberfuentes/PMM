package com.example.finalprojectpmm.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectpmm.DatabaseHelper.DBHelper;
import com.example.finalprojectpmm.Models.Customer;
import com.example.finalprojectpmm.R;

public class MainActivity extends AppCompatActivity
{

    TextView mTextWelcome;
    Button mBtnBuy, mBtnList;

    int id;
    DBHelper dbHelper;
    ListView mLview;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnBuy = (Button) findViewById(R.id.btnBuy);
        mBtnList = (Button)findViewById(R.id.btnList);
        dbHelper = new DBHelper(MainActivity.this);
        mTextWelcome = (TextView)findViewById(R.id.textWelcome);


        id = getIntent().getExtras().getInt("CustomerID");
        Log.i("ID Customer", Integer.toString(id));

        if(id!=0){
            Customer customer = dbHelper.getCustomerUsername(id);
            String name = customer.getUsername();
            mTextWelcome.setText("Welcome to the app " + name);
        }


        dbHelper = new DBHelper(this);

        mBtnBuy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, BuyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("CustomerID",id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mBtnList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("CustomerID", id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }


}
