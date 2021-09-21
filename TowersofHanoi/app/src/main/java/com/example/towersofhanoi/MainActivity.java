package com.example.towersofhanoi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/////////////////////////////////////////////////////////////////////////////////////
//
// Towers of Hanoi app for displaying the moves to complete the
// puzzle. Source and destination towers are input via radio
// groups
//
// Author: M. Halper
//
/////////////////////////////////////////////////////////////////////////////////////

public class MainActivity extends AppCompatActivity {

    private int sourceTowerIndex;  // current source tower
    private int destTowerIndex;    // current destination tower
    private TextView tv;
    private String tower[];        // names of towers, defined in resource file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get a handle on the TextView for displaying the moves

           tv = (TextView) findViewById(R.id.text_main);

        // by default: source tower is first tower (A), and dest tower is third
        // tower (C)

           sourceTowerIndex = 0;
           destTowerIndex = 2;

        // get the names of the towers from the resources

           tower = getResources().getStringArray(R.array.tower);

    } // end onCreate

/////////////////////////////////////////////////////////////////////////////////////

    public void onRadioSourceClicked(View view)
    {
        //
        // This method responds to a radio button click to
        // set the source tower
        //

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_source1:
                if (checked)
                    sourceTowerIndex = 0;
                break;

            case R.id.radio_source2:
                if (checked)
                    sourceTowerIndex = 1;
                break;

            case R.id.radio_source3:
                if (checked)
                    sourceTowerIndex = 2;
                break;
        }

    } // end onRadioSourceClicked

/////////////////////////////////////////////////////////////////////////////////////

    public void onRadioDestClicked(View view)
    {
        //
        // This method responds to a radio button click to
        // set the destination tower
        //

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_dest1:
                if (checked)
                    destTowerIndex = 0;
                break;

            case R.id.radio_dest2:
                if (checked)
                    destTowerIndex = 1;
                break;

            case R.id.radio_dest3:
                if (checked)
                    destTowerIndex = 2;
                break;
        }
    } // end onRadioDestClicked

/////////////////////////////////////////////////////////////////////////////////////

    public void showMoves(View view)
    {
        //
        // This method responds to the button press and displays the
        // required moves for the requested Towers of Hanoi puzzle.
        // It calls on the recursive method "towers" to display the
        // actual moves
        //

        EditText editText;
        int num_rings;
        String source;
        String spare;
        String dest;
        int spareTowerIndex;

        // make sure the source and dest towers are different

        if(sourceTowerIndex == destTowerIndex)

            tv.setText("The source and destination towers must be different.");

        else
        {
            // determine the spare tower index

               spareTowerIndex = 3 - (sourceTowerIndex + destTowerIndex);

            // get the number of rings.  If bad number, then set value to 1 by default

               editText = (EditText) findViewById(R.id.edit_rings);

            try
            {
                num_rings = Integer.parseInt(editText.getText().toString());
            }
            catch(NumberFormatException e)
            {
                num_rings = 1;
            }

            // get the tower names

           source = tower[sourceTowerIndex];
           dest = tower[destTowerIndex];
           spare = tower[spareTowerIndex];

           tv.setText("");

            tv.append("Number of rings: " + num_rings + "\n");
            tv.append("Source Tower: " + source + "\n");
            tv.append("Destination Tower: " + dest + "\n");
            tv.append("Spare Tower: " + spare + "\n");
            // YYY: Call towers
            towers(num_rings, source, dest, spare);



        }

    } // end showMoves

/////////////////////////////////////////////////////////////////////////////////////

    public void towers(int num_rings, String source, String dest, String spare)
    {
        // YYY: To be completed.
        if (num_rings == 1) {
            tv.append("Move disk from rod " + source + " to rod " + dest +"\n");
            return;
        }
        towers(num_rings-1, source, spare, dest);
        tv.append("Move disk from rod " + source + " to rod " + dest + "\n");
        towers(num_rings-1, spare, dest, source);
    }  // end towers

} // end MainActivity
