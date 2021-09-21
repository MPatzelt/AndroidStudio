package com.example.mylab4;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void displayImage(View view)
            throws IOException
    {
        EditText et1;
        String filename;

        et1 = (EditText) findViewById(R.id.edit_file);
        filename = et1.getText().toString();

        Uri uri = Uri.parse(filename);// image_url is a string
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.image_area);
        draweeView.setImageURI(uri);

        //https://web.njit.edu/~mp675/it114/discord.png
    }
}