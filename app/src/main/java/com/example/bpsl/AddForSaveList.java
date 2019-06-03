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
import java.util.ArrayList;


public class AddForSaveList extends AppCompatActivity {

    ListView listView;
    ArrayList<Item> tempListASL = new ArrayList<>();
    ArrayList<Item> displayList = new ArrayList<>();
    ListEdit listEditForAddSaveList = new ListEdit();
    MainActivity mainForAddSaveList = new MainActivity();
    SavedListSecond savedListSecondInAddSL = new SavedListSecond();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlistsearch);

        //When the item is clicked, alter dialog will be popped up to add the items.
        listView = (ListView) findViewById(R.id.LVSearch);
        ArrayAdapter<Item> adapter = new ArrayAdapter<>(this,R.layout.newlistsearch,R.id.textViewSearch,displayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)  {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddForSaveList.this);
                builder.setMessage("Do you want to put this item into the list?").setCancelable(true)
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        })
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listEditForAddSaveList.editSPL.getList().add(tempListASL.get(position));


                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }



    //creating menu bar for this class
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //instantiate MenuInflater. This adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);

        //instantiate searchItem to search the items.
        MenuItem searchItem = menu.findItem(R.id.app_add_search);
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

                tempListASL.clear();
                //search's the whole list and put each item into the temporary list.
                for(Item temp : mainForAddSaveList.mainList){
                    if(temp.getItem_name().toLowerCase().contains(searchText.toLowerCase())){
                        tempListASL.add(temp);
                    }
                }
                //instantiate the ArrayAdapter to show searched items in the list view.
                ArrayAdapter<Item> adapter = new ArrayAdapter<>(AddForSaveList.this,R.layout.newlistsearch,R.id.textViewSearch,tempListASL);
                listView.setAdapter(adapter);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    //This method is to click the item in the option and to activate their activities.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        double total;
        String direction;

        //whenever they change item list, total price and direction will be changed
        if(id == R.id.app_bar_done_adding){
            total=listEditForAddSaveList.calculateListTotal(listEditForAddSaveList.editSPL.getList());
            direction = listEditForAddSaveList.calculateDirectionString(listEditForAddSaveList.editSPL.getList());
            for(ShoppingList temp :savedListSecondInAddSL.listOfShoppingList ){
                if(temp.getId().toLowerCase().equalsIgnoreCase(listEditForAddSaveList.editSPL.getId().toLowerCase())){
                    temp.setTotal_price(total);
                    temp.setDirection(direction);
                }
            }
            Intent nextIntent = new Intent(AddForSaveList.this,ListEdit.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_back){
            total=listEditForAddSaveList.calculateListTotal(listEditForAddSaveList.editSPL.getList());
            direction = listEditForAddSaveList.calculateDirectionString(listEditForAddSaveList.editSPL.getList());
            for(ShoppingList temp :savedListSecondInAddSL.listOfShoppingList ){
                if(temp.getId().toLowerCase().equalsIgnoreCase(listEditForAddSaveList.editSPL.getId().toLowerCase())){
                    temp.setTotal_price(total);
                    temp.setDirection(direction);
                }
            }
            Intent nextIntent = new Intent(AddForSaveList.this,ListEdit.class);
            startActivity(nextIntent);
            return false;
        }
        if(id == R.id.app_bar_mainMenu){
            total=listEditForAddSaveList.calculateListTotal(listEditForAddSaveList.editSPL.getList());
            direction = listEditForAddSaveList.calculateDirectionString(listEditForAddSaveList.editSPL.getList());
            for(ShoppingList temp :savedListSecondInAddSL.listOfShoppingList ){
                if(temp.getId().toLowerCase().equalsIgnoreCase(listEditForAddSaveList.editSPL.getId().toLowerCase())){
                    temp.setTotal_price(total);
                    temp.setDirection(direction);
                }
            }
            Intent nextIntent = new Intent(AddForSaveList.this,MainActivity.class);
            startActivity(nextIntent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }




}
