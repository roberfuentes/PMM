package com.example.finalprojectpmm.Models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Products implements Serializable
{

    int id;
    String name;
    String description;
    int unitPrice;
    Bitmap bitImg;
    int imgNotProcessed;



    public Products(int id, String name, int unitPrice, Bitmap bitImg)
    {
        this.id = id;
        this.name = name;

        this.unitPrice = unitPrice;
        this.bitImg = bitImg;
    }
    public Products(String name, int unitPrice, int imgNotProcessed)
    {
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.imgNotProcessed = imgNotProcessed;
    }



    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public int getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice)
    {
        this.unitPrice = unitPrice;
    }
    public Bitmap getbitImg()
    {
        return bitImg;
    }

    public void setbitImg(Bitmap bitImg)
    {
        this.bitImg = bitImg;
    }

    public int getimgNotProcessed()
    {
        return imgNotProcessed;
    }

    public void setimgNotProcessed(int imgNotProcessed)
    {
        this.imgNotProcessed = imgNotProcessed;
    }
}
