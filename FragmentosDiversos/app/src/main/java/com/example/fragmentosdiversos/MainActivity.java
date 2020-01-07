package com.example.fragmentosdiversos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

        public class MainActivity extends AppCompatActivity
        {

            Button mButton;
            Button mButtonDialog;
            Button mButtonDialogF;
            int mStackPosition = 0;
            @Override
            protected void onCreate(Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                mButton = (Button)findViewById(R.id.newFragment);
                mButtonDialog = (Button)findViewById(R.id.dialog);
                mButtonDialogF = (Button)findViewById(R.id.dialogF);

                mButtonDialog.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        showDialog();
                    }
                });
                mButtonDialogF.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        showDialogF();
                    }
                });
                mButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        addFragment();
                    }
                });



                if(savedInstanceState == null){
            Fragment fragment = DynamicFragment.newInstance(mStackPosition);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.container, fragment);
            ft.commit();
        }else{
            mStackPosition = savedInstanceState.getInt("position");
        }
    }



    public void addFragment(){
        mStackPosition++;
        Fragment fragment = DynamicFragment.newInstance(mStackPosition);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }


    public void showDialog(){
        new AlertDialog.Builder(this)
        .setTitle("Selecciona una acción")
        .setPositiveButton("Nuevo", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                addFragment();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        }).setNeutralButton("Back", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                getFragmentManager().popBackStack();
            }
        }).create().show();
    }

    public void showDialogF(){
                DialogFragment newFragment = MyDialogFragment.newInstance("cADENA DE PARAMETRO");
                newFragment.show(getFragmentManager(), "dialog");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("position", mStackPosition);
    }
}
