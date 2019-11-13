package com.example.examennoviembre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class Electrico extends AppCompatActivity {


    //Variables

    Spinner mSpnElectrico;
    RadioGroup mRadioGroupSecure;
    CheckBox mCheckBoxProtection, mCheckBoxGPS, mCheckBoxExtras;
    Button mButtonFactura, mButtonTotalPrecio;
    EditText mEntryDias;
    TextView mTxtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrico);


        //SPINNER
        mSpnElectrico = (Spinner)findViewById(R.id.spnElectrico);
        AdapterElectricos adapterElectricos = new AdapterElectricos(this);
        mSpnElectrico.setAdapter(adapterElectricos);

        //Passing to controller
        mButtonFactura = (Button)findViewById(R.id.buttonFactura);
        mButtonTotalPrecio = (Button)findViewById(R.id.buttonTotalPrice);
        mCheckBoxProtection = (CheckBox)findViewById(R.id.checkBoxProtection);
        mCheckBoxGPS = (CheckBox)findViewById(R.id.checkboxGPS);
        mCheckBoxExtras= (CheckBox)findViewById(R.id.checkBoxExtras);
        mRadioGroupSecure = (RadioGroup)findViewById(R.id.radioGroupSecure);
        mEntryDias = (EditText)findViewById(R.id.entryDias);
        mTxtPrice = (TextView)findViewById(R.id.txtPrice);
        System.out.println("Test Id:" + mSpnElectrico.getSelectedItemId() + "Position:"+ mSpnElectrico.getSelectedItemPosition() + "" + mSpnElectrico.getSelectedItem() + "" );







        mSpnElectrico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("soy gilipollas" + id + " pero mas aun " + mSpnElectrico.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mButtonTotalPrecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("works?");

                int radio = mRadioGroupSecure.getCheckedRadioButtonId();
                RadioButton radioSelection = (RadioButton)findViewById(radio);
                int indexData = mSpnElectrico.getSelectedItemPosition();
                MedioTransporte electrico = MainActivity.electricos[indexData];




                double porcentaje = 0;
                int extras = 0;
                int dias = 1;
                int alquiler = Integer.parseInt(electrico.getAlquiler());

                if(radioSelection.getText().toString().equals("Seguro Completo")){
                    porcentaje = 0.2;
                }

                //CheckBoxes
                if(mCheckBoxExtras.isChecked()){
                    extras += 50;
                }
                if(mCheckBoxProtection.isChecked()){
                    extras += 50;
                }
                if(mCheckBoxGPS.isChecked()){
                    extras += 50;
                }

                if(mEntryDias.getText().toString().equals("")){
                    dias = 1;
                }else{
                    dias = Integer.parseInt(mEntryDias.getText().toString());
                }



                int beforePorcentaje = (alquiler*dias) + extras;
                double afterPorcentaje = beforePorcentaje + (beforePorcentaje*porcentaje);
                mTxtPrice.setText(String.valueOf(afterPorcentaje));
            }
        });


        mButtonFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Variables
                Intent intento = new Intent(Electrico.this, activity_factura.class);
                Bundle myBundle = new Bundle();
                String extras = "";

                int dias = 1;
                double porcentaje = 0;

                //Getting the IDS
                int indexData = mSpnElectrico.getSelectedItemPosition();
                int radio = mRadioGroupSecure.getCheckedRadioButtonId();
                MedioTransporte electrico = MainActivity.electricos[indexData];
                int alquiler = Integer.parseInt(electrico.getAlquiler());

                System.out.println("radio:"+radio);

                //Applying the IDS to get the values
                RadioButton radioSelection = (RadioButton)findViewById(radio);



                //putBundles

                //RadioButton
                if(radio != -1){
                    if(radioSelection.getText().toString().equals("Seguro Completo")){
                        myBundle.putInt("Porcentaje", 20);
                        porcentaje = 0.2;
                    }
                }


                //Object
                myBundle.putSerializable("Electrico", electrico);

                //CheckBoxes
                if(mCheckBoxExtras.isChecked()){
                    extras += 50;
                }
                if(mCheckBoxProtection.isChecked()){
                    extras += 50;
                }
                if(mCheckBoxGPS.isChecked()){
                    extras += 50;
                }
                myBundle.putString("Extras", extras);

                if(mEntryDias.getText().toString().equals("")){
                    myBundle.putInt("Dias", 1);
                }else{
                    dias = Integer.parseInt(mEntryDias.getText().toString());
                    myBundle.putInt("Dias", dias);
                }

                int beforePorcentaje = (alquiler*dias) + Integer.parseInt(extras);
                double afterPorcentaje = beforePorcentaje + (beforePorcentaje*porcentaje);
                mTxtPrice.setText(String.valueOf(afterPorcentaje));

                myBundle.putString("Price", String.valueOf(afterPorcentaje));


                intento.putExtras(myBundle);
                startActivity(intento);

            }
        });

    }
}
