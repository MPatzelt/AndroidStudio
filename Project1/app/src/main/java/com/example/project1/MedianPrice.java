package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class MedianPrice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_median_price);
    }

    public void findMedian(View view) throws IOException {
        TextView tv;
        SaunaList the_list;
        LinkedList<Double> priceList;
        Sauna curSauna;
        Double medianPrice;
        Integer median_marker;

        the_list = SaunaList.getInstance();

        tv = (TextView) findViewById(R.id.text_median);
        tv.setText("");

        priceList = new LinkedList<Double>();

        if(the_list.isEmpty())
            tv.append("Unable to compute, the list is empty.");
        //fills another empty list with the Price values in the list
        else {
            for (int i = 0; i < the_list.size(); i++) {
                curSauna = the_list.get(i);
                priceList.add(curSauna.getPrice());
            }

            //sort the priceList
            Collections.sort(priceList);
            tv.setText("");

            if (priceList.size() % 2 == 1)
                medianPrice = priceList.get((priceList.size() - 1) / 2);
            else {
                median_marker = priceList.size() / 2;
                medianPrice = (priceList.get(median_marker) + priceList.get(median_marker - 1)) / 2.0;
            }

            tv.append(String.format("%.2f", medianPrice));
        }

    }
}