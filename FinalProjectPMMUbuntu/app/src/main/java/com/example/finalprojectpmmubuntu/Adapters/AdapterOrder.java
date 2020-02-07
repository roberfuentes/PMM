package com.example.finalprojectpmmubuntu.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.finalprojectpmmubuntu.Models.Order;
import com.example.finalprojectpmmubuntu.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterOrder extends ArrayAdapter<Order>
{



    public AdapterOrder(@NonNull Context context, @NonNull ArrayList<Order> orders)
    {
        super(context, 0, orders);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }


    public View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){


        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout_order , parent, false);

        Order order = getItem(position);

        TextView id = (TextView)convertView.findViewById(R.id.viewOrderId);
        TextView customerID = (TextView)convertView.findViewById(R.id.viewOrderCustomerId);
        TextView cost = (TextView)convertView.findViewById(R.id.viewOrderCost);


        id.setText("Id:"+Integer.toString(order.getId()));
        customerID.setText("Customer ID:"+Integer.toString(order.getCustID()));
        cost.setText("Cost:"+order.getCost());


        return convertView;



    }
}
