package com.example.finalprojectpmm.Models;


import android.graphics.Bitmap;

import java.io.Serializable;

public class OrderLine implements Serializable
{

    int id, orderID;
    int productID;
    String price, quantity;
    Boolean insurance;
    int img;
    Bitmap bitImg;




    //Retrieve data
    public OrderLine(int id, String price, String quantity, Boolean insurance, Bitmap bitImg, int orderID)
    {

        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.insurance = insurance;
        this.bitImg = bitImg;
        this.orderID = orderID;
    }



    public OrderLine(String price, String quantity, Boolean insurance, Bitmap bitImg)
    {
        this.price = price;
        this.quantity = quantity;
        this.insurance = insurance;
        this.bitImg = bitImg;
    }


    //Buy to pay
    public OrderLine(String price, String quantity, Boolean insurance, int img, int productID)
    {
        this.price = price;
        this.quantity = quantity;
        this.insurance = insurance;
        this.img = img;
        this.productID = productID;
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

    public int getProductID()
    {
        return productID;
    }
    public void setProductID(int productID)
    {
        this.productID = productID;
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
