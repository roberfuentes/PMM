package com.example.finalprojectpmm.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class PayActivity extends AppCompatActivity
{

    SQLiteDatabase db;
    ListView lViewOrderLine;
    Button mBtnPay;
    DBHelper dbHelper = null;
    static ImageView imageView;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        mBtnPay = (Button) findViewById(R.id.btnPay);
        dbHelper = new DBHelper(this);
        imageView = (ImageView) findViewById(R.id.imagetest);


        final ArrayList<OrderLine> orderLines = (ArrayList<OrderLine>) getIntent().getSerializableExtra("orderlines");
        id = getIntent().getExtras().getInt("CustomerID");


        lViewOrderLine = (ListView) findViewById(R.id.listOrderLines);

        AdapterOrderLinePay listAdapter = new AdapterOrderLinePay(this, orderLines);

        lViewOrderLine.setAdapter(listAdapter);


        mBtnPay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int total = 0;
                for (OrderLine orderLine : orderLines)
                {
                    int price = 0;
                    if(orderLine.getInsurance()){
                        price +=50;
                    }
                    price += Integer.parseInt(orderLine.getPrice()) * Integer.parseInt(orderLine.getQuantity());
                    total += price;

                }
                long orderID=0;
                //Insert order price
                if ((orderID= dbHelper.insertOrder(id, total))>0){

                    for (OrderLine orderLine : orderLines)
                    {

                        int price = Integer.parseInt(orderLine.getPrice());
                        int quantity = Integer.parseInt(orderLine.getQuantity());
                        boolean insurance = orderLine.getInsurance();
                        int image = orderLine.getImg();
                        long orderlineOrderId = orderID;
                        int productID = orderLine.getProductID();
                        System.out.println("El product id aqui es" + productID);


                        Drawable drawable = getResources().getDrawable(image);
                        Bitmap bitImage = ((BitmapDrawable) drawable).getBitmap();

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);



                        dbHelper.insertOrderLine(price, quantity, insurance, productID, orderlineOrderId);
                        System.out.println("orderline inserted");

                    }

                    Intent intent = new Intent(PayActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("CustomerID", id);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }else{
                    Toast.makeText(PayActivity.this, "The order hasn't been inserted!", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }
}
