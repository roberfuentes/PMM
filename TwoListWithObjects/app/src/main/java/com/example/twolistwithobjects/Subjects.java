package com.example.twolistwithobjects;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Subjects implements Serializable
{

    private String subject;

    Subjects(String subject){
        this.subject = subject;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    @NonNull
    @Override
    public String toString()
    {
        return "este es el objeto = " + subject;
    }
}
