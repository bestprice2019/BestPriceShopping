package com.example.bpsl;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class SavedListSecond extends AppCompatActivity {

    ListView listView;
    public static ArrayList<ShoppingList> listOfShoppingList = new ArrayList<>();
    ListEdit listEditInSaveList;
//    public static int stopperSLS = 1;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savelistsecond);
//        if(stopperSLS ==1) {
//            ArrayList<Item> defaultCartOne = new ArrayList<>();
//            ArrayList<Item> defaultCartTwo = new ArrayList<>();
//
//            defaultCartOne.add(new Item("DefSM", "Defitem1", 7.5));
//            defaultCartOne.add(new Item("DefSM2", "Defitem2", 4.5));
//            defaultCartOne.add(new Item("DefSM3", "Defitem3", 8.5));
//
//            defaultCartTwo.add(new Item("DefSM", "Defitem1", 7.5));
//            defaultCartTwo.add(new Item("DefSM2", "Defitem2", 4.5));
//            defaultCartTwo.add(new Item("DefSM3", "Defitem3", 8.5));
//
//            listOfShoppingList.add(new ShoppingList("defid", "deTitle", "dDate", "dTime", 20.5, defaultCartOne, "location"));
//            listOfShoppingList.add(new ShoppingList("defid2", "deTitle2", "dDate2", "dTime2", 20.5, defaultCartTwo, "location2"));
//        }
//        else{
//
//        }
//        stopperSLS++;


        listView = findViewById(R.id.LVSaveListSecond);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.savelistsecond,R.id.TVSaveListSecond,listOfShoppingList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)  {
                AlertDialog.Builder builder = new AlertDialog.Builder(SavedListSecond.this);
                builder.setMessage("Would you like to edit this shopping list?").setCancelable(true)
                        .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listEditInSaveList = new ListEdit();
                                listEditInSaveList.editSPL=listOfShoppingList.get(position);
                                Intent editListIntent = new Intent(SavedListSecond.this,ListEdit.class);
                                startActivity(editListIntent);
                            }

                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SavedListSecond.this);
                                builder.setMessage("Are you sure to delete this shopping list?").setCancelable(true)
                                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }

                                        })
                                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                listOfShoppingList.remove(position);
                                                ArrayAdapter<ShoppingList> adapterThree = new ArrayAdapter<>(SavedListSecond.this, R.layout.savelistsecond, R.id.TVSaveListSecond, listOfShoppingList);
                                                listView.setAdapter(adapterThree);
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_savelist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.app_bar_done){
            Intent nextIntent = new Intent(SavedListSecond.this,MainActivity.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_back){
            Intent nextIntent = new Intent(SavedListSecond.this,MainActivity.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_mainMenu){
            Intent nextIntent = new Intent(SavedListSecond.this,MainActivity.class);
            startActivity(nextIntent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
