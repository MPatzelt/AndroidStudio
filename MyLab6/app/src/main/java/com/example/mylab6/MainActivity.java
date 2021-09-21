package com.example.mylab6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fibonacci(View view) {
        TextView tv;
        EditText et1;
        String fibonacciValue;
        Integer fibonacciTest=0;

        et1 = (EditText) findViewById(R.id.edit_infile);
        fibonacciValue = et1.getText().toString();

        tv = (TextView) findViewById(R.id.text_main);

        try {
            fibonacciTest = fibonacciTest.parseInt(fibonacciValue);
            fibonacciValue = String.valueOf(fib(fibonacciTest));
            tv.setText("The fibonacci value of f(" + fibonacciTest +") is " + fibonacciValue + ".");
        }

        catch(NumberFormatException e) {
            tv.setText("Error: Invalid value was inputted.");
        }

    }

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }

        else if (n == 1 || n == 2) {
            return 1;
        }
        else {
            return fib(n-2) + fib(n-1); //something is wrong here

        }
    }
}