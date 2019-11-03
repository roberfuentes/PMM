package com.example.twolistwithobjects;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterCourses extends ArrayAdapter
{

    Activity context;

    AdapterCourses(Activity context){
        super(context, R.layout.dropdown_courses, MainActivity.dataCourses);
        this.context = context;
    }

    public View getView(int i, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.dropdown_courses,null);

        TextView lblCourse = (TextView)view.findViewById(R.id.lblCourse);
        lblCourse.setText(MainActivity.dataCourses[i].getCurso());

        return view;
    }
}
