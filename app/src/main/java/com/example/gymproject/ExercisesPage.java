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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final List<Exercise> exercises = new ArrayList<>();

        exercises.add(new Exercise("@Breeding_dumbbells_lying", R.drawable.breeding_dumbbells_lying));
        exercises.add(new Exercise("@climbs_the_trunk", R.drawable.climbs_the_trunk));
        exercises.add(new Exercise("Concentrated bending arms", R.drawable.concentrated_bending_arms));
        exercises.add(new Exercise("dumbbell hammer curls", R.drawable.curl_with_a_dumbbell));
        exercises.add(new Exercise("Curls on the bench", R.drawable.curls_on_the_bench));
        exercises.add(new Exercise("Dips", R.drawable.dips));
        exercises.add(new Exercise("Dumbbell bench press", R.drawable.dumbbell_bench_press));
        exercises.add(new Exercise("Bent-over dumbbell rows", R.drawable.end_dumbbell));
        exercises.add(new Exercise("Lat Pulldowens", R.drawable.end_of_the_upper_block_in_front_of_him));
        exercises.add(new Exercise("End of the upper block the neck", R.drawable.end_of_the_upper_block_the_neck));
        exercises.add(new Exercise("Extension arms with a dumbbell", R.drawable.extension_arms_with_a_dumbbell));
        exercises.add(new Exercise("Tricep push down", R.drawable.extension_of_hands));
        exercises.add(new Exercise("Flexion simulator leg", R.drawable.flexion_simulator_leg));
        exercises.add(new Exercise("Bicep free", R.drawable.inclined_leg));
        exercises.add(new Exercise("Leg extension", R.drawable.leg_extension));
        exercises.add(new Exercise("Lunges with dumbbells", R.drawable.lunges_with_dumbbells));
        exercises.add(new Exercise("Middle cable fly", R.drawable.middle_cable_fly));
        exercises.add(new Exercise("Bench press", R.drawable.press_of_a_bar));
        exercises.add(new Exercise("Pull ups", R.drawable.pulling_up_wide_grip_hand));
        exercises.add(new Exercise("pull_on_deltoids", R.drawable.pull_on_deltoids));
        exercises.add(new Exercise("Reduction of arms", R.drawable.reduction_of_arms));
        exercises.add(new Exercise("Run", R.drawable.run));
        exercises.add(new Exercise("seated chests", R.drawable.seated_chests));
        exercises.add(new Exercise("Standing calf raises", R.drawable.standing_calf_raises));
        exercises.add(new Exercise("Straightening simulator wheel", R.drawable.straightening_simulator_wheel));


        final int finalPosition = position;
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Exercise ex : exercises) {
                    if (ex.isSelected()) {
                        ;
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
