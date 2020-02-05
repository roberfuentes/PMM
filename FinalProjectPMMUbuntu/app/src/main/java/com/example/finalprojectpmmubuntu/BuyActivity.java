package com.example.finalprojectpmmubuntu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;


public class BuyActivity extends AppCompatActivity
{

    int phones[][] = {{R.drawable.xiaomi_redmi_note_7, 150}, {R.drawable.xiaomi_redmi_note_8, 200}, {R.drawable.xiaomi_mi_9_lite, 250}, {R.drawable.xiaomi_mi_9t, 300}};
    ArrayList<OrderLine> orderLines;
    int index=0;

    Button mBtnLeft, mBtnRight, mBtnAdd, mBtnOrder;
    CheckBox mCboxInsurance, mCboxHeadphones;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        mBtnLeft = (Button)findViewById(R.id.btnLeft);
        mBtnRight = (Button)findViewById(R.id.btnRight);
        mBtnAdd= (Button)findViewById(R.id.btnAddOrderLine);
        mBtnOrder = (Button)findViewById(R.id.btnOrder);
        mCboxInsurance = (CheckBox)findViewById(R.id.cboxInsurance);
        mCboxHeadphones = (CheckBox)findViewById(R.id.cboxHeadphones);
        orderLines = new ArrayList<>();
        if(savedInstanceState==null){
            addFragment();
        }


        mBtnLeft.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                replaceFragment(0);
            }
        });
        mBtnRight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                replaceFragment(1);
            }
        });


        mBtnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String price = Integer.toString(phones[index][1]);
                String quantity = "1";
                Boolean insurance = false;
                Boolean headphones = false;

                if(mCboxInsurance.isChecked()){
                    insurance = true;

                }
                if(mCboxHeadphones.isChecked()){
                    headphones = true;

                }
                int image = phones[index][0];

                Toast.makeText(BuyActivity.this, "Index" + index + ", Precio: "+ price + " and size:" + orderLines.size(), Toast.LENGTH_SHORT).show();
                OrderLine orderline = new OrderLine(price, quantity, insurance, headphones, image);
                orderLines.add(orderline);

            }
        });

        mBtnOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putSerializable("orderlines", orderLines);
                System.out.println("Size before:"+orderLines.size());
                Intent intent = new Intent(BuyActivity.this, PayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });







    }

    public void addFragment(){
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);

        Fragment fragment = new FragmentBuy();
        fragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragmentPhone, fragment);
        ft.commit();
    }
    public void replaceFragment(int direction){
        if(direction==0){
            if(index!=0){
                index--;
            }else{
                index=3;
            }
        }else{
            if(index!=3){
                index++;
            }else{
                index=0;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);

        Fragment fragment = new FragmentBuy();
        fragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentPhone, fragment);
        ft.commit();
    }
}