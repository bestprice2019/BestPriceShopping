package com.example.bpsl;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


import java.util.Locale;
import java.util.TimeZone;


public class NewListDescription extends AppCompatActivity {

    TextView dateTV;
    TextView timeTV;
    Button buttonDate;
    Button buttonTime;
    Calendar c;
    DatePickerDialog dpd;
    TimePickerDialog tpd;
    Database myDb;
    public static EditText editTitle,editDate,editTime;
    public static String descriptionTitle;
    public static String descriptionDate;
    public static String descriptionTime;


    //When this class is activated, it instantiates all varibles and sets the layout
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


                c = Calendar.getInstance(TimeZone.getTimeZone("GMT+12"),
                        Locale.getDefault());;

                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(NewListDescription.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        int adjustMonth = month+1;
                        String setMonth = "";
                        String setDay = "";
                        if(dayOfMonth <10){
                            setDay = "0"+dayOfMonth;
                        }
                        else{
                            setDay = Integer.toString(dayOfMonth);
                        }

                        if(adjustMonth <10){
                            setMonth = "0"+month;
                        }
                        else{
                            setMonth = Integer.toString(adjustMonth);
                        }
                        dateTV.setText(setDay+"/"+setMonth+"/"+year);
                    }
                }, year, month, day);
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


                        if(hourOfDay >= 12){
                            AMPM = "PM";
                        }
                        else {
                            AMPM = "AM";
                        }
                        if(hourOfDay >= 13){
                            realHour = hourOfDay - 12;
                        }
                        if(hourOfDay == 0){
                            realHour = 12;
                        }
                        if(hourOfDay <13){
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
        Button buttonNextOfDescription = (Button) findViewById(R.id.nextOfDescription);
        buttonNextOfDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                descriptionTitle = editTitle.getText().toString();
//                Log.d("TAG",descriptionTitle);
                descriptionDate = editDate.getText().toString();
//                Log.d("TAG",descriptionDate);
                descriptionTime = editTime.getText().toString();
//                Log.d("TAG",descriptionTime);
                Intent intent = new Intent(v.getContext(),NewListSearch.class);
                startActivity(intent);

            }
        });
        editTitle.setText(descriptionTitle);
        editDate.setText(descriptionDate);
        editTime.setText(descriptionTime);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}