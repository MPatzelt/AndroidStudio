package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;

public class ShowSauna extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sauna);
    }

    public void showSauna(View view) throws IOException {
        TextView tv;
        SaunaList the_list;
        EditText et1;
        String pc;
        Sauna curSauna;

        the_list = SaunaList.getInstance();

        tv = (TextView) findViewById(R.id.text_show);
        tv.setText("");

        //enter product code
        et1 = (EditText) findViewById(R.id.edit_product_code);
        pc = et1.getText().toString();

        Integer curPos=0;
        boolean found = false;

        //check entered code against the Sauna list, stops if it encounters a match
        while(!found && curPos < the_list.size()) {
            curSauna = the_list.get(curPos);

            //set to true to break the loop
            if (curSauna.getProductCode().equalsIgnoreCase(pc)) {
                tv.append(curSauna.getMake() + "\n");
                tv.append(curSauna.getWoodType() + "\n");
                tv.append(curSauna.getCapacity() + "\n");
                tv.append(curSauna.getHeating() + "\n");
                tv.append("$" + curSauna.getPrice() + "\n");
                tv.append("$" + String.format("%.2f", curSauna.getShipping()) + "\n");
                tv.append("$" + String.format("%.2f",(curSauna.getPrice() + curSauna.getShipping())) + "\n");
                Uri uri = Uri.parse(curSauna.getImage());// image_url is a string
                SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.image_area);
                draweeView.setImageURI(uri);
                found = true;
            }
            curPos++;
        }
        if (!found)
            tv.setText("There are no matches for this product code.");

    }
}