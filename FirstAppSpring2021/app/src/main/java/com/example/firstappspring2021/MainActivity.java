package com.example.firstappspring2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void processPress(View view)
    {

        TextView tv;
        EditText et1;
        EditText et2;
        String infilename;
        String outfilename;

        int num = 10;


        tv = (TextView) findViewById(R.id.text_main);

        tv.setText("Hello everyone.  We're in the method.\n");

        // get the values from the EditTexts and
        // display them

        et1 = (EditText) findViewById(R.id.edit_infile);
        et2 = (EditText) findViewById(R.id.edit_outfile);

        infilename = et1.getText().toString();
        outfilename = et2.getText().toString();

        tv.append("Input file's name is: " + infilename + "\n");
        tv.append("Output file's name is: " + outfilename + "\n");

        Log.d(TAG, "We are in the method \"processPress\"; num = " + num);
        Log.e(TAG, "We are in the method \"processPress\"; num = " + num);
        Log.w(TAG, "We are in the method \"processPress\"; num = " + num);

    } // end processPress

}