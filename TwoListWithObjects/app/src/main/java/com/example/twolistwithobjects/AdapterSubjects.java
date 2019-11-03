package com.example.twolistwithobjects;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterSubjects extends ArrayAdapter
{

    private
    Activity context;

    AdapterSubjects(Activity context){
        super(context, R.layout.dropdown_subjects, MainActivity.dataSubjectsShow);
        this.context = context;
    }

    public View getView(int i, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.dropdown_subjects, null);

        TextView lblSubjectOne = (TextView)view.findViewById(R.id.lblSubject1);
        TextView lblSubjectTwo = (TextView)view.findViewById(R.id.lblSubject2);
        int x = 0;
        //Recorre la 2da dimensión y consigue el subject (NO FUNCIONA)

            lblSubjectOne.setText(MainActivity.dataSubjectsShow[i].getSubject());
            lblSubjectTwo.setText(MainActivity.dataSubjectsShow[i].getSubject());





        return view;

    }

}
