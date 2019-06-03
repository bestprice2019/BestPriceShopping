package com.example.bpsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static com.example.bpsl.NewListDescription.descriptionDate;
import static com.example.bpsl.NewListDescription.descriptionTime;
import static com.example.bpsl.NewListDescription.descriptionTitle;

/**
 * This class allows users to save their new shopping list.
 */
public class NewListSave extends AppCompatActivity {

    private Button button;
    Database myDb;
    TextView TVtitle,TVdate,TVtime,TVtotal,TVdirection;
    NewListSearch NLSearch = new NewListSearch();
    public double finalTotal;
    public String supermarket="";
    public static int id = 0;
    SavedListSecond savedListSecond = new SavedListSecond();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlistsave);
        myDb = new Database(this);

        //to calculate total and direction
        double total = 0;
        for (Item tempItem : NLSearch.cart) {
            total = total + tempItem.getPrice();
            if (tempItem.getSupermarket().contains("Count")) {
                if (!supermarket.contains("Count")) {
                    supermarket = supermarket + ".CountDown";
                } else {
                }
            } else {
            }
            if (tempItem.getSupermarket().contains("Pak")) {
                if (!supermarket.contains("Pak")) {
                    supermarket = supermarket + ".PakNSave";
                } else {
                }
            } else {
            }
            if (tempItem.getSupermarket().contains("New")) {
                if (!supermarket.contains("New")) {
                    supermarket = supermarket + ".NewWorld";
                } else {
                }
            } else {
            }
        }
        BigDecimal bd = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
        finalTotal = bd.doubleValue();



        button = (Button) findViewById(R.id.buttonSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id++;
                String realID = Integer.toString(id);
                ArrayList<Item> myCart = new ArrayList<>();

                for(Item temp:NLSearch.cart){
                    myCart.add(temp);
                }

                boolean inserting = savedListSecond.listOfShoppingList.add(new ShoppingList(realID,descriptionTitle,descriptionDate,descriptionTime,finalTotal,myCart,supermarket));
                NLSearch.cart.clear();
                Intent intent = new Intent(NewListSave.this, SavedListSecond.class);
                startActivity(intent);

//                boolean inserting =
//                        myDb.insertDataListTable(descriptionTitle, descriptionDate,
//                        descriptionTime, finalTotal);
                if(inserting = true)
                    Toast.makeText(NewListSave.this,"New LIst is saved.",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(NewListSave.this,"Saving Error!",Toast.LENGTH_LONG).show();



            }
        });

        TVtitle = (TextView)findViewById(R.id.TVtitle);
        TVtitle.setText("REFERENCE: "+ descriptionTitle);

        TVdate = (TextView)findViewById(R.id.TVdate);
        TVdate.setText("DATE: "+ descriptionDate);

        TVtime = (TextView)findViewById(R.id.TVtime);
        TVtime.setText("TIME: "+ descriptionTime);

        TVtotal = (TextView)findViewById(R.id.TVtotal);
        TVtotal.setText("TOTAL PRICE: $"+finalTotal);

        TVdirection = (TextView)findViewById(R.id.TVdirection);
        TVdirection.setText("SUPERMARKET: "+supermarket+".");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_newlist_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.app_bar_edit_description){
            Intent nextIntent = new Intent(NewListSave.this,NewListDescription.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_edit_item){
            Intent nextIntent = new Intent(NewListSave.this,NewListEdit.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_mainMenu){
            Intent nextIntent = new Intent(NewListSave.this,MainActivity.class);
            startActivity(nextIntent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
