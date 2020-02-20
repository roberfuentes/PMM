package com.example.finalprojectpmm.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalprojectpmm.Models.OrderLine;
import com.example.finalprojectpmm.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterOrderLinePay extends ArrayAdapter<OrderLine>
{




    public AdapterOrderLinePay(@NonNull Context context, @NonNull ArrayList<OrderLine> orderLines)
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


        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout_orderline, parent, false);

        TextView lviewPrice = (TextView)convertView.findViewById(R.id.lviewPrice);
        TextView lviewQuantity= (TextView)convertView.findViewById(R.id.lviewQuantity);
        TextView lviewInsurance = (TextView)convertView.findViewById(R.id.lviewInsurance);
        TextView lviewHeadphones = (TextView)convertView.findViewById(R.id.lviewHeadphones);
        ImageView lviewPhone = (ImageView)convertView.findViewById(R.id.lviewImage);



        OrderLine orderLine = getItem(position);
        Boolean insurance = orderLine.getInsurance();

        lviewPrice.setText("Price: "+orderLine.getPrice());
        lviewQuantity.setText("Quantity: "+orderLine.getQuantity());

        if(insurance){
            lviewInsurance.setText("Insurance: Yes");
        }else{
            lviewInsurance.setText("Insurance: No");
        }



        lviewPhone.setImageResource(orderLine.getImg());
        lviewPhone.getLayoutParams().height = 150;
        lviewPhone.getLayoutParams().width= 200;

        return convertView;
    }
}
