package com.example.thermostatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeThermostat(View view) {
        TextView tv;
        EditText et1;
        String infilename;

        et1 = (EditText) findViewById(R.id.edit_infile);
        infilename = et1.getText().toString();

        tv = (TextView) findViewById(R.id.text_main);

        try {
            SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.image_area);
            int num = Integer.parseInt(infilename);
            Log.i("",num+" is an integer");
            tv.setText("The thermostat has been successfully changed to \n" + infilename);
            if(num > 50) {
                draweeView.setImageURI("https://web.njit.edu/~mp675/SeniorCapstone/Hot.jpg");
            }
            else {
                draweeView.setImageURI("https://web.njit.edu/~mp675/SeniorCapstone/Cold.jpg");
            }
        } catch (NumberFormatException e) {
            Log.i("",infilename +" is not an integer");
            tv.setText("Please enter an integer to change the thermostat.");
        }


    }
}

//https://web.njit.edu/~mp675/SeniorCapstone/Hot.jpg
//https://web.njit.edu/~mp675/SeniorCapstone/Cold.jpg