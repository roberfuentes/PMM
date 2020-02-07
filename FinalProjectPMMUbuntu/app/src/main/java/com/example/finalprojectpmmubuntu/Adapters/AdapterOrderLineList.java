package com.example.finalprojectpmmubuntu.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalprojectpmmubuntu.Models.OrderLine;
import com.example.finalprojectpmmubuntu.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterOrderLineList extends ArrayAdapter<OrderLine>
{

    public AdapterOrderLineList(@NonNull Context context, @NonNull ArrayList<OrderLine> orderLines)
    {
        super(context, 0, orderLines);

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    public View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){


        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layour_orderline_list, parent, false);

        TextView lviewId = (TextView)convertView.findViewById(R.id.lviewId);
        TextView lviewPrice = (TextView)convertView.findViewById(R.id.lviewPrice);
        TextView lviewQuantity= (TextView)convertView.findViewById(R.id.lviewQuantity);
        TextView lviewInsurance = (TextView)convertView.findViewById(R.id.lviewInsurance);
        TextView lviewHeadphones = (TextView)convertView.findViewById(R.id.lviewHeadphones);
        ImageView lviewPhone = (ImageView)convertView.findViewById(R.id.lviewImage);
        TextView lviewOrderId = (TextView)convertView.findViewById(R.id.lviewOrderId);


        OrderLine orderLine = getItem(position);
        Boolean insurance = orderLine.getInsurance();
        Boolean headphones = orderLine.getHeadphones();

        lviewId.setText("ID:"+Integer.toString(orderLine.getId()));
        lviewPrice.setText("Price: "+orderLine.getPrice());
        lviewQuantity.setText("Quantity: "+orderLine.getQuantity());
        lviewOrderId.setText("Order ID:" + orderLine.getorderID());

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


        lviewPhone.setImageBitmap(orderLine.getBitImg());
        lviewPhone.getLayoutParams().height = 150;
        lviewPhone.getLayoutParams().width= 200;

        return convertView;
    }
}
