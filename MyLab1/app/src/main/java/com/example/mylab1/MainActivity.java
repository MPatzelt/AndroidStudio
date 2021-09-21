package com.example.mylab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void processPress(View view)
            throws java.io.IOException
    {
        TextView tv;
        EditText et1;
        EditText et2;
        String infilename;
        String outfilename;

        et1 = (EditText) findViewById(R.id.edit_infile);
        infilename = et1.getText().toString();

        et2 = (EditText) findViewById(R.id.edit_outfile);
        outfilename = et2.getText().toString();

        tv = (TextView) findViewById(R.id.text_main);
        tv.setText("");

        AssetManager assetmanager = getAssets();
        Scanner fsc = new Scanner(assetmanager.open(infilename));

        double nums[] = new double[100];
        int values = 0;

        File outfile = new File(getExternalFilesDir(null), outfilename);
        FileWriter fw = new FileWriter(outfile);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        while(fsc.hasNext())
        {
            nums[values] = fsc.nextDouble();
            values++;
        }
        fourth_root_it(nums, values);

        for (int i=0; i<values;i++) {
            tv.append(String.format("%.5f",nums[i]) + "\n");
            pw.append(String.format("%.5f",nums[i]) + "\n");
        }

        pw.close();

    }

    public void fourth_root_it(double[] a, int num_vals)
    {
        for (int i=0; i<num_vals;i++) {
            a[i] = Math.pow(a[i],(.25));
        }
    }
}