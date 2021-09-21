package com.example.project2;

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

        // get a handle on the TextView for displaying the status

        tv = (TextView) findViewById(R.id.text_answer);

        // Try to open the connection to the server

        try
        {
            socket = new Socket(hostname, port);

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(new InputStreamReader(socket.getInputStream()));

            tv.setText("Connected to server.");
        }
        catch (IOException e)  // socket problems
        {
            tv.setText("Problem: " + e.toString());
            socket = null;
        }

    } // end onCreate

    public void sendOperation(View view) {
        EditText et1;
        EditText et2;
        EditText et3;
        String user_operation;
        String operand1;
        String operand2;
        String result;
        //created three variables for the user input

        // are we connected?

        if(socket == null)
        {
            tv.setText("Not connected.");
        }
        else
        {
            //take the user input from each field and sends it to the server
            et1 = (EditText) findViewById(R.id.edit_operation);
            user_operation = et1.getText().toString();

            et2 = (EditText) findViewById(R.id.edit_bd1);
            operand1 = et2.getText().toString();

            et3 = (EditText) findViewById(R.id.edit_bd2);
            operand2 = et3.getText().toString();

            //prints the three inputs out on the server side
            out.println(user_operation);
            out.println(operand1);
            out.println(operand2);

            //retrieves the answer from the server
            result = in.nextLine();

            tv.setText(result);
        }

    }
}

//afsconnect2.njit.edu