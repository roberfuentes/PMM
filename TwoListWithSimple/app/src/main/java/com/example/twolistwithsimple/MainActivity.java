package com.example.twolistwithsimple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{


    ListView mLCourses;
    ListView mLSubjects;

    String cursos[] = {"1SMR", "2SMR", "1DAM", "2DAM"};
    String subjects[][] = {{"SOM", "RDL"},
                            {"SER", "SOR"},
                            {"BD", "PROGRAMACIÓN"},
                            {"DIN", "PMM"}};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLCourses = (ListView)findViewById(R.id.lCourses);
        mLSubjects = (ListView)findViewById(R.id.lSubjects);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cursos);
        mLCourses.setAdapter(myAdapter);

        final ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        mLCourses.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                myAdapter2.clear();
                for(int i = 0; i < subjects[position].length; i++){
                    myAdapter2.add(subjects[position][i]);

                    System.out.println("hola");
                }
                mLSubjects.setAdapter(myAdapter2);

            }
        });


    }
}
