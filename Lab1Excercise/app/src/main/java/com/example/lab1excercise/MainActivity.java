package com.example.lab1excercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void processPress(View view) throws java.io.IOException
    {
        TextView tv;
        EditText et1;
        String infilename;

        et1 = (EditText) findViewById(R.id.edit_infile);
        infilename = et1.getText().toString();

        tv = (TextView) findViewById(R.id.text_main);
        tv.setText("");

        AssetManager assetmanager = getAssets();
        Scanner fsc = new Scanner(assetmanager.open(infilename));

        float nums[] = new float[100];
        int numbers = 0;

        while(fsc.hasNext())
        {
            nums[numbers] = fsc.nextFloat();
            tv.append(nums[numbers] + "\n");
            numbers++;
        }

    } // end processPress

}