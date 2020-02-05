package com.example.finalprojectpmmubuntu;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity
{

    SQLiteDatabase db;
    ListView lViewOrderLine;
    Button mBtnPay;
    DBHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        mBtnPay = (Button)findViewById(R.id.btnPay);
        dbHelper = new DBHelper(this);



        final ArrayList<OrderLine> orderLines = (ArrayList<OrderLine>)getIntent().getSerializableExtra("orderlines");


        lViewOrderLine = (ListView)findViewById(R.id.listOrderLines);

        AdapterOrderLinePayment listAdapter = new AdapterOrderLinePayment(this, 0, orderLines);

        lViewOrderLine.setAdapter(listAdapter);


        mBtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long custID;
                long orderID;
                long orderLineID;
                int cost=0;
                for(OrderLine orderLine : orderLines){
                    int total = Integer.parseInt(orderLine.getPrice()) * Integer.parseInt(orderLine.getQuantity()) ;
                    cost += total;
                }

                if((custID = dbHelper.insertCustomer("Roberv2"))!= -1){
                    Toast.makeText(PayActivity.this, "Customer has been inserted and his long is "+ custID, Toast.LENGTH_SHORT).show();


                    if((orderID = dbHelper.insertOrder(custID, cost))!=-1){
                        Toast.makeText(PayActivity.this, "Order has been inserted", Toast.LENGTH_SHORT).show();
                        for(OrderLine orderLine : orderLines){
                            int price = Integer.parseInt(orderLine.getPrice());
                            int quantity = Integer.parseInt(orderLine.getQuantity());
                            if((orderLineID = dbHelper.insertOrderLine(orderID, price, quantity, orderLine.getImg()))!=-1){

                                Toast.makeText(PayActivity.this, "OrderLine has been inserted", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(PayActivity.this, "OrderLine has not been inserted", Toast.LENGTH_SHORT).show();
                                }

                        }
                    }else{
                        Toast.makeText(PayActivity.this, "Order has not been inserted", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(PayActivity.this, "Customer has not been inserted", Toast.LENGTH_SHORT).show();
                }



            }
        });



    }
}