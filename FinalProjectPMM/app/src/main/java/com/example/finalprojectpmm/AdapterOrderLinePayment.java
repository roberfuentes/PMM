package com.example.finalprojectpmm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterOrderLinePayment extends ArrayAdapter<OrderLine>
{


    ArrayList<OrderLine> orderlines;

    public AdapterOrderLinePayment(@NonNull Context context, int resource, @NonNull ArrayList<OrderLine> orderLines)
    {
        super(context, 0, orderLines);
        this.orderlines = orderLines;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    public View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout_orderline_pay, parent, false);

        TextView lviewPrice = (TextView)convertView.findViewById(R.id.lviewPrice);
        TextView lviewQuantity= (TextView)convertView.findViewById(R.id.lviewQuantity);
        TextView lviewInsurance = (TextView)convertView.findViewById(R.id.lviewInsurance);
        TextView lviewHeadphones = (TextView)convertView.findViewById(R.id.lviewHeadphones);
        ImageView lviewPhone = (ImageView)convertView.findViewById(R.id.lviewImage);


        OrderLine orderLine = getItem(position);
        Boolean insurance = orderLine.getInsurance();
        Boolean headphones = orderLine.getHeadphones();

        lviewPrice.setText("Price: "+orderLine.getPrice());
        lviewQuantity.setText("Quantity: "+orderLine.getQuantity());

        if(insurance){
            lviewInsurance.setText("Insurance: Yes");
        }else{
            lviewInsurance.setText("Insurance: No");
        }

        if(headphones){
            lviewHeadphones.setText("Headphones: Yes");
        }else{
            lviewHeadphones.setText("Headphones: No");
        }

        lviewPhone.setImageResource(orderLine.getImg());
        lviewPhone.getLayoutParams().height = 150;
        lviewPhone.getLayoutParams().width= 200;

        return convertView;
    }
}
