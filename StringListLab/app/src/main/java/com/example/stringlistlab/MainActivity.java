package com.example.stringlistlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/////////////////////////////////////////////////////
//
// This app manipulates a list of strings.  Options
// are chosen from the main app bar.
//
// Some options just display results in the main
// TextView.  Others invoke new activities to carry
// out their tasks.
//
// Author: M. Halper
//
/////////////////////////////////////////////////////

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        StringList the_list;

        // set the reference to the "main" TextView object so
        // we do not have to retrieve it in every method below

        tv = (TextView) findViewById(R.id.text_main);

        // create/access the list of strings

        the_list = StringList.getInstance();

        // put some strings on the list (if the list is empty).  Note that the
        // "new" list might not be empty due to a restart of the app

        if(the_list.isEmpty())
        {

            the_list.add(the_list.size(), "pizza");
            the_list.add(the_list.size(), "crackers");
            the_list.add(the_list.size(), "peanut butter");
            the_list.add(the_list.size(), "jelly");
            the_list.add(the_list.size(), "bread");

        }

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
        // show the list in order

        int j;
        StringList the_list;

        tv.setText("");

        // get the list

        the_list = StringList.getInstance();

        // display the list items

        for(j = 0; j < the_list.size(); j++)

            tv.append(the_list.get(j) + '\n');

        if(the_list.isEmpty())
            tv.append("There's nothing in the list.");

    } // end onOption1

    public void onOption2(MenuItem i)
    {
        // YYY: show the list in reverse order

        int j;
        StringList the_list;

        tv.setText("");

        // get the list

        the_list = StringList.getInstance();

        // display the reverse list items

        for(j = the_list.size()-1; j >= 0; j--)

            tv.append(the_list.get(j) + '\n');

    } // end onOption2

    public void onOption3(MenuItem i)
    {
        // YYY: show the list size
        int j;
        int k=0;
        StringList the_list;
        tv.setText("");

        // get the list

        the_list = StringList.getInstance();

        tv.append(Integer.toString(the_list.size()));

    } // end onOption3

    public void onOption4(MenuItem i)
    {

        // Start the activity to add a new item to the list

        startActivity(new Intent(this, AddItemActivity.class));

    } // end onOption4

    public void onOption5(MenuItem i)
    {

        // YYY: Start the activity to remove an item from the list

        startActivity(new Intent(this, RemoveItemActivity.class));

    } // end onOption5

    public void onOption6(MenuItem i)
    {

        // YYY: Remove all items from the list
        StringList the_list;

        tv.setText("");

        // get the list

        the_list = StringList.getInstance();

        while(!the_list.isEmpty()) {
            the_list.remove(0);
        }


    } // end onOption6

} // end MainActivity
