package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {

    private LinearLayout myWorkoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


//
////        RecyclerView recyclerView = findViewById(R.id.recycler);
////        recyclerView.setHasFixedSize(true);
////
////        recyclerView.setLayoutManager( new GridLayoutManager( this, 2));
////
////
////        List<CardMainMenu> cardMainMenuList = new ArrayList<>();
////
////        cardMainMenuList.add(new CardMainMenu("My Workout" , R.drawable.my_workouts_icon));
////        cardMainMenuList.add(new CardMainMenu("Weekly Activates" , R.drawable.weekly_activitys_icon));
////        cardMainMenuList.add(new CardMainMenu("Update My Details" , R.drawable.update_details_icon));
////        cardMainMenuList.add(new CardMainMenu("Preferences" , R.drawable.preferences_icon));
////
////
////        CardMainMenuAdapter CardMainMenuAdapter = new  CardMainMenuAdapter((cardMainMenuList));
////
////        recyclerView.setAdapter(CardMainMenuAdapter);

        myWorkoutBtn = findViewById(R.id.my_workout_btn);

        myWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MyWorkoutPage.class );
                startActivity(intent);
            }
        });
    }
}
