package com.example.finalprojectpmm.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.finalprojectpmm.Adapters.AdapterOrder;
import com.example.finalprojectpmm.Adapters.AdapterOrderLineList;
import com.example.finalprojectpmm.DatabaseHelper.DBHelper;
import com.example.finalprojectpmm.Models.Customer;
import com.example.finalprojectpmm.Models.Order;
import com.example.finalprojectpmm.Models.OrderLine;
import com.example.finalprojectpmm.R;

import java.util.ArrayList;


public class ListDataActivity extends AppCompatActivity
{
    ListView  viewOrder, viewOrderLine;
    ArrayList<Order> orderArrayList;
    ArrayList<OrderLine> orderLineArrayList;
    TextView customerData;
    Button mRefresh;
    int id;

    DBHelper dbHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        dbHelper = new DBHelper(this);

        customerData = (TextView)findViewById(R.id.customerData);
        viewOrder = (ListView)findViewById(R.id.lViewOrder);
        viewOrderLine = (ListView)findViewById(R.id.lViewOrderLine);
        mRefresh = (Button)findViewById(R.id.btnRefresh);

        id = getIntent().getExtras().getInt("CustomerID");
        Customer customer  = dbHelper.getCustomerUsername(id);

        customerData.setText("Welcome to your orders " +  customer.getUsername() + " with ID:" + id);


        orderArrayList = dbHelper.retrieveOrder(id);
        System.out.println("There are " + orderArrayList.size() + " orders from the customer " + id);
        AdapterOrder adapterOrder = new AdapterOrder(this,orderArrayList);


        viewOrder.setAdapter(adapterOrder);


        System.out.println("Adapter done");


        mRefresh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                refreshLists();
            }
        });

        viewOrder.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Order order = (Order)parent.getItemAtPosition(position);
                showOrderLineListView(order);
            }
        });



    }


    public void refreshLists(){
        viewOrder.setAdapter(null);
        viewOrderLine.setAdapter(null);

        //Reload orders
        orderArrayList = dbHelper.retrieveOrder(id);
        AdapterOrder adapter = new AdapterOrder(this, orderArrayList);
        viewOrder.setAdapter(adapter);
        dbHelper.close();

    }

    public void showOrderLineListView(Order order){

        int id = order.getId();

        orderLineArrayList= dbHelper.retrieveOrderLine(id);
        AdapterOrderLineList adapterOrderLineList = new AdapterOrderLineList(this, orderLineArrayList);
        viewOrderLine.setAdapter(adapterOrderLineList);
        dbHelper.close();
    }
}
