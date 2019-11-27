package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText mEntryCalculate;
    Button mButtonCalculate;
    TextView mLabelResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEntryCalculate = (EditText) findViewById(R.id.entryNum);
        mButtonCalculate = (Button) findViewById(R.id.buttonCalculate);
        mLabelResult = (TextView) findViewById(R.id.labelResult);


    }

    public void calculateOperation(View view){
        int n = Integer.parseInt(mEntryCalculate.getText().toString());
        mLabelResult.append(n+" != ");
        MyThread thread = new MyThread();
        thread.execute(n);

    }

    public int factorial(int n){
        int res=1;
        for(int i=1; i<=n;i++){
            res*=i;
            SystemClock.sleep(300);
        }
        return res;
    }
    class MyThread extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... n) {
            return factorial(n[0]);
        }

        protected void onPostExecute(Integer res){
            mLabelResult.append(res+"\n");
        }
    }



}
