package com.example.twolistwithobjects;

import java.io.Serializable;

public class Courses implements Serializable
{

    private String curso;


    Courses(String curso){
        this.curso = curso;
    }

    public String getCurso()
    {
        return curso;
    }

    public void setCurso(String curso)
    {
        this.curso = curso;
    }
}
