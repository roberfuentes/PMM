package com.example.finalprojectpmmubuntu.Models;


import android.graphics.Bitmap;

import java.io.Serializable;

public class OrderLine implements Serializable
{

    int id;
    int orderID;
    String price, quantity;
    Boolean insurance, headphones;
    int img;
    Bitmap bitImg;


    public OrderLine(String price, String quantity, Boolean insurance, int img, int orderID)
    {
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.insurance = insurance;
        this.img = img;
    }

    //Retrieve data
    public OrderLine(int id, String price, String quantity, Boolean insurance, Boolean headphones, Bitmap bitImg, int orderID)
    {

        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.insurance = insurance;
        this.headphones = headphones;
        this.bitImg = bitImg;
        this.orderID = orderID;
    }


    //Buy to pay
    public OrderLine(String price, String quantity, Boolean insurance, Boolean headphones, int img)
    {
        this.price = price;
        this.quantity = quantity;
        this.insurance = insurance;
        this.img = img;
        this.headphones = headphones;
    }

    public Boolean getHeadphones()
    {
        return headphones;
    }

    public void setHeadphones(Boolean headphones)
    {
        this.headphones = headphones;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getorderID()
    {
        return orderID;
    }

    public void setorderID(int orderID)
    {
        this.orderID = orderID;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getQuantity()
    {
        return quantity;
    }

    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }

    public Boolean getInsurance()
    {
        return insurance;
    }

    public void setInsurance(Boolean insurance)
    {
        this.insurance = insurance;
    }

    public int getImg()
    {
        return img;
    }

    public void setImg(int img)
    {
        this.img = img;
    }


    public Bitmap getBitImg()
    {
        return bitImg;
    }

    public void setBitImg(Bitmap bitImg)
    {
        this.bitImg = bitImg;
    }
}
