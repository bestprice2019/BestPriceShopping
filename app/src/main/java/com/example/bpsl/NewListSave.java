package com.example.bpsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class NewListSave extends AppCompatActivity {

    private Button button;
    Database myDb;
    NewListDescription NLD = new NewListDescription();
    TextView TVtitle,TVdate,TVtime,TVtotal;
    NewListSearch NLS = new NewListSearch();
    public double descriptionTotal;
    public double finalTotal;
    List<Item> myCart = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlistsave);
        myDb = new Database(this);

        DecimalFormat df = new DecimalFormat("####0.00");
        finalTotal = Double.parseDouble(df.format(this.calculateTotal(NLS.cart)));

        button = (Button) findViewById(R.id.buttonSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewListSave.this, SavedList.class);
                startActivity(intent);

                boolean inserting = myDb.insertDataListTable(NLD.descriptionTitle, NLD.descriptionDate,
                        NLD.descriptionTime, finalTotal);
                if(inserting = true)
                    Toast.makeText(NewListSave.this,"New LIst is saved.",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(NewListSave.this,"Saving Error!",Toast.LENGTH_LONG).show();

            }
        });

        TVtitle = (TextView)findViewById(R.id.TVtitle);
        TVtitle.setText("REFERENCE:"+NLD.descriptionTitle);

        TVdate = (TextView)findViewById(R.id.TVdate);
        TVdate.setText("DATE:"+NLD.descriptionDate);

        TVtime = (TextView)findViewById(R.id.TVtime);
        TVtime.setText("TIME:"+NLD.descriptionTime);

        TVtotal = (TextView)findViewById(R.id.TVtotal);
        TVtotal.setText("TOTAL PRICE:"+Double.toString(finalTotal)+"$");
    }

    public double calculateTotal(List<Item> list){
        descriptionTotal = 0;
        for (Item tempItem : list) {
            descriptionTotal = descriptionTotal + Double.parseDouble(tempItem.getPrice());
        }


        return descriptionTotal;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
