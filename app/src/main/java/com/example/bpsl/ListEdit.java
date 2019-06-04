package com.example.bpsl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class ListEdit extends AppCompatActivity {

    ListView listView;
    public static ShoppingList editSPL;
    SavedListSecond savedListSecondInListEdit = new SavedListSecond();

    //When this class is activated, it instantiates all variables and sets layout
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listedit);
        listView = (ListView) findViewById(R.id.LVEdit);
        ArrayAdapter<Item> adapter = new ArrayAdapter<>(this,R.layout.listedit,R.id.TVEdit,editSPL.getList());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)  {
                //When the item was clicked, alert dialog will be popped up to delete the items in the shopping list.
                AlertDialog.Builder builder = new AlertDialog.Builder(ListEdit.this);
                builder.setMessage("Do you want to delete this item from the list?").setCancelable(true)
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        })
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(editSPL.getList().size()>1) {
                                    editSPL.getList().remove(position);
                                    ArrayAdapter<Item> adapterTwo = new ArrayAdapter<>(ListEdit.this, R.layout.listedit, R.id.TVEdit, editSPL.getList());
                                    listView.setAdapter(adapterTwo);
                                }
                                else {
                                    //When there is only 1 item left in the shopping list, alert dialog will be popped up
                                    // to confirm and remind the user that will also delete the shopping list.
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ListEdit.this);
                                    builder.setMessage("If you delete this item, it will also delete the shopping list.\nAre you sure to delete this item?").setCancelable(true)
                                            .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }

                                            })
                                            .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    editSPL.getList().clear();
                                                    for(ShoppingList temp :savedListSecondInListEdit.listOfShoppingList ){
                                                        if(temp.getId().equalsIgnoreCase(editSPL.getId())){
                                                            savedListSecondInListEdit.listOfShoppingList.remove(temp);
                                                            break;
                                                        }
                                                    }
                                                    Intent intentForDelete = new Intent(ListEdit.this,SavedListSecond.class);
                                                    startActivity(intentForDelete);
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();

                                }
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });


    }

    //to calculate the total price of the item list
    public double calculateListTotal(ArrayList<Item> list){
        double listTotal = 0;
        for (Item tempItem : list) {
            listTotal = listTotal + tempItem.getPrice();
        }
        BigDecimal bd = new BigDecimal(listTotal).setScale(2, RoundingMode.HALF_UP);
        listTotal = bd.doubleValue();

        return listTotal;
    }

    //to calculate how many supermarkets that users need to go
    public String calculateDirectionString(ArrayList<Item> list){
        String dir = "";
        for (Item temp : list){
            if (temp.getSupermarket().contains("Count")) {
                if (!dir.contains("Count")) {
                    dir = dir + ".CountDown";
                } else {
                }
            } else {
            }
            if (temp.getSupermarket().contains("Pak")) {
                if (!dir.contains("Pak")) {
                    dir = dir + ".PakNSave";
                } else {
                }
            } else {
            }
            if (temp.getSupermarket().contains("New")) {
                if (!dir.contains("New")) {
                    dir = dir + ".NewWorld";
                } else {
                }
            } else {
            }
        }
        return dir;
    }

    //creating menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //instantiate MenuInflater. This adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listedit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //activate the option
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        double total;
        String direction;

        //whenever the shopping list is changed, the total price and direction will be changed.
        if(id == R.id.app_bar_done){
            total=this.calculateListTotal(editSPL.getList());
            direction = this.calculateDirectionString(editSPL.getList());
            savedListSecondInListEdit = new SavedListSecond();
            for(ShoppingList temp :savedListSecondInListEdit.listOfShoppingList ){
                if(temp.getId().toLowerCase().equalsIgnoreCase(editSPL.getId().toLowerCase())){
                    temp.setTotal_price(total);
                    temp.setDirection(direction);
                }
            }
            Intent nextIntent = new Intent(ListEdit.this,SavedListSecond.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_back){
            total=this.calculateListTotal(editSPL.getList());
            direction = this.calculateDirectionString(editSPL.getList());
            savedListSecondInListEdit = new SavedListSecond();
            for(ShoppingList temp :savedListSecondInListEdit.listOfShoppingList ){
                if(temp.getId().toLowerCase().equalsIgnoreCase(editSPL.getId().toLowerCase())){
                    temp.setTotal_price(total);
                    temp.setDirection(direction);
                }
            }
            Intent nextIntent = new Intent(ListEdit.this,SavedListSecond.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_mainMenu){
            total=this.calculateListTotal(editSPL.getList());
            direction = this.calculateDirectionString(editSPL.getList());
            savedListSecondInListEdit = new SavedListSecond();
            for(ShoppingList temp :savedListSecondInListEdit.listOfShoppingList ){
                if(temp.getId().toLowerCase().equalsIgnoreCase(editSPL.getId().toLowerCase())){
                    temp.setTotal_price(total);
                    temp.setDirection(direction);
                }
            }
            Intent nextIntent = new Intent(ListEdit.this,MainActivity.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_add){
            Intent nextIntent = new Intent(ListEdit.this,AddForSaveList.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_edit_description){
            Intent editDIntent = new Intent(ListEdit.this,EditSaveListDescription.class);
            startActivity(editDIntent);
        }

        return super.onOptionsItemSelected(item);
    }

}
