package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class AddSauna extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sauna);
    }

    public void addSauna(View view) throws IOException {
        TextView tv;
        SaunaList the_list;

        EditText et1;
        EditText et2;
        EditText et3;
        EditText et4;
        EditText et5;
        EditText et6;
        EditText et7;
        String make;
        String pc;
        String wood;
        Integer capacity;
        String heating;
        Double price;
        String url;

        the_list = SaunaList.getInstance();

        tv = (TextView) findViewById(R.id.text_add);
        tv.setText("The sauna has been successfully added");

        et1 = (EditText) findViewById(R.id.new_make);
        make = et1.getText().toString();

        et2 = (EditText) findViewById(R.id.new_product_code);
        pc = et2.getText().toString();

        et3 = (EditText) findViewById(R.id.new_wood);
        wood = et3.getText().toString();

        et4 = (EditText) findViewById(R.id.new_capacity);
        capacity = Integer.parseInt(et4.getText().toString());

        et5 = (EditText) findViewById(R.id.new_heating);
        heating = et5.getText().toString();

        et6 = (EditText) findViewById(R.id.new_price);
        price = Double.parseDouble(et6.getText().toString());

        et7 = (EditText) findViewById(R.id.new_url);
        url = et7.getText().toString();

        Sauna newSauna = new Sauna();
        newSauna.setMake(make);
        newSauna.setProductCode(pc);
        newSauna.setWoodType(wood);
        newSauna.setCapacity(capacity);
        newSauna.setHeating(heating);
        newSauna.setPrice(price);
        newSauna.setImage(url);

        the_list.add(the_list.size(), newSauna);
    }

}