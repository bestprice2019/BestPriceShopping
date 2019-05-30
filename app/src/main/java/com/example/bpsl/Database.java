package com.example.bpsl;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper
{
    //Constants for db name and version
    private static final String DATABASE_NAME = "BPSL_Database.db";
    private static final int DATABASE_VERSION = 1;

    //Constants for identifying table and columns
    public static final String TABLE_NAME = "SHOPPING_LISTS";
    public static final String TABLE2_NAME = "ITEM";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "DATE";
    public static final String COL_4 = "TIME";
    public static final String COL_5 = "TOTAL_PRICE";
    public static final String COL_22 = "SUPERMARKET";
    public static final String COL_23 = "ITEM_NAME";
    public static final String COL_24 = "PRICE";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void readItemText(){


        try {
            BufferedReader in = new BufferedReader(
                    new FileReader("C:\\Users\\zawli\\AndroidStudioProjects\\BPSL\\ItemFinalNoSpaceForDatabase"));
            String line;

            while ((line = in.readLine())!= null) {
                String[] tokens=line.split(",");
                insertDataItemTable(tokens[0],tokens[1],tokens[2]);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) //called first time DBOpenHelper class is instantiated
    {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE STRING,DATE STRING,TIME STRING,TOTAL_PRICE DOUBLE)");
        db.execSQL("CREATE TABLE "+TABLE2_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,SUPERMARKET STRING,ITEM_NAME VARCHAR(100),PRICE STRING)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) //Can rebuild database with new structure
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        onCreate(db);
    }

    public boolean insertDataListTable(String title, String date, String time, Double total_price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,time);
        contentValues.put(COL_5,total_price);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public boolean insertDataItemTable(String supermarket, String item_name, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentItems = new ContentValues();
        contentItems.put(COL_22,supermarket);
        contentItems.put(COL_23,item_name);
        contentItems.put(COL_24,price);
        long result = db.insert(TABLE2_NAME,null ,contentItems);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }

    public Cursor getAllItemData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorTwo = db.rawQuery("select * from "+TABLE2_NAME,null);
        return cursorTwo;
    }

}
