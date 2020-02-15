package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public class MyWorkoutPage extends AppCompatActivity {

    private List<Exercise> exercises;
    ImageView addWorkoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout_page);

        addWorkoutBtn = findViewById(R.id.add_workout_top_btn);


        RecyclerView recyclerView = findViewById(R.id.recycler_workout);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager( new LinearLayoutManager( this));


        final List<Workout> workouts = new ArrayList<>();

        workouts.add(new Workout("Sunday",  R.drawable.incline_bench_press));
        workouts.add(new Workout("Monday",  R.drawable.incline_bench_press));
        workouts.add(new Workout("Tuesday", R.drawable.incline_bench_press));
        workouts.add(new Workout("Sunday",  R.drawable.incline_bench_press));
        workouts.add(new Workout("Sunday",  R.drawable.incline_bench_press));
        workouts.add(new Workout("Sunday",  R.drawable.incline_bench_press));


        exercises = new ArrayList<>();


        exercises.add(new Exercise("Bicep pull", R.drawable.bicsep_machine));
        exercises.add(new Exercise("Bicep pull", R.drawable.bicsep_machine));
        exercises.add(new Exercise("Bicep pull", R.drawable.bicsep_machine));
        exercises.add(new Exercise("Bicep pull", R.drawable.bicsep_machine));
        exercises.add(new Exercise("Bicep pull", R.drawable.bicsep_machine));



        addWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyWorkoutPage.this, PopupAddWorkout.class));
            }
        });

        final MyWorkoutAdapter myWorkoutAdapter = new MyWorkoutAdapter((workouts));

        myWorkoutAdapter.setListener(new MyWorkoutAdapter.MyWorkoutListener() {
            @Override
            public void onClickListener(int position) {
                WorkoutExercisesPage.setExercisesList(exercises);
                Intent intent = new Intent(MyWorkoutPage.this, WorkoutExercisesPage.class);
                startActivity(intent);
            }

            @Override
            public void onWorkoutLongClicked(int position, View view, ImageView recycleBin) {

                if (recycleBin.getVisibility() == View.GONE) {
                    recycleBin.setVisibility((View.VISIBLE));
                } else {
                    recycleBin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onDeleteListener(int position) {
                    workouts.remove(position);
                    myWorkoutAdapter.notifyItemRemoved(position);

                }


        });

        recyclerView.setAdapter(myWorkoutAdapter);
    }

}
