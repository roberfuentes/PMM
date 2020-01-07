package com.example.examennoviembrenavidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class SelectVehicle extends AppCompatActivity
{

    Spinner mSpinner;
    Button mPrecio, mFactura;
    CheckBox mExtraAccesorios, mExtraGPS, mExtras;
    RadioGroup mRadioGroup;
    EditText mEntryDays;
    TextView mTxtPrice;

    private MedioTransporte[] currentTransport;
    int[] priceExtras = new int[3];
    boolean checkInsurance;
    int daysAlquiler;
    int priceAlquiler;
    int totalExtras;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_vehicle);

        //Load variables
        mPrecio = findViewById(R.id.btnPreviewPrice);
        mFactura = findViewById(R.id.btnFactura);
        mRadioGroup = findViewById(R.id.radioGroup);
        mEntryDays = findViewById(R.id.entryDays);
        mTxtPrice = findViewById(R.id.txtPreviewPrice);

        mExtraAccesorios = findViewById(R.id.extraAccesorios);
        mExtraGPS = findViewById(R.id.extraGPS);
        mExtras = findViewById(R.id.extras);

        final CheckBox[] checkboxes = {mExtraAccesorios, mExtraGPS, mExtras};
        checkInsurance = false;
        priceAlquiler = 0;


        currentTransport = (MedioTransporte[]) getIntent().getSerializableExtra("array");

        adaptSpinner();


        mPrecio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getAccesories(checkboxes);
                getInsurance(mRadioGroup);
                getDaysAlquiler(mEntryDays);
                getPriceAlquiler(mSpinner);
                setTextPrecio(mTxtPrice);

            }
        });

        mFactura.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                goToFactura(mSpinner, checkboxes);
            }
        });

    }


    private void showPrice()
    {

    }

    private void adaptSpinner()
    {
        mSpinner = findViewById(R.id.spinner);
        AdapterSpinner adapter = new AdapterSpinner(this, currentTransport);
        mSpinner.setAdapter(adapter);
    }

    private int calculatePrice()
    {
        int calculate = 0;
        //Dias x precio dia
        calculate = daysAlquiler * priceAlquiler;

        //Extras
        calculate += totalExtras;

        //Añade seguro
        if (checkInsurance)
        {
            calculate += (calculate * 0.2);
        }
        return  calculate;
    }

    private void getAccesories(CheckBox[] checkboxes)
    {
        for (int i = 0; i < checkboxes.length; i++)
        {
            if (checkboxes[i].isChecked())
            {
                priceExtras[i] = 50;
            } else
            {
                priceExtras[i] = 0;
            }
            Toast.makeText(this, priceExtras[0] + "" + priceExtras[1] + "" + priceExtras[2], Toast.LENGTH_SHORT).show();
        }
        totalExtras = 0;
        for (int i = 0; i < priceExtras.length; i++)
        {
            totalExtras += priceExtras[i];
        }
    }

    private void getInsurance(RadioGroup radio)
    {
        int radioId = radio.getCheckedRadioButtonId();

        if (radioId != -1)
        {
            RadioButton radioSelected = findViewById(radioId);
            if (radioSelected.getText().toString().equals("Seguro completo"))
            {
                checkInsurance = true;
            } else
            {
                checkInsurance = false;
            }
        }
    }

    private void getDaysAlquiler(EditText entryDays)
    {
        String days = entryDays.getText().toString();
        if (days.equals(""))
        {
            daysAlquiler = 1;
        } else
        {
            daysAlquiler = Integer.parseInt(days);
        }
    }

    private void getPriceAlquiler(Spinner spinner)
    {
        MedioTransporte transporte = getCurrentPositionSpinner(spinner);
        priceAlquiler = Integer.parseInt(transporte.getAlquiler());
    }




    private void goToFactura(Spinner spinner, CheckBox[] checkboxes)
    {
        getAccesories(checkboxes);
        getInsurance(mRadioGroup);
        getDaysAlquiler(mEntryDays);
        getPriceAlquiler(mSpinner);

        MedioTransporte transporte = getCurrentPositionSpinner(spinner);
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getApplicationContext(), Factura.class);
        int calculate = calculatePrice();

        bundle.putSerializable("VehicleDetails", transporte);
        bundle.putInt("Extras", totalExtras);
        bundle.putBoolean("Insurance", checkInsurance);
        bundle.putInt("Total", calculate);
        bundle.putInt("Dias", daysAlquiler);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    private MedioTransporte getCurrentPositionSpinner(Spinner spinner)
    {
        int index = spinner.getSelectedItemPosition();
        MedioTransporte transporte = currentTransport[index];
        return transporte;
    }

    public void setTextPrecio(TextView txtPrice)
    {

        int calculate = calculatePrice();
        txtPrice.setText("Subototal: " + calculate);
    }
}
