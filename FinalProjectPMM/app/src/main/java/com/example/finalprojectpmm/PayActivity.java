package com.example.finalprojectpmm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity
{

    ListView lViewOrderLine;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);


        ArrayList<OrderLine> orderLines = (ArrayList<OrderLine>)getIntent().getSerializableExtra("orderlines");
        System.out.println("Size after"+orderLines.size());

        lViewOrderLine = (ListView)findViewById(R.id.listOrderLines);

        AdapterOrderLinePayment listAdapter = new AdapterOrderLinePayment(this, 0, orderLines);

        lViewOrderLine.setAdapter(listAdapter);



    }
}
