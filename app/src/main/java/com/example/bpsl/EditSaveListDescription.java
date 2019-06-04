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

public class EditSaveListDescription extends AppCompatActivity {

    TextView dateTVSL;
    TextView timeTVSL;
    Calendar c;
    DatePickerDialog dpd;
    TimePickerDialog tpd;
    EditText editTitleSL,editDateSL,editTimeSL;
    ListEdit listEditInDescriptionEdit;

    //When this class is activated, it instantiates all the text views, all the edit texts, calendar, date and time pick dialogs.
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout
        setContentView(R.layout.editsavelistdescription);
        listEditInDescriptionEdit = new ListEdit();
        editTitleSL = (EditText) findViewById(R.id.editTitleSL);
        editTitleSL.setText(listEditInDescriptionEdit.editSPL.getTitle());
        editDateSL = (EditText) findViewById(R.id.editDateSL);
        editDateSL.setText(listEditInDescriptionEdit.editSPL.getDate());
        editTimeSL = (EditText) findViewById(R.id.editTimeSL);
        editTimeSL.setText(listEditInDescriptionEdit.editSPL.getTime());



        dateTVSL = (TextView) findViewById(R.id.editDateSL);
        Button buttonDateSL = (Button) findViewById(R.id.chooseDateSL);
        buttonDateSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                c = Calendar.getInstance(TimeZone.getTimeZone("GMT+12"),
                        Locale.getDefault());;

                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(EditSaveListDescription.this, new DatePickerDialog.OnDateSetListener() {

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
                        dateTVSL.setText(setDay+"/"+setMonth+"/"+year);
                    }
                }, year, month, day);
                dpd.show();
            }
        });

        timeTVSL = (TextView) findViewById(R.id.editTimeSL);
        Button buttonTime = (Button) findViewById(R.id.chooseTimeSL);
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                final int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);

                tpd = new TimePickerDialog(EditSaveListDescription.this, new TimePickerDialog.OnTimeSetListener() {
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
                        timeTVSL.setText(realHour+":"+realMin+" "+AMPM);
                    }
                },hour,minute,false);
                tpd.show();
            }
        });


        //function of "Done Edit" button.
        Button button = (Button) findViewById(R.id.DoneEditSL);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTitleSL.getText().toString();
                String date = editDateSL.getText().toString();
                String time = editTimeSL.getText().toString();
                listEditInDescriptionEdit.editSPL.setTitle(title);
                listEditInDescriptionEdit.editSPL.setDate(date);
                listEditInDescriptionEdit.editSPL.setTime(time);
                Intent intent = new Intent(EditSaveListDescription.this,ListEdit.class);
                startActivity(intent);

            }
        });
    }

    //set menu layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
