package com.example.firstdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private static final String DATABASE_NAME = "DBC";
    private static final int DATABASE_VERSION= 3;
    private static final String DATABASE_TABLE_CLIENTES= "Clientes";
    private static final String CODIGO = "codigo";
    private static final String NOMBRE = "nombre";
    private static final String PHONE = "phone";





    Spinner spn;
    final static String[] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        spn = (Spinner)findViewById(R.id.spinner);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, );
        //spn.setAdapter(adapter);
        DBHelper dbHelper = new DBHelper(this, DATABASE_NAME, null, DATABASE_VERSION);


        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Toast.makeText(this, "going to DB", Toast.LENGTH_SHORT).show();

        if(db != null){

            for(int cont = 1; cont < 3 ; cont++){
                int codigo = cont;
                String nombre = "Cli" + cont;
                String phone = cont + "XXXXXXX";

                /*db.execSQL("INSERT INTO Persona (id, name, phone)" +
                        "VALUES ("+codigo + ", '" +nombre+"', '"+phone+"')");*/

                ContentValues registro = new ContentValues();
                registro.put("id", codigo);
                registro.put("name", nombre);
                registro.put("phone", phone);
                long check = db.insert("Clientes", null, registro);
                if(check != -1){
                    Toast.makeText(this, "Added " + cont, Toast.LENGTH_SHORT).show();
                }


            }

            //SELECT 1 RAWQUERY
            String[] args = new String[]{"Cli1"};
            Cursor c = db.rawQuery("SELECT name, phone FROM CLientes WHERE name=?", args);
            String nombreCli="";
            String phoneCli="";

            if(c.moveToFirst()){
                do{
                    nombreCli = c.getString(0);
                    phoneCli = c.getString(1);

                }while(c.moveToNext());
            }
            Toast.makeText(this, "Select 1-> Nombre:" + nombreCli + " phone:" + phoneCli, Toast.LENGTH_SHORT).show();



            String[] fields = new String[]{"name", "phone"};
            String[] args2 = new String[]{"Cli1"};

            c = db.query("Clientes", fields,"name=?", args2, null, null, null);
            if(c.moveToFirst()){
                do{
                    nombreCli = c.getString(0);
                    phoneCli = c.getString(1);


                }while(c.moveToNext());
            }
            Toast.makeText(this, "Select 2-> Nombre:" + nombreCli + " phone:" + phoneCli, Toast.LENGTH_SHORT).show();


            db.close();
        }
    }





}
