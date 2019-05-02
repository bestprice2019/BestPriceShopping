package com.example.bpsl;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper
{
    //Constants for db name and version
    private static final String DATABASE_NAME = "BPSL_Database.db";
    private static final int DATABASE_VERSION = 1;

    //Constants for identifying table and columns
    public static final String TABLE_NAME = "SHOPPING_LISTS";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "DATE";
    public static final String COL_4 = "TIME";

//    public static final String[] ALL_COLUMNS = {COL_2, COL_3, COL_4};

//        private static final String TABLE_CREATE =
//            "CREATE TABLE "+TABLE_NAME+" (TITLE STRING PRIMARY KEY AUTOINCREMENT,DATE STRING,TIME STRING)";

//
//    private String defaultData = "INSERT INTO "+TABLE_NAME+" ("+COL_2+","+ COL_3 +","+ COL_4 +")\n" +
//            "VALUES ('Sunday Party', '12/3/2020', '7:00PM')";
//
//    private String defaultDataTwo = "INSERT INTO "+TABLE_NAME+" ("+COL_2+","+ COL_3 +","+ COL_4 +")\n" +
//            "VALUES ('Weekend Party', '16/4/2020', '8:00PM')";

    public Database(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) //called first time DBOpenHelper class is instantiated
    {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE STRING,DATE STRING,TIME STRING)");
//        this.deleteData(db);
//        db.execSQL(defaultData);
//        db.execSQL(defaultDataTwo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) //Can rebuild database with new structure
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String date, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,time);
        long result = db.insert(TABLE_NAME,null ,contentValues);
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

    public void deleteData(SQLiteDatabase db){
        db.execSQL("DELETE FROM SHOPPING_LISTS");
    }


}
