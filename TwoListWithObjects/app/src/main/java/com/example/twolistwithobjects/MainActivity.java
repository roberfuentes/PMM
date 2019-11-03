package com.example.twolistwithobjects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{

    public static Courses[] dataCourses = new Courses[]{
            new Courses("1SMR"),
            new Courses("2SMR"),
            new Courses("1DAM"),
            new Courses("2DAM")
    };

   /* public static Subjects[] dataSubjects = new Subjects[]{
            new Subjects("RDL"),
            new Subjects("RDL"),
            new Subjects("BD"),
            new Subjects("DIN")
    };*/

    public static Subjects[][] dataSubjects = new Subjects[][]{
            {new Subjects("SOM"), new Subjects("RDL")},
            {new Subjects("SER"), new Subjects("WEB")},
            {new Subjects("BD"), new Subjects("PROGRAMACION")},
            {new Subjects("DIN"), new Subjects("PMM")}
    };

    public static Subjects[] dataSubjectsShow = new Subjects[] {new Subjects("SOM"), new Subjects("RDL")};
    ListView mLCourses;
    ListView mLSubjects;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLCourses = (ListView)findViewById(R.id.lCourses);
        mLSubjects = (ListView)findViewById(R.id.lSubjects);

        AdapterCourses adapterCourse = new AdapterCourses(this);
        mLCourses.setAdapter(adapterCourse);

        final AdapterSubjects adapterSubject = new AdapterSubjects(this);
        //Si funciona si lo adaptamos directamente, cargan todos los elementos [][]
        //mLSubjects.setAdapter(adapterSubject);
        System.out.println("length"+dataSubjects[0].length);



        mLCourses.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                System.out.println("dice hola?");
                //adapterSubject.clear();


                //añade los 2 subjects correspondientes a un nuevo array
                for (int x=0;x<2;x++){
                    dataSubjectsShow[x] = dataSubjects[position][x];

                }
                System.out.println("dice hola 2?");

                System.out.println(dataSubjectsShow[0]);
                System.out.println(dataSubjectsShow[1]);
/*
                for(int x=0; x< 2; x++){
                    System.out.println("pasa antes del primero? " + x);
                    adapterSubject.add(dataSubjectsShow[x]);
            }
              */  System.out.println("dice hola? 3");
              //adapta los 2 subjects al array (lo hace en distintas lineas del listview
                mLSubjects.setAdapter(adapterSubject);

            }
        });










    }
}
