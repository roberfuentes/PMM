package com.example.finalprojectpmm.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.finalprojectpmm.DatabaseHelper.DBHelper;
import com.example.finalprojectpmm.Fragments.FragmentBuy;
import com.example.finalprojectpmm.Models.OrderLine;
import com.example.finalprojectpmm.Models.Products;
import com.example.finalprojectpmm.R;

import java.util.ArrayList;


public class BuyActivity extends AppCompatActivity
{

    int phones[][] = {{}, {R.drawable.xiaomi_redmi_note_7, 150}, {R.drawable.xiaomi_redmi_note_8, 200}, {R.drawable.xiaomi_mi_9_lite, 250}, {R.drawable.xiaomi_mi_9t, 300}};
    ArrayList<OrderLine> orderLines;
    int index, id;

    Button mBtnLeft, mBtnRight, mBtnAdd, mBtnOrder;
    CheckBox mCboxInsurance, mCboxHeadphones;

    DBHelper dbHelper;


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
        dbHelper = new DBHelper(this);

        if (savedInstanceState == null)
        {
            if (checkProductValues())
            {
                System.out.println("es true");
                insertProductValues();
            }
            System.out.println("es true");

            index = 1;
            addFragment();
        }



        id = getIntent().getExtras().getInt("CustomerID");
        System.out.println("U catching the customer right?" + id);



        mBtnLeft.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (index != 1)
                {
                    index--;
                } else
                {
                    index = 4;
                }
                replaceFragment();
            }
        });
        mBtnRight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (index != 4)
                {
                    index++;
                } else
                {
                    index = 1;
                }
                replaceFragment();
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
                OrderLine orderline = new OrderLine(price, quantity, insurance, headphones, image, index);
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
                bundle.putInt("CustomerID", id);
                System.out.println("Size before:"+orderLines.size());
                Intent intent = new Intent(BuyActivity.this, PayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });







    }

    public void addFragment()
    {
        Bundle bundle = new Bundle();
        Products product = dbHelper.retrieveProduct(index);
        bundle.putSerializable("product", product);
        System.out.println("This should be getting the product ADD:" + product.getName());

        String phoneName = product.getName();
        int phonePrice = product.getUnitPrice();
        Bitmap phoneImage = product.getbitImg();

        Fragment fragment = FragmentBuy.newInstance(phoneName, phonePrice, phoneImage);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.add(R.id.fragmentPhone, fragment);
        ft.commit();


    }
    public void replaceFragment()
    {

        Bundle bundle = new Bundle();
        Products product = dbHelper.retrieveProduct(index);
        bundle.putSerializable("product", product);

        String phoneName = product.getName();
        int phonePrice = product.getUnitPrice();
        Bitmap phoneImage = product.getbitImg();

        Fragment fragment = FragmentBuy.newInstance(phoneName, phonePrice, phoneImage);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.fragmentPhone, fragment);
        ft.commit();
    }

    private void insertProductValues()
    {
        dbHelper.insertProducts();
    }

    public boolean checkProductValues()
    {
        if (dbHelper.checkProducts())
        {
            return true;
        } else
        {
            return false;
        }
    }


    public Bitmap imageToBitmap(int image){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(image);
        BitmapDrawable bmDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bmDrawable.getBitmap();
        return bitmap;
    }

}