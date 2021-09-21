package com.example.circleareaclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CommunicateActivity extends AppCompatActivity {

    private Socket socket = null;
    private PrintWriter out = null;
    private Scanner in = null;
    private TextView tv;

    // In onCreate, connect to the server, and then wait for the
    // user to input the radius and press the button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate);

        int port;
        String hostname;

        // Get the hostname from the intent

        Intent intent = getIntent();
        hostname = intent.getStringExtra(MainActivity.HOST_NAME);

        // Get the port from the intent.  Default port is 4000

        port = intent.getIntExtra(MainActivity.PORT, 4000);

        // get a handle on the textview for displaying the area

        tv = (TextView) findViewById(R.id.text_answer);

        // Try to open the connection to the server

        try
        {
            socket = new Socket(hostname, port);

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(new InputStreamReader(socket.getInputStream()));

            tv.setText("Connected to server.");
        }
        catch(IOException e)  // socket problems
        {
            tv.setText("Problem: " + e.toString());
            socket = null;
        }

    } // end onCreate

    // send the radius to the server, and receive the result (area of the
    // circle) in return

    public void sendRadius(View view)
    {
        EditText et;
        double radius;
        double area;

        // are we connected?

        if(socket == null)
        {
            tv.setText("Not connected.");
        }
        else
        {
            // get the radius to send to the server

            et = (EditText) findViewById(R.id.edit_radius);
            radius = Double.parseDouble(et.getText().toString());

            try
            {
                // send the radius to the server

                out.println(radius);

                // get result

                area = in.nextDouble();

                // display the result

                tv.setText("The area is " + String.format("%.2f", area));

                // close connection to server

                out.close();
                in.close();
                socket.close();

                // indicate that we're no longer connected

                socket = null;

            }
            catch (IOException e)  // socket problems
            {
                tv.setText("Problem: " + e.toString());
            }

        }

    } // end sendRadius

} // end CommunicateActivity
