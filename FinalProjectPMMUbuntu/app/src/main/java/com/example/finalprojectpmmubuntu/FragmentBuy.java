package com.example.finalprojectpmmubuntu;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBuy extends Fragment
{
    int index;

    public FragmentBuy()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            index = getArguments().getInt("index");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        int phones[][] = {{R.drawable.xiaomi_redmi_note_7, 150}, {R.drawable.xiaomi_redmi_note_8, 200}, {R.drawable.xiaomi_mi_9_lite, 250}, {R.drawable.xiaomi_mi_9t, 300}};
        View v = inflater.inflate(R.layout.fragment_buy, container, false);

        ImageView image = (ImageView)v.findViewById(R.id.phoneSelection);
        TextView price = (TextView)v.findViewById(R.id.phonePrice);

        image.setImageResource(phones[index][0]);
        image.getLayoutParams().height = 400;
        image.getLayoutParams().width = 600;
        String priceString = Integer.toString(phones[index][1]);
        price.setText("Price: "+ priceString + "€");

        return v;
    }

}