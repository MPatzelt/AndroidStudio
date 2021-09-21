package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_list);
    }

    public void saveList(View view) throws IOException {
        TextView tv;
        EditText et1;
        SaunaList the_list;
        String outfilename;
        Sauna currentSauna;

        the_list = SaunaList.getInstance();

        et1 = (EditText) findViewById(R.id.edit_output);
        outfilename = et1.getText().toString();

        tv = (TextView) findViewById(R.id.text_save);

        File outfile = new File(getExternalFilesDir(null), outfilename);
        FileWriter fw = new FileWriter(outfile);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        for (int i=0; i<the_list.size();i++) {
            currentSauna = the_list.get(i);
            pw.print(currentSauna.getMake() + "\n");
            pw.print(currentSauna.getProductCode() + "\n");
            pw.print(currentSauna.getCapacity() + "\n");
            pw.print(currentSauna.getWoodType() + "\n");
            pw.print(currentSauna.getHeating() + "\n");
            pw.print(String.format("%.2f", currentSauna.getPrice()) + "\n");
            pw.print(currentSauna.getImage() + "\n");
        }

        pw.close();
        tv.setText("File successfully saved.");

    }
}