package com.example.stringlistlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RemoveItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);
    }

    // add the item to the list

    public void removeItem(View view)
    {

        EditText et1;
        int pos;
        StringList the_list;

        // get the new item and its desired position
        et1 = (EditText) findViewById(R.id.edit_position);
        pos = Integer.parseInt(et1.getText().toString());

        // Get the string list instance

        the_list = StringList.getInstance();

        // try to put the new item on the list

        try
        {
            the_list.remove(pos);

            Toast.makeText(RemoveItemActivity.this,
                    "List Position " + pos + " removed from the list",
                    Toast.LENGTH_SHORT).show();
        }
        catch(IndexOutOfBoundsException e)
        {
            Toast.makeText(RemoveItemActivity.this,
                    "Failed to remove item in the list",
                    Toast.LENGTH_SHORT).show();

        }

    } // end addItem

} // end RemoveItemActivity

