package com.example.gymproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class Calendar extends AppCompatActivity {



    private ListView listView;
    private CalendarView calendarView;
    public GregorianCalendar cal_month;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        listView = (ListView)findViewById(R.id.list_item);



        ArrayList<String> arrayList = new ArrayList<>();


        arrayList.add("Strength Exercise: 10:00-11:00 " );
        arrayList.add("Aerobic Exercise: 11:00-12:30");



        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

    }
}