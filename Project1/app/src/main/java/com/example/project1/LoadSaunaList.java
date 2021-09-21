package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class LoadSaunaList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_sauna_list);
    }

    public void loadSauna(View view) throws IOException {
        TextView tv;
        EditText et1;
        String filename;
        SaunaList the_list;

        the_list = SaunaList.getInstance();

        et1 = (EditText) findViewById(R.id.edit_file);
        filename = et1.getText().toString();

        tv = (TextView) findViewById(R.id.text_load);
        tv.setText("");

        try {
            URL file_url = new URL(filename);
            Scanner fsc = new Scanner(file_url.openStream());

            Sauna mySauna;

            //Figure out how to check if file exists and toasts

            while (fsc.hasNext()) {
                //create sauna
                mySauna = new Sauna();

                //read data for new saunas from file
                mySauna.setMake(fsc.nextLine());
                mySauna.setProductCode(fsc.nextLine());
                mySauna.setWoodType(fsc.nextLine());
                mySauna.setCapacity(fsc.nextInt());
                fsc.nextLine();
                mySauna.setHeating(fsc.nextLine());
                mySauna.setPrice(fsc.nextDouble());
                fsc.nextLine();
                mySauna.setImage(fsc.nextLine());

                the_list.add(the_list.size(), mySauna);
            }
            tv.setText("Sauna has been successfully loaded.");
        }

        catch (IOException e) {
            tv.setText("There is nothing here.");
            //Toast.makeText(LoadSaunaList.this, "There is nothing here.", Toast.LENGTH_SHORT).show();
            // Code is correct but doesn't run on my machine
        }


        /*for (int i=0; i<the_list.size();i++) {
            mySauna = the_list.get(i);

            tv.append(mySauna.getMake()+"\n");
            tv.append(mySauna.getProductCode()+"\n");
            tv.append(mySauna.getWoodType()+"\n");
            tv.append(mySauna.getCapacity()+"\n");
            tv.append(mySauna.getHeating()+"\n");
            tv.append(mySauna.getPrice()+"\n");
            tv.append(mySauna.getImage()+"\n"+"\n");
        }*/
    }
}