package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MyWorkoutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout_page);


        RecyclerView recyclerView = findViewById(R.id.recycler_workout);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager( new LinearLayoutManager( this));


        List<Workout> workouts = new ArrayList<>();

        workouts.add(new Workout("Sunday",  R.drawable.incline_bench_press));
        workouts.add(new Workout("Monday",  R.drawable.incline_bench_press));
        workouts.add(new Workout("Tuesday", R.drawable.incline_bench_press));
        workouts.add(new Workout("Sunday",  R.drawable.incline_bench_press));
        workouts.add(new Workout("Sunday",  R.drawable.incline_bench_press));
        workouts.add(new Workout("Sunday",  R.drawable.incline_bench_press));



        MyWorkoutAdapter myWorkoutAdapter = new MyWorkoutAdapter((workouts));

        recyclerView.setAdapter(myWorkoutAdapter);
    }
}
