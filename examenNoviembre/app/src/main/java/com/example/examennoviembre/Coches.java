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

public class Coches extends AppCompatActivity {


    Spinner mSpinnerCoches;
    RadioGroup mRadioGroupSecure;
    CheckBox mCheckBoxProtection, mCheckBoxGPS, mCheckBoxExtras;
    Button mButtonFactura, mButtonTotalPrecio;
    EditText mEntryDias;
    TextView mTxtPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coches);

        //SPINNER
        mSpinnerCoches = (Spinner)findViewById(R.id.spnCoches);
        AdapterCoches adapterCoches = new AdapterCoches(this);
        mSpinnerCoches.setAdapter(adapterCoches);


        //Passing to controller
        mButtonFactura = (Button)findViewById(R.id.buttonFactura);
        mButtonTotalPrecio = (Button)findViewById(R.id.buttonTotalPrice);
        mCheckBoxProtection = (CheckBox)findViewById(R.id.checkBoxProtection);
        mCheckBoxGPS = (CheckBox)findViewById(R.id.checkboxGPS);
        mCheckBoxExtras= (CheckBox)findViewById(R.id.checkBoxExtras);
        mRadioGroupSecure = (RadioGroup)findViewById(R.id.radioGroupSecure);
        mEntryDias = (EditText)findViewById(R.id.entryDias);
        mTxtPrice = (TextView)findViewById(R.id.txtPrice);








        mSpinnerCoches.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("soy gilipollas" + id + " pero mas aun " + mSpinnerCoches.getSelectedItemPosition());
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
                int indexData = mSpinnerCoches.getSelectedItemPosition();
                MedioTransporte coches = MainActivity.coches[indexData];




                double porcentaje = 0;
                int extras = 0;
                int dias = 1;
                int alquiler = Integer.parseInt(coches.getAlquiler());

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
                Intent intento = new Intent(Coches.this, activity_factura.class);
                Bundle myBundle = new Bundle();


                //Getting the IDS
                int indexData = mSpinnerCoches.getSelectedItemPosition();
                int radio = mRadioGroupSecure.getCheckedRadioButtonId();
                System.out.println("radio:"+radio);

                //Applying the IDS to get the values
                RadioButton radioSelection = (RadioButton)findViewById(radio);
                MedioTransporte coche = MainActivity.coches[indexData];


                //putBundles

                //RadioButton
                if(radio != -1){
                    if(radioSelection.getText().toString().equals("Seguro Completo")){
                        myBundle.putInt("Porcentaje", 20);
                    }
                }

                //Object
                myBundle.putSerializable("coche", coche);

                //CheckBoxes
                if(mCheckBoxExtras.isChecked()){
                    myBundle.putInt("Extras", 50);
                }
                if(mCheckBoxProtection.isChecked()){
                    myBundle.putInt("Proteccion", 50);
                }
                if(mCheckBoxGPS.isChecked()){
                    myBundle.putInt("GPS", 50);
                }

                if(mEntryDias.getText().toString().equals("")){
                    myBundle.putInt("duas", 1);
                }else{
                    int dias = Integer.parseInt(mEntryDias.getText().toString());
                    myBundle.putInt("dias", dias);
                }



                startActivity(intento);




            }
        });


    }
}
