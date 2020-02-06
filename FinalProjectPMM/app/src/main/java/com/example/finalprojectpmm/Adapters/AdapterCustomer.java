package com.example.finalprojectpmm.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.finalprojectpmm.Models.Customer;
import com.example.finalprojectpmm.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterCustomer extends ArrayAdapter<Customer>
{


    public AdapterCustomer(@NonNull Context context, @NonNull List<Customer> objects)
    {
        super(context, 0, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }


    public View initView(int position, View convertView, ViewGroup parent){

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout_customer, parent, false);

        Customer customer = getItem(position);

        TextView id = (TextView)convertView.findViewById(R.id.viewCustomerId);
        TextView name = (TextView)convertView.findViewById(R.id.viewCustomerName);

        id.setText(Integer.toString(customer.getId()));
        name.setText(customer.getName());

        return convertView;
    }
}
