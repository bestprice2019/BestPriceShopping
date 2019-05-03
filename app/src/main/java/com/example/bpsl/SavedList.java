package com.example.bpsl;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SavedList extends AppCompatActivity {

    private Button button;
    Database myDb;
    TextView dataList;
    String displayString = "Hi";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savelist);
//        Intent intent = new Intent(SavedList.this,SavedList.class);
//        startActivity(intent);
//        myDb.getAllData();
//        this.ViewAll();
        myDb = new Database(this);
        Cursor cursor = myDb.getAllData();
        if (cursor.getCount() == 0) {
//            showMessage("Error","No Data!");
//            dataList.setText("No Data");
            displayString = "No Data.";
            return;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()) {
                buffer.append("No:" + cursor.getString(0) + "\n");
                buffer.append("Title:" + cursor.getString(1) + "\n");
                buffer.append("Date:" + cursor.getString(2) + "\n");
                buffer.append("Time:" + cursor.getString(3) + "\n");
                buffer.append("\n");
            }

            displayString = buffer.toString();
        }


        button = (Button) findViewById(R.id.ToMain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavedList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        dataList = (TextView) findViewById(R.id.textViewSavedList);
        dataList.setText(displayString);

//        dataList.s
    }
}







