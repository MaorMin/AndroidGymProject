package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ExercisesPage extends AppCompatActivity {

    private Workout workout;
    private Button addBtn;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_page);
        dataBase = DataBase.getInstance();
        addBtn = findViewById(R.id.add_workout_top_btn);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int position = 0;
        if (bundle != null) {
            position = (int) bundle.get("position");
        }
        workout = MyWorkoutPage.workouts.get(position);



        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager( new LinearLayoutManager( this));


        final List<Exercise> exercises = new ArrayList<>();

        exercises.add(new Exercise("Bicep pull", R.drawable.bicsep_machine));
        exercises.add(new Exercise("Incline bench press", R.drawable.incline_bench_press));
        exercises.add(new Exercise("Bicep free", R.drawable.bicsep_standing));
        exercises.add(new Exercise("Tricep", R.drawable.tricep_parallel));
        exercises.add(new Exercise("Bicep pull", R.drawable.bicsep_machine));
        exercises.add(new Exercise("Incline bench press", R.drawable.incline_bench_press));
        exercises.add(new Exercise("Bicep free", R.drawable.bicsep_standing));
        exercises.add(new Exercise("Tricep", R.drawable.tricep_parallel));


        final int finalPosition = position;
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Exercise ex : exercises){
                    if (ex.isSelected()) { ;
                        workout.getExeList().add(ex);
                    }
                    dataBase.addExercise(workout.getExeList(), workout.getName());
                }
                WorkoutExercisesPage.setExercisesList(workout.getExeList());
                Intent intent = new Intent(ExercisesPage.this, WorkoutExercisesPage.class);
                startActivity(intent);
                intent.putExtra("position", finalPosition);
                finish();
            }
        });


        ExerciseAdapter exerciseAdapter = new ExerciseAdapter((exercises));

        exerciseAdapter.setLisener(new ExerciseAdapter.ExercisesListener() {
            @Override
            public void onCheckBoxListener(int position) {
                exercises.get(position).setSelected(true);
            }
        });
        recyclerView.setAdapter(exerciseAdapter);

    }
}
