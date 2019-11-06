package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    TextView mTextView1;
    TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView1 = (TextView)findViewById(R.id.txtView1);


        registerForContextMenu(mTextView1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

   @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opt1:
                Toast.makeText(getApplicationContext(), "PASÉ POR AQUÍ 1", Toast.LENGTH_SHORT).show(); //Correcto
                return true;
            case R.id.opt2:
                Toast.makeText(getApplicationContext(), "PASÉ POR AQUÍ 2", Toast.LENGTH_SHORT).show(); //Correcto
                return true;
            case R.id.opt3:
                Toast.makeText(getApplicationContext(), "PASÉ POR AQUÍ 3", Toast.LENGTH_SHORT).show(); //Correcto
                return true;
            case R.id.opt31:
                Toast.makeText(getApplicationContext(), "PASÉ POR AQUÍ 31", Toast.LENGTH_SHORT).show(); //Correcto
                return true;
            case R.id.opt32:
                Toast.makeText(getApplicationContext(), "PASÉ POR AQUÍ 32", Toast.LENGTH_SHORT).show(); //Correcto
                return true;
            case R.id.opt33:
                Toast.makeText(getApplicationContext(), "PASÉ POR AQUÍ 33", Toast.LENGTH_SHORT).show(); //Correcto
                return true;
            default:
                return super.onOptionsItemSelected(item);
            }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mContextual1:
                Toast.makeText(getApplicationContext(), "PASÉ POR AQUÍ mContextual1", Toast.LENGTH_SHORT).show(); //Correcto
                return true;
            case R.id.mContextual2:
                Toast.makeText(getApplicationContext(), "PASÉ POR AQUÍ mContextual2", Toast.LENGTH_SHORT).show(); //Correcto
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
