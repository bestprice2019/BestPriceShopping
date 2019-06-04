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


import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class allows the user edit the shopping list before it is saved.
 */
public class NewListEdit extends AppCompatActivity {

    ListView listView;
    NewListSearch newListSearchForNewListEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listedit);
        newListSearchForNewListEdit = new NewListSearch();
        listView = (ListView) findViewById(R.id.LVEdit);
        ArrayAdapter<Item> adapter = new ArrayAdapter<>(this,R.layout.listedit,R.id.TVEdit,newListSearchForNewListEdit.cart);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)  {
                //When the item is clicked, alert dialog will be popped up to delete items.
                AlertDialog.Builder builder = new AlertDialog.Builder(NewListEdit.this);
                builder.setMessage("Do you want to delete this item from the list?").setCancelable(true)
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        })
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(newListSearchForNewListEdit.cart.size()>1) {
                                    newListSearchForNewListEdit.cart.remove(position);
                                    ArrayAdapter<Item> adapterTwo = new ArrayAdapter<>(NewListEdit.this, R.layout.listedit, R.id.TVEdit, newListSearchForNewListEdit.cart);
                                    listView.setAdapter(adapterTwo);
                                }
                                else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(NewListEdit.this);
                                    builder.setMessage("If you delete this item, it will also delete the shopping list.\nAre you sure to delete this item?").setCancelable(true)
                                            .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }

                                            })
                                            .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    newListSearchForNewListEdit.cart.clear();
                                                    Intent intentForDelete = new Intent(NewListEdit.this,NewListDescription.class);
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


    //running menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //instantiate MenuInflater. This adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listedit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.app_bar_done){

            Intent nextIntent = new Intent(NewListEdit.this,NewListSave.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_back){

            Intent nextIntent = new Intent(NewListEdit.this,NewListSave.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_mainMenu){
            Intent nextIntent = new Intent(NewListEdit.this,MainActivity.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_add){
            Intent nextIntent = new Intent(NewListEdit.this,NewListSearch.class);
            startActivity(nextIntent);
            return false;
        }

        return super.onOptionsItemSelected(item);
    }

}
