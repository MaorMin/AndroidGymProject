package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ExercisesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_page);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager( new LinearLayoutManager( this));


        List<Exercise> exercises = new ArrayList<>();

        exercises.add(new Exercise("Bicep pull", R.drawable.bicsep_machine));
        exercises.add(new Exercise("Incline bench press", R.drawable.incline_bench_press));
        exercises.add(new Exercise("Bicep free", R.drawable.bicsep_standing));
        exercises.add(new Exercise("Tricep", R.drawable.tricep_parallel));

        exercises.add(new Exercise("Bicep pull", R.drawable.bicsep_machine));
        exercises.add(new Exercise("Incline bench press", R.drawable.incline_bench_press));
        exercises.add(new Exercise("Bicep free", R.drawable.bicsep_standing));
        exercises.add(new Exercise("Tricep", R.drawable.tricep_parallel));


        ExerciseAdapter exerciseAdapter = new ExerciseAdapter((exercises));

        recyclerView.setAdapter(exerciseAdapter);

    }
}
