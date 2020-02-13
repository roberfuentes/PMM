package com.example.finalprojectpmm.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalprojectpmm.DatabaseHelper.DBHelper;
import com.example.finalprojectpmm.Models.Products;
import com.example.finalprojectpmm.R;

import java.io.ByteArrayOutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBuy extends Fragment
{
    private static final String ARG_PRODUCT_NAME = "name";
    private static final String ARG_PRODUCT_PRICE = "price";
    private static final String ARG_PRODUCT_IMAGE = "image";



    String phoneName;
    int phonePrice;
    Bitmap phoneImage;

    public FragmentBuy()
    {

    }

    public static FragmentBuy newInstance(String phoneName, int phonePrice, Bitmap phoneImage){
        FragmentBuy fragmentBuy = new FragmentBuy();
        Bundle args = new Bundle();
        args.putString(ARG_PRODUCT_NAME, phoneName);
        args.putInt(ARG_PRODUCT_PRICE, phonePrice);

        ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
        phoneImage.compress(Bitmap.CompressFormat.PNG, 100, imageStream);
        byte[] byteImage = imageStream.toByteArray();

        args.putByteArray(ARG_PRODUCT_IMAGE, byteImage);
        fragmentBuy.setArguments(args);
        return fragmentBuy;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            phoneName = getArguments().getString(ARG_PRODUCT_NAME);
            phonePrice = getArguments().getInt(ARG_PRODUCT_PRICE);
            byte[] tempByte= getArguments().getByteArray(ARG_PRODUCT_IMAGE);

            //decode the byte and add to local Bitmap
            phoneImage = BitmapFactory.decodeByteArray(tempByte, 0, tempByte.length);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        View v = inflater.inflate(R.layout.fragment_buy, container, false);


        ImageView image = (ImageView)v.findViewById(R.id.phoneSelection);
        TextView name = (TextView)v.findViewById(R.id.phoneName);
        TextView price = (TextView)v.findViewById(R.id.phonePrice);

        String strPrice = Integer.toString(phonePrice);

        image.setImageBitmap(phoneImage);
        image.getLayoutParams().height = 400;
        image.getLayoutParams().width = 600;
        name.setText("Name: " + phoneName);
        price.setText("Price: " + strPrice);

        return v;
    }
}
