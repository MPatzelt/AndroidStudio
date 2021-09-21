package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URL;
import java.util.Scanner;

////////////////////////////////////////////////////////////////////////////////////////////
// Michael Patzelt////
// IT114, Section 002////
// Dr. Halper////
// App Project #1////
// Mar.30, 2021////////
// This app is designed to load a list of saunas and calculate the price of a sauna including shipping.
// /////////////////////////////////////////////////////////////////////////////////////////

// https://web.njit.edu/~mp675/it114/SaunaList.txt

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SaunaList the_list;

        // set the reference to the "main" TextView object so
        // we do not have to retrieve it in every method below

        tv = (TextView) findViewById(R.id.text_main);

        // create/access the list of strings

        the_list = SaunaList.getInstance();

    } // end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onOption1(MenuItem i)
    {
        // Display list of Sauna
        SaunaList the_list;
        Sauna currentSauna;

        the_list = SaunaList.getInstance();
        tv.setText("");

        for (int j=0; j<the_list.size();j++) {
            currentSauna = the_list.get(j);
            tv.append(currentSauna.getMake() + ", ");
            tv.append(currentSauna.getProductCode() + ", ");
            tv.append(currentSauna.getCapacity() + ", ");
            tv.append(String.format("%.2f", currentSauna.getPrice()) + "\n");
        }

        if(the_list.isEmpty())
            tv.append("There's nothing in the list.");

    } // end onOption1

    public void onOption2(MenuItem i)
    {
        //Loads a list of saunas from a link
        startActivity(new Intent(this, LoadSaunaList.class));

    } // end onOption2

    public void onOption3(MenuItem i)
    {
        //Add a new sauna
        startActivity(new Intent(this, AddSauna.class));

    } // end onOption3

    public void onOption4(MenuItem i)
    {
        // Show details of a sauna
        startActivity(new Intent(this, ShowSauna.class));

    } // end onOption4

    public void onOption5(MenuItem i)
    {
        // Remove a sauna
        startActivity(new Intent(this, RemoveSauna.class));
    } // end onOption5

    public void onOption6(MenuItem i)
    {
        //Adjust price of sauna (by a %)
        startActivity(new Intent(this, ChangePrice.class));

    }// end onOption6

    public void onOption7(MenuItem i)
    {
        //Show all saunas with a given capacity
        startActivity(new Intent(this, SearchCapacity.class));

    } // end onOption7

    public void onOption8(MenuItem i)
    {
        //Show the median price of the saunas
        startActivity(new Intent(this, MedianPrice.class));

    } // end onOption8

    public void onOption9(MenuItem i)
    {
        //Save list to file
        startActivity(new Intent(this, SaveList.class));

    } // end onOption9
}

//https://web.njit.edu/~halper/it114/asd.txt