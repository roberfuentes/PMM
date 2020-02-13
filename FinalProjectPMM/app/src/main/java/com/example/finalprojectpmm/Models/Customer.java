package com.example.finalprojectpmm.Models;

import java.io.Serializable;

public class Customer implements Serializable
{
    int id;

    String Username;
    String password;



    public Customer(int id, String Username, String password)
    {
        this.id = id;
        this.Username = Username;
        this.password = password;
    }
    public Customer(int id, String Username)
    {
        this.id = id;
        this.Username = Username;
    }
    public Customer(String Username)
    {
        this.Username = Username;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return Username;
    }

    public void setUsername(String Username)
    {
        this.Username = Username;
    }
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
