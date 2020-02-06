package com.example.finalprojectpmm.Activities;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.finalprojectpmm.Adapters.AdapterOrderLinePay;
import com.example.finalprojectpmm.DatabaseHelper.DBHelper;
import com.example.finalprojectpmm.Models.OrderLine;
import com.example.finalprojectpmm.R;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity
{

    SQLiteDatabase db;
    ListView lViewOrderLine;
    Button mBtnPay;
    DBHelper dbHelper = null;
    static ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        mBtnPay = (Button)findViewById(R.id.btnPay);
        dbHelper = new DBHelper(this);
        imageView = (ImageView)findViewById(R.id.imagetest);



        final ArrayList<OrderLine> orderLines = (ArrayList<OrderLine>)getIntent().getSerializableExtra("orderlines");


        lViewOrderLine = (ListView)findViewById(R.id.listOrderLines);

        AdapterOrderLinePay listAdapter = new AdapterOrderLinePay(this,  orderLines);

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

                if((custID = dbHelper.insertCustomer("Roberv3"))!= -1){
                    Toast.makeText(PayActivity.this, "Customer has been inserted and his long is "+ custID, Toast.LENGTH_SHORT).show();


                    if((orderID = dbHelper.insertOrder(custID, cost))!=-1){
                        Toast.makeText(PayActivity.this, "Order has been inserted", Toast.LENGTH_SHORT).show();
                        for(OrderLine orderLine : orderLines){
                            int price = Integer.parseInt(orderLine.getPrice());
                            int quantity = Integer.parseInt(orderLine.getQuantity());
                            boolean insurance = orderLine.getInsurance();
                            boolean headphones = orderLine.getHeadphones();
                            if((orderLineID = dbHelper.insertOrderLine(price, quantity, insurance, headphones, orderLine.getImg(), orderID))!=-1){


                                Toast.makeText(PayActivity.this, "OrderLine has been inserted", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(PayActivity.this, "OrderLine has not been inserted", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }else{
                        Toast.makeText(PayActivity.this, "Order has not been inserted", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(PayActivity.this, "Customer has not been inserted: " +custID , Toast.LENGTH_SHORT).show();
                }
                dbHelper.retrieveCustomerData();
                dbHelper.retrieveOrderData();
                dbHelper.retrieveOrderLineData();


                //System.out.println("Theorically works");

            }
        });



    }
}