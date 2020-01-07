package com.example.fragmentobasico;

import androidx.appcompat.app.AppCompatActivity;
import android.app.FragmentTransaction;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public Fragment mFrag;
    int mStackPosition = 1;
    int dibujos[] = {R.drawable.hielo, R.drawable.fuego, R.drawable.aire};
    Button manejaFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        manejaFragment = (Button)findViewById(R.id.btn);
        manejaFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment();
            }
        });

        if(savedInstanceState == null){
            Fragment miFragment = MiFragmento.newInstance(dibujos[0]);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.miFrg, miFragment);
            ft.commit();
        }else{
            mStackPosition = savedInstanceState.getInt("position");
        }

    }
    public void addFragment(){
        Fragment miFragment;
        Random r = new Random();
        miFragment = MiFragmento.newInstance(dibujos[r.nextInt(dibujos.length)]);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.miFrg, miFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }
}
