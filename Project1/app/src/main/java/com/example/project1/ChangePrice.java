package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;

public class ChangePrice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_price);
    }

    public void changePrice(View view) throws IOException {
        TextView tv;
        SaunaList the_list;
        EditText et1;
        EditText et2;
        String pc1;
        Double pc2;
        Sauna curSauna;

        the_list = SaunaList.getInstance();

        tv = (TextView) findViewById(R.id.text_cp);

        //enter product code
        et1 = (EditText) findViewById(R.id.edit_product_code);
        pc1 = et1.getText().toString();

        //ask user for value, negative or positive
        et2 = (EditText) findViewById(R.id.price_change);
        pc2 = Double.parseDouble(et2.getText().toString());

        Integer curPos=0;
        boolean found = false;
        Double newPrice;

        //check entered code against the Sauna list, stops if it encounters a match
        while(!found && curPos < the_list.size()) {
            curSauna = the_list.get(curPos);

            //set to true to break the loop
            if (curSauna.getProductCode().equalsIgnoreCase(pc1)) {
                //based on whether or not number is positive or negative, add or subtract from price
                newPrice = curSauna.getPrice() + (pc2/100.0) * curSauna.getPrice();

                curSauna.setPrice(newPrice);
                found = true;
            }
            curPos++;
        }
        if (!found)
            tv.setText("There are no matches for this product code.");
        else
            tv.setText("Price successfully changed");

    }
}