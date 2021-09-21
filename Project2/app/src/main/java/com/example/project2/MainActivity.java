package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

////////////////////////////////////////////////////////////////////////////////////////////
// Michael Patzelt////
// IT114, Section 002////
// Dr. Halper////
// App Project #2////
// Apr.22, 2021////////
// This app is designed to create a server/client that allows the client to enter two decimal numbers and perform
// one of four different operations.
// ///////////////

public class MainActivity extends AppCompatActivity {

    public final static String HOST_NAME = "com.example.bdclient.HOSTNAME";
    public final static String PORT = "com.example.bdclient.PORT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Allow for network access on the main thread

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    } // end onCreate

    public void connectServer(View view)
    {
        EditText et1;
        EditText et2;
        String hostname;
        int port;
        Intent intent = new Intent(this, CommunicateActivity.class);

        // get the hostname and the port

        et1 = (EditText) findViewById(R.id.edit_host);
        hostname = et1.getText().toString();

        et2 = (EditText) findViewById(R.id.edit_port);
        port = Integer.parseInt(et2.getText().toString());

        // start the activity for communicating with the client.  Pass it
        // the hostname and port in the intent

        intent.putExtra(HOST_NAME, hostname);
        intent.putExtra(PORT, port);

        startActivity(intent);

    } // end connectServer

} // end MainActivity