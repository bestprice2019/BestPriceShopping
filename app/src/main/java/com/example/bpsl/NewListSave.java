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

import static com.example.bpsl.NewListDescription.descriptionDate;
import static com.example.bpsl.NewListDescription.descriptionTime;
import static com.example.bpsl.NewListDescription.descriptionTitle;

public class NewListSave extends AppCompatActivity {

    private Button button;
    Database myDb;
    TextView TVtitle,TVdate,TVtime,TVtotal;
    NewListSearch NLSearch = new NewListSearch();
    public double descriptionTotal;
    public double finalTotal;
    public static int id = 0;
    SavedListSecond savedListSecond = new SavedListSecond();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlistsave);
        myDb = new Database(this);

        DecimalFormat df = new DecimalFormat("####0.00");
        finalTotal = Double.parseDouble(df.format(this.calculateTotal(NLSearch.cart)));

        button = (Button) findViewById(R.id.buttonSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id++;
                String realID = Integer.toString(id);
                ArrayList<Item> myCart = new ArrayList<>();
//                myCart=NLSearch.cart;

                for(Item temp:NLSearch.cart){
                    myCart.add(temp);
                }

                boolean inserting = savedListSecond.listOfShoppingList.add(new ShoppingList(realID,descriptionTitle,descriptionDate,descriptionTime,Double.toString(finalTotal),myCart,"Location"));
                NLSearch.cart.clear();
                Intent intent = new Intent(NewListSave.this, SavedListSecond.class);
                startActivity(intent);

//                boolean inserting =
                        myDb.insertDataListTable(descriptionTitle, descriptionDate,
                        descriptionTime, finalTotal);
                if(inserting = true)
                    Toast.makeText(NewListSave.this,"New LIst is saved.",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(NewListSave.this,"Saving Error!",Toast.LENGTH_LONG).show();



            }
        });

        TVtitle = (TextView)findViewById(R.id.TVtitle);
        TVtitle.setText("REFERENCE:"+ descriptionTitle);

        TVdate = (TextView)findViewById(R.id.TVdate);
        TVdate.setText("DATE:"+ descriptionDate);

        TVtime = (TextView)findViewById(R.id.TVtime);
        TVtime.setText("TIME:"+ descriptionTime);

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
