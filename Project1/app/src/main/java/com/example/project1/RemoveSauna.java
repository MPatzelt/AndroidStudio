package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;

public class RemoveSauna extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_sauna);
    }

    public void removeSauna(View view) throws IOException {
        TextView tv;
        SaunaList the_list;
        EditText et1;
        String pc;
        Sauna curSauna;

        the_list = SaunaList.getInstance();

        tv = (TextView) findViewById(R.id.text_remove);


        //enter product code
        et1 = (EditText) findViewById(R.id.edit_product_code);
        pc = et1.getText().toString();

        Integer curPos=0;
        boolean found = false;

        //check entered code against the Sauna list, stops if it encounters a match
        while(!found && curPos < the_list.size()) {
            curSauna = the_list.get(curPos);

            if (curSauna.getProductCode().equalsIgnoreCase(pc)) {
                the_list.remove(curSauna);
                found = true;
            }
            curPos++;
        }
        if (!found)
            tv.setText("There are no matches for this product code.");
        else
            tv.setText("Sauna has been successfully removed.");
    }
}