package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    public static Zonas[] dataZonas = new Zonas[]{
            new Zonas("Asia y Oceania", 10, R.drawable.asia),
            new Zonas("America y África", 20, R.drawable.america),
            new Zonas("Europa", 10, R.drawable.europa),

    };

    Spinner mSpinnerZonas;
    ImageView mImageWorldwide;
    RadioGroup mRdGroup;
    CheckBox mCheckRegalo, mCheckTarjeta;
    EditText mTxtPeso;
    Button mBtnCalculo;
    RadioButton mTarifa;
    TextView continente, peso;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View to controller
        mSpinnerZonas = (Spinner) findViewById(R.id.spnZonas);
        mImageWorldwide = (ImageView) findViewById(R.id.imgWorldWide);
        mRdGroup = (RadioGroup) findViewById(R.id.radioGroupTarifa);
        mCheckRegalo = (CheckBox) findViewById(R.id.checkRegalo);
        mCheckTarjeta = (CheckBox) findViewById(R.id.checkTarjeta);
        mTxtPeso = (EditText) findViewById(R.id.entryPeso);
        mBtnCalculo = (Button) findViewById(R.id.btnCalculo);


        final TextView mContinente = (TextView) findViewById(R.id.txtContinente);
        TextView mPeso = (TextView) findViewById(R.id.txtPeso);

        //Spinner
        AdapterEnvio adapterEnvio = new AdapterEnvio(this);
        mSpinnerZonas.setAdapter(adapterEnvio);

        //Set Image
        mImageWorldwide.setImageResource(R.drawable.shipworldwide);


        mBtnCalculo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Intent and Bundle
                Intent intento = new Intent(MainActivity.this, Envio.class);
                Bundle bundleZona = new Bundle();
                int radioId = -1;
                String tarifa;

                //Get IDS
                if(mRdGroup.getCheckedRadioButtonId() == -1){

                }else{
                 radioId = mRdGroup.getCheckedRadioButtonId();
                 mTarifa = (RadioButton) findViewById(radioId);
                 tarifa = mTarifa.getText().toString();
                 bundleZona.putString("tarifa", tarifa);

                }

                int indexDataZonas = mSpinnerZonas.getSelectedItemPosition();
                String peso;
                String checkRegalo = "";
                String checkTarjeta = "";
                if(mCheckRegalo.isChecked()){
                    checkRegalo = mCheckRegalo.getText().toString();
                }
                if(mCheckTarjeta.isChecked()){
                    checkTarjeta = mCheckTarjeta.getText().toString();
                }

                if(mTxtPeso.getText().toString().equals("")){
                   peso = "0";
                }else{
                    peso = mTxtPeso.getText().toString();
                }


                //Create object and assign the radio selected to the radiobutton
                Zonas zona = dataZonas[indexDataZonas];


                //Get Strings


                //Put String
                bundleZona.putSerializable("zona", zona);
                bundleZona.putString("peso", peso);

                if(checkTarjeta!=""){
                    bundleZona.putString("tarjeta", checkTarjeta);
                }
                if(checkRegalo!=""){
                    bundleZona.putString("regalo", checkRegalo);
                }


                intento.putExtras(bundleZona);
                startActivity(intento);


            }
        });


    }
}
