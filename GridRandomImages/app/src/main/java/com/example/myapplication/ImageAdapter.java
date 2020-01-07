package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImageAdapter extends BaseAdapter
{
    Context mContext;
    public static int[] array = {
            R.drawable.img1, R.drawable.img2,R.drawable.img3,R.drawable.img4,
            R.drawable.img5,R.drawable.img6
    };


    public ImageAdapter(Context context){
        this.mContext = context;
    }

    public int getCount(){
        return array.length;
    }

    public Object getItem(int position){
        return array;
    }

    public long getItemId(int position){
        return 0;
    }



    public View getView(int position, View convertView, ViewGroup parent){
        if(position == 0){
            array = RandomUtils.RandomizeArray(array);
            /*List<Integer> listArray = Arrays.asList(array);
            Collections.shuffle(listArray);
            listArray.toArray(array);*/
        }

        //boolean whileFlag = false;
        //boolean flag = false;

        /*int[] secondArray = {0,0,0,0,0,0};
        while(!whileFlag){
            for(int i = 0; i < secondArray.length; i++){
                if(secondArray[i] == array[position]){
                    flag = true;
                }
                if(!flag){
                    secondArray[i] = array[position];
                    whileFlag = true;
                }else{
                    array = RandomUtils.RandomizeArray(array);
                }
            }


        }*/

        ImageView imgView = new ImageView(mContext);
        imgView.setImageResource(array[position]);

        imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imgView.setLayoutParams(new GridView.LayoutParams(340, 350));

        System.out.println("la posicion exacta" + array[position]);
        return imgView;
    }



}