package com.example.gymproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class CalendarActivities extends AppCompatActivity
{
    private ListView listView;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        final CalendarView calendarView = findViewById(R.id.calendarView);
        listView = findViewById(R.id.list_item);

        int day = calendar.get(Calendar.DAY_OF_WEEK);
        activities(day);

//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position,
//                                    long id) {
//                Intent intent = new Intent(CalendarActivities.this, SendMessage.class);
//                String message = "abc";
//                intent.putExtra(EXTRA_MESSAGE, message);
//                startActivity(intent);
//            }
//        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                 calendar.set(year, month, dayOfMonth);
                 int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                 switch(dayOfWeek) {
                     case 1:
                         activities(1);
                         break;
                     case 2:
                         activities(2);
                         break;
                     case 3:
                         activities(3);
                         break;
                     case 4:
                         activities(4);
                         break;
                     case 5:
                         activities(5);
                         break;
                     case 6:
                         activities(6);
                         break;
                     case 7:
                         activities(7);
                         break;
                     }
                 }
        });
    }

    public void activities(int currDay){

        ArrayList<String> arrayList = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        String morning = " 08:00-10:00";
        String middle = " 10:00-12:00";
        String afternoon = " 16:00-18:00";
        String evening = " 18:00-20:00";
        String night = " 20:00-22:00";

        switch(currDay){
            case 1:
                Log.i("day","1");
                arrayList.add(this.getString(R.string.zumba_event) + morning);
                arrayAdapter.add(this.getString(R.string.aerobic_event) + evening);
                break;
            case 2:
                Log.i("day","2");
                arrayList.add(this.getString(R.string.TRX_event) + morning);
                arrayAdapter.add(this.getString(R.string.HIT_event) + evening);
                break;
            case 3:
                Log.i("day","3");
                arrayList.add(this.getString(R.string.yoga_event) + morning);
                arrayAdapter.add(this.getString(R.string.kick_box_event) + afternoon);
                break;
            case 4:
                Log.i("day","4");
                arrayList.add(this.getString(R.string.aerobic_event) + morning);
                arrayAdapter.add(this.getString(R.string.sculpting_event) + night);
                break;
            case 5:
                Log.i("day","5");
                arrayList.add(this.getString(R.string.workout_station_event) + evening);
                arrayAdapter.add(this.getString(R.string.zumba_event) + night);
                break;
            case 6:
                Log.i("day","6");
                arrayList.add(this.getString(R.string.TRX_event) + morning);
                arrayAdapter.add(this.getString(R.string.HIT_event) + middle);
                break;
            case 7:
                Log.i("day","7");
                arrayList.add(this.getString(R.string.no_activity_event));
                break;
        }
    }
}