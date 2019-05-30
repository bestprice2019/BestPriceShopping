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
import android.widget.Button;
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

    ListView listView;
    public static ArrayList<Item> cart = new ArrayList<>();

    ArrayList<Item> list = new ArrayList<>();

    ArrayList<Item> displayList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlistsearch);

        InputStream in = getResources().openRawResource(R.raw.itemfinal);
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
        ArrayAdapter<Item> adapter = new ArrayAdapter<>(this,R.layout.newlistsearch,R.id.textViewSearch,displayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)  {
                AlertDialog.Builder builder = new AlertDialog.Builder(NewListSearch.this);
                builder.setMessage("Do you want to put this item into the list?").setCancelable(true)
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        })
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cart.add(list.get(position));
//                                AlertDialog.Builder builderTwo = new AlertDialog.Builder(NewListSearch.this);
//                                builderTwo.setMessage("Do you still want to add your list?").setCancelable(false)
//                                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                Intent intent = new Intent(NewListSearch.this, NewListSave.class);
//                                                startActivity(intent);
//                                            }
//
//                                        })
//                                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//
//                                            }
//                                        });
//                                AlertDialog alertTwo = builderTwo.create();
//                                alertTwo.show();
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
        inflater.inflate(R.menu.menu_search, menu);

//        MenuItem nextButton = menu.findItem(R.id.app_bar_next);
//        nextButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                Intent intent = new Intent(NewListSearch.this, NewListSave.class);
//                startActivity(intent);
//                return false;
//            }
//
//
//        });

        //instantiate MenuItem and assign the item from the menu_search.xml
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        //instantiate SearchView, this will change the search list as the user enters letters
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        //set searchView to search item by putting a word or text.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //query's the current string as the user types in the search bar
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            //when the query test string changes
            @Override
            public boolean onQueryTextChange(String searchText) {
                //create the temporary list for searched items
                ArrayList<Item> tempList = new ArrayList<>();

                //search's the whole list and put each item into the temporary list.
                for(Item temp : list){
                    if(temp.getItem_name().toLowerCase().contains(searchText.toLowerCase())){
                        tempList.add(temp);
                    }
                }
                //instantiate the ArrayAdapter to show searched items in the list view.
                ArrayAdapter<Item> adapter = new ArrayAdapter<>(NewListSearch.this,R.layout.newlistsearch,R.id.textViewSearch,tempList);
                listView.setAdapter(adapter);
                return true;
            }
        });

        //refresh's the menu bar.
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.app_bar_next){
            Intent nextIntent = new Intent(NewListSearch.this,NewListSave.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_back){
            Intent nextIntent = new Intent(NewListSearch.this,NewListDescription.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_mainMenu){
            Intent nextIntent = new Intent(NewListSearch.this,MainActivity.class);
            startActivity(nextIntent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Item> searchMachine(String searchText, List<Item> inputList){
        List<Item> searchList = new ArrayList<>();
        for(Item temp : inputList){
            if(temp.getItem_name().toLowerCase().contains(searchText.toLowerCase())){
                searchList.add(temp);
            }
        }


        return searchList;
    }


}
