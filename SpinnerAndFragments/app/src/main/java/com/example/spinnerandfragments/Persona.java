package com.example.spinnerandfragments;

import java.io.Serializable;

public class Persona implements Serializable
{

    private String nombre;
    private String apellidos;
    private String edad;
    private String estudios;
    private String trabajos;
    private int imagen;

    public Persona(String nombre, String apellidos, String edad, String estudios, String trabajos, int imagen){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.estudios = estudios;
        this.trabajos= trabajos;
        this.imagen = imagen;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellidos()
    {
        return apellidos;
    }

    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }

    public String getEdad()
    {
        return edad;
    }

    public void setEdad(String edad)
    {
        this.edad = edad;
    }

    public String getEstudios()
    {
        return estudios;
    }

    public void setEstudios(String estudios)
    {
        this.estudios = estudios;
    }

    public String getTrabajos()
    {
        return trabajos;
    }

    public void setTrabajos(String trabajos)
    {
        this.trabajos = trabajos;
    }

    public int getImagen()
    {
        return imagen;
    }

    public String toString(){
        return "Pues la vida de esta persona es que ha estudiado " + estudios + " y encima trabaja en " + trabajos;
    }
}
