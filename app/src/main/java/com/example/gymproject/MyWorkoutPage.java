package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import static com.facebook.AccessTokenManager.TAG;


public class MyWorkoutPage extends AppCompatActivity {

    private List<Exercise> exercises;
    DataBase dataBase;
    ImageView addWorkoutBtn;
    final static List<Workout> workouts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout_page);

        dataBase = DataBase.getInstance();
        addWorkoutBtn = findViewById(R.id.add_workout_top_btn);

        RecyclerView recyclerView = findViewById(R.id.recycler_workout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager( this));


        addWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyWorkoutPage.this, PopupAddWorkout.class));
            }
        });



        final MyWorkoutAdapter myWorkoutAdapter = new MyWorkoutAdapter((workouts));
        dataBase.getWorkouts(workouts, myWorkoutAdapter);

//        Workout workout = new Workout("demo", R.drawable.a_img);
//        List<Exercise> exlist = new ArrayList<Exercise>();
//        exlist.add(new Exercise("Bicep pull", R.drawable.bicsep_machine));
//        exlist.add(new Exercise("Incline bench press", R.drawable.incline_bench_press));
//        exlist.add(new Exercise("Bicep free", R.drawable.bicsep_standing));
//        workout.setExeList(exlist);
//
//        dataBase.addWorkout(workout);



        myWorkoutAdapter.setListener(new MyWorkoutAdapter.MyWorkoutListener() {
            @Override
            public void onClickListener(int position) {
                WorkoutExercisesPage.setExercisesList(workouts.get(position).getExeList());
                Intent intent = new Intent(MyWorkoutPage.this, WorkoutExercisesPage.class);
                startActivity(intent);
                intent.putExtra("position", position);
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
                String name = workouts.get(position).getName();
                workouts.remove(position);
                myWorkoutAdapter.notifyItemRemoved(position);
                dataBase.removeWorkout(name);
                }
        });

        recyclerView.setAdapter(myWorkoutAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(MyWorkoutPage.this,MainMenu.class);
        startActivity(setIntent);
        finish();
    }

}
