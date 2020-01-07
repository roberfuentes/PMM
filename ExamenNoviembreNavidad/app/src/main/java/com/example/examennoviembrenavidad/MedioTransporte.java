package com.example.examennoviembrenavidad;


import java.io.Serializable;

public class MedioTransporte implements Serializable
{

    String modelo, marca, alquiler;
    int foto;
    public MedioTransporte(String modelo, String marca, String alquiler, int foto){
        this.modelo = modelo;
        this.marca = marca;
        this.alquiler = alquiler;
        this.foto = foto;

    }

    public int getFoto() {
        return foto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(String alquiler) {
        this.alquiler = alquiler;
    }


    public String toString(){
        String message = "";
        message += "modelo:"+modelo+", marca:"+marca+", alquiler:"+alquiler;
        return message;
    }
}