package com.example.fragmentosdiversos;

import java.util.Random;

public class RandomUtils
{
    public static Random r = new Random();

    public static int randomInt(int range){
        return(r.nextInt(range));
    }
}
