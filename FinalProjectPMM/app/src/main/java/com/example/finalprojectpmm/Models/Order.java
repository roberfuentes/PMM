package com.example.finalprojectpmm.Models;

import java.io.Serializable;

public class Order implements Serializable
{
    int id;
    int custID;
    String cost;


    public Order(int id, int custID, String cost)
    {
        this.id = id;
        this.custID = custID;
        this.cost = cost;
    }


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCustID()
    {
        return custID;
    }

    public void setCustID(int custID)
    {
        this.custID = custID;
    }

    public String getCost()
    {
        return cost;
    }

    public void setCost(String cost)
    {
        this.cost = cost;
    }
}
