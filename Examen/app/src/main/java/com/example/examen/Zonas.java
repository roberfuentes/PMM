package com.example.examen;


import java.io.Serializable;

public class Zonas implements Serializable
{
    String continente;
    int peso;
    int foto;

    public Zonas(String continente, int peso, int foto)
    {
        this.continente = continente;
        this.peso = peso;
        this.foto = foto;
    }

    public int getFoto()
    {
        return foto;
    }

    public String getContinente()
    {
        return continente;
    }

    public void setContinente(String continente)
    {
        this.continente = continente;
    }

    public int getPeso()
    {
        return peso;
    }

    public void setPeso(int peso)
    {
        this.peso = peso;
    }

    public String toString()
    {
        String frase = "";
        frase += "Continente" + continente + " Peso:" + peso;
        return frase;
    }
}
