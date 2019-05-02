package com.example.bpsl;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class NewListDescription extends AppCompatActivity {

    TextView dateTV;
    TextView timeTV;
    Button buttonDate;
    Button buttonTime;
    Button buttonNext;
    Calendar c;
    DatePickerDialog dpd;
    TimePickerDialog tpd;
    Database myDb;
    EditText editTitle,editDate,editTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlistdescription);
        myDb = new Database(this);

        dateTV = (TextView) findViewById(R.id.editDate);
        buttonDate = (Button) findViewById(R.id.chooseDate);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(NewListDescription.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateTV.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                }, 2019, 4, 3);
                dpd.show();
            }
        });

        timeTV = (TextView) findViewById(R.id.editTime);
        buttonTime = (Button) findViewById(R.id.chooseTime);
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                final int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);

                tpd = new TimePickerDialog(NewListDescription.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String AMPM = "AM";
                        int realHour = 0;
                        String realMin = "";
                        if(hourOfDay > 11){
                            AMPM = "PM";
                        }
                        else {

                        }
                        if(hourOfDay > 12){
                            realHour = hourOfDay - 12;
                        }
                        else {
                            realHour = hourOfDay;
                        }
                        if(minute < 10){
                            realMin = "0"+minute;
                        }
                        else {
                            realMin = minute+"";
                        }
                        timeTV.setText(realHour+":"+realMin+" "+AMPM);
                    }
                },hour,minute,false);
                tpd.show();
            }
        });

        editTitle = (EditText) findViewById(R.id.editTitle);
        editDate = (EditText) findViewById(R.id.editDate);
        editTime = (EditText) findViewById(R.id.editTime);

        Button button = (Button) findViewById(R.id.nextOfDescription);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setContentView(R.layout.newlistsearch);
                Intent intent = new Intent(v.getContext(),NewListSearch.class);
                startActivity(intent);
                boolean inserting = myDb.insertData(editTitle.getText().toString(),
                        editDate.getText().toString(),
                        editTime.getText().toString());
                if(inserting = true)
                    Toast.makeText(NewListDescription.this,"Data Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(NewListDescription.this,"Data Inserted Error",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
