package com.example.mylab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void displayWatches(View view)
        throws IOException
    {
        TextView tv;
        EditText et1;
        String filename;

        et1 = (EditText) findViewById(R.id.edit_file);
        filename = et1.getText().toString();

        tv = (TextView) findViewById(R.id.text_main);
        tv.setText("");

        URL file_url = new URL(filename);
        Scanner fsc = new Scanner(file_url.openStream());

        Watch watch[] = new Watch[100];
        int numwatches = 0;

        while(fsc.hasNext())
        {
            //create watch
            watch[numwatches] = new Watch();

            //read data for new watch from file
            watch[numwatches].setMake(fsc.nextLine());
            watch[numwatches].setModel(fsc.nextLine());
            watch[numwatches].setPower_source(fsc.nextLine());
            watch[numwatches].setWater_resistance(fsc.nextInt());
            fsc.nextLine();
            watch[numwatches].setBand_content(fsc.nextLine());
            watch[numwatches].setPrice(fsc.nextDouble());
            fsc.nextLine();

            numwatches++;
        }

        double avgWR = 0;
        double avgPrice = 0;
        
        for (int i=0; i<numwatches;i++) {
            
            tv.append("Watch " + (i+1) + "\n");
            tv.append(watch[i].getMake()+"\n");
            tv.append(watch[i].getModel()+"\n");
            tv.append(watch[i].getPower_source()+"\n");
            tv.append(watch[i].getWater_resistance()+"\n");
            avgWR += watch[i].getWater_resistance();
            tv.append(watch[i].getBand_content()+"\n");
            tv.append(watch[i].getPrice()+"\n");
            avgPrice += watch[i].getPrice();
        }

        tv.append("Watches Average Water Resistance: " + String.format("%.1f",avgWR/numwatches) +"\n");
        tv.append("Watches Average Price: " + String.format("%.2f",avgPrice/numwatches) +"\n");
    }
}