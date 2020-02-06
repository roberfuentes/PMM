package com.example.finalprojectpmm.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.finalprojectpmm.Adapters.AdapterCustomer;
import com.example.finalprojectpmm.Adapters.AdapterOrder;
import com.example.finalprojectpmm.Adapters.AdapterOrderLineList;
import com.example.finalprojectpmm.Adapters.AdapterOrderLinePay;
import com.example.finalprojectpmm.DatabaseHelper.DBHelper;
import com.example.finalprojectpmm.Models.Customer;
import com.example.finalprojectpmm.Models.Order;
import com.example.finalprojectpmm.Models.OrderLine;
import com.example.finalprojectpmm.R;

import java.util.ArrayList;


public class ListDataActivity extends AppCompatActivity
{
    ListView viewCustomer, viewOrder, viewOrderLine;
    ArrayList<Customer> customerArrayList;
    ArrayList<Order> orderArrayList;
    ArrayList<OrderLine> orderLineArrayList;
    Button mRefresh;

    DBHelper dbHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        dbHelper = new DBHelper(this);
        viewCustomer = (ListView)findViewById(R.id.lViewCustomer);
        viewOrder = (ListView)findViewById(R.id.lViewOrder);
        viewOrderLine = (ListView)findViewById(R.id.lViewOrderLine);
        mRefresh = (Button)findViewById(R.id.btnRefresh);




        customerArrayList = dbHelper.retrieveCustomers();
        orderArrayList = dbHelper.retrieveOrder(-1);
        orderLineArrayList = dbHelper.retrieveOrderLine(-1);
        AdapterOrder adapterOrder = new AdapterOrder(this,orderArrayList);
        AdapterCustomer adapterCustomer = new AdapterCustomer(this, customerArrayList);
        AdapterOrderLineList adapterOrderLineList = new AdapterOrderLineList(this, orderLineArrayList);

        viewOrder.setAdapter(adapterOrder);
        viewCustomer.setAdapter(adapterCustomer);
        viewOrderLine.setAdapter(adapterOrderLineList);



        mRefresh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                refreshLists();
            }
        });

        viewCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Customer customer = (Customer)parent.getItemAtPosition(position);
                showOrderListView(customer);
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
    }

    public void showOrderListView(Customer customer){
        viewOrderLine.setAdapter(null);

        int id = customer.getId();

        orderArrayList = dbHelper.retrieveOrder(id);
        AdapterOrder adapterOrder = new AdapterOrder(this, orderArrayList);
        viewOrder.setAdapter(adapterOrder);
    }

    public void showOrderLineListView(Order order){

        int id = order.getId();

        orderLineArrayList= dbHelper.retrieveOrderLine(id);
        AdapterOrderLineList adapterOrderLineList = new AdapterOrderLineList(this, orderLineArrayList);
        viewOrderLine.setAdapter(adapterOrderLineList);
    }
}
