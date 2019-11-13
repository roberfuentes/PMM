package com.example.examentest;

import java.io.Serializable;

public class Pizza implements Serializable
{
    String ingrediente1, ingrediente2, tamano;
    int foto;

    public Pizza(String ingrediente1, String ingrediente2, String tamano, int foto){
        this.ingrediente1 = ingrediente1;
        this.ingrediente2 = ingrediente2;
        this.tamano = tamano;
        this.foto = foto;
    }

    public int getFoto()
    {
        return foto;
    }

    public String getIngrediente1()
    {
        return ingrediente1;
    }

    public void setIngrediente1(String ingrediente1)
    {
        this.ingrediente1 = ingrediente1;
    }

    public String getIngrediente2()
    {
        return ingrediente2;
    }

    public void setIngrediente2(String ingrediente2)
    {
        this.ingrediente2 = ingrediente2;
    }

    public String getTamano()
    {
        return tamano;
    }


    public void setTamano(String tamano)
    {
        this.tamano = tamano;
    }
}
