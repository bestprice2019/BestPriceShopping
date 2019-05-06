package com.example.bpsl;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SavedList extends AppCompatActivity {

    private Button button;
    Database myDb;

    String displayString = "Hi";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savelist);
        myDb = new Database(this);
        Cursor cursor = myDb.getAllData();
        if (cursor.getCount() == 0) {
            displayString = "No Data.";
            return;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()) {
                buffer.append("No:" + cursor.getString(0) + "\n");
                buffer.append("Title:" + cursor.getString(1) + "\n");
                buffer.append("Date:" + cursor.getString(2) + "\n");
                buffer.append("Time:" + cursor.getString(3) + "\n");
                buffer.append("Total Price:"+ cursor.getString(4)+ "$\n");
                buffer.append("\n");
            }

            displayString = buffer.toString();
        }

        TextView textView = (TextView) findViewById(R.id.textViewSavedList);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(displayString);

        button =(Button)findViewById(R.id.ToMainMenu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}







