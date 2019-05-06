package com.example.bpsl;


import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class NewListSearch extends AppCompatActivity {

    Database myDb;
    ListView listView;
    public static List<Item> cart = new ArrayList<Item>();

    public static List<Item> list = new ArrayList<Item>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlistsearch);


        InputStream in = getResources().openRawResource(R.raw.small);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(in, Charset.forName("UTF-8"))
        );

        String line ="";
        try{while((line = reader.readLine())!=null){
            String[] tokens = line.split(",");
            Item aItem = new Item(tokens[0],tokens[1],tokens[2]);
            list.add(aItem);
        }
        }catch (IOException e) {
            e.printStackTrace();
        }


        listView = (ListView) findViewById(R.id.LVSearch);
        ArrayAdapter<Item> adapter = new ArrayAdapter<>(this,R.layout.newlistsearch,R.id.textViewSearch,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)  {
                AlertDialog.Builder builder = new AlertDialog.Builder(NewListSearch.this);
                builder.setMessage("Do you want to put this item into the list?").setCancelable(false)
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        })
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cart.add(list.get(position));
                                AlertDialog.Builder builderTwo = new AlertDialog.Builder(NewListSearch.this);
                                builderTwo.setMessage("Do you still want to add your list?").setCancelable(false)
                                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(NewListSearch.this, NewListSave.class);
                                                startActivity(intent);
                                            }

                                        })
                                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        });
                                AlertDialog alertTwo = builderTwo.create();
                                alertTwo.show();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchText) {
                ArrayList<Item> tempList = new ArrayList<>();

                for(Item temp : list){
                    if(temp.getItem_name().toLowerCase().contains(searchText.toLowerCase())){
                        tempList.add(temp);
                    }
                }
                ArrayAdapter<Item> adapter = new ArrayAdapter<>(NewListSearch.this,R.layout.newlistsearch,R.id.textViewSearch,tempList);
                listView.setAdapter(adapter);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public List<Item> searchMachine(String searchText,List<Item> inputList){
        List<Item> searchList = new ArrayList<>();
        for(Item temp : inputList){
            if(temp.getItem_name().toLowerCase().contains(searchText.toLowerCase())){
                searchList.add(temp);
            }
        }


        return searchList;
    }


}
