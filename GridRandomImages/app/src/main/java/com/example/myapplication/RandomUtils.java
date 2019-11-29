package com.example.myapplication;

import java.util.Random;

public class RandomUtils {

    private static Random r = new Random();

    public static int randomInt(int range){
        return(r.nextInt(range));
    }

    public static int randomIndex(Object[] array){
        return(randomInt(array.length));
    }

    public static <T> T randomElement(T[] array){
        return(array[randomIndex(array)]);
    }

    public static float randomFloat(int n){
        return((float)Math.random()*n);
    }
    public static int[] RandomizeArray(int[] array){
        Random ran = new Random();  // Random number generator
        boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = ran.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
        for(int i = 0;i<array.length;i++){
            System.out.println("El array" + array[i]);
        }
        return array;
    }

}