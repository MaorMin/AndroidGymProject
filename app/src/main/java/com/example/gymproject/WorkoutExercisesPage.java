package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutExercisesPage extends AppCompatActivity {

    private static List<Exercise> exercises;
    private ImageView addExeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_exercises_page);

        addExeBtn = findViewById(R.id.add_workout_exe_btn);

        RecyclerView recyclerView = findViewById(R.id.recycler_workout_exercise);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager( new LinearLayoutManager( this));

        addExeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutExercisesPage.this, ExercisesPage.class);
                startActivity(intent);
            }
        });

        final WorkoutExerciseAdapter workoutExerciseAdapter = new WorkoutExerciseAdapter((exercises));

        workoutExerciseAdapter.setListener(new WorkoutExerciseAdapter.WorkoutExerciseListener() {


            @Override
            public void onCollapseClickListener(LinearLayout layout, ImageView arrowDown, ImageView arrowUp) {
                layout.setVisibility(View.GONE);
                arrowDown.setVisibility(View.VISIBLE);
                arrowUp.setVisibility(View.GONE);
            }

            @Override
            public void onExpendClickListener(LinearLayout layout, ImageView arrowDown, ImageView arrowUp) {

                layout.setVisibility(View.VISIBLE);
                arrowDown.setVisibility(View.GONE);
                arrowUp.setVisibility(View.VISIBLE);

            }

            @Override
            public void onWorkoutExerciseLongClicked(int position, View view, ImageView recycleBin) {

                if(recycleBin.getVisibility() == View.GONE){
                    recycleBin.setVisibility((View.VISIBLE));
                }
                else{
                    recycleBin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onDeleteListener(int position) {
                exercises.remove(position);
                workoutExerciseAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onClickListener(ImageView img) {
                if (img.getVisibility() == View.VISIBLE)
                    img.setVisibility(View.GONE);
            }

        });



        recyclerView.setAdapter(workoutExerciseAdapter);

    }

    public static void setExercisesList(List<Exercise> exerciseList){
        exercises = new ArrayList<>(exerciseList);
    }
}
