package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class SearchCapacity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_capacity);
    }

    public void searchCapacity(View view) throws IOException {
        TextView tv;
        SaunaList the_list;
        EditText et1;
        Integer sc;
        Sauna curSauna;

        the_list = SaunaList.getInstance();

        tv = (TextView) findViewById(R.id.text_capacity);
        tv.setText("");

        //enter product code
        et1 = (EditText) findViewById(R.id.edit_capacity);
        sc = Integer.parseInt(et1.getText().toString());

        Integer curPos=0;
        Integer matches=0;

        while(curPos < the_list.size()) {
            curSauna = the_list.get(curPos);
            //if capacity matches append it to the textview and run again until you're at the end of the list
            if (curSauna.getCapacity().equals(sc)) {
                tv.append(curSauna.getMake() + ", ");
                tv.append(curSauna.getProductCode() + ", ");
                tv.append(curSauna.getWoodType() + ", ");
                tv.append(curSauna.getHeating() + ", ");
                tv.append(String.format("%.2f", curSauna.getPrice()) + "\n");
                matches++;
            }
            curPos++;
        }
        if(matches==0) {
            tv.append("There are no saunas with this capacity.");
        }
    }
}