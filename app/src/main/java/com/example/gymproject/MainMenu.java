package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager( new GridLayoutManager( this, 2));


        List<CardMainMenu> cardMainMenuList = new ArrayList<>();

        cardMainMenuList.add(new CardMainMenu("My Workout" , R.drawable.my_workouts_icon));
        cardMainMenuList.add(new CardMainMenu("Weekly Activates" , R.drawable.weekly_activitys_icon));
        cardMainMenuList.add(new CardMainMenu("Update My Details" , R.drawable.update_details_icon));
        cardMainMenuList.add(new CardMainMenu("Preferences" , R.drawable.preferences_icon));



        CardMainMenuAdapter CardMainMenuAdapter = new  CardMainMenuAdapter((cardMainMenuList));

        recyclerView.setAdapter(CardMainMenuAdapter);

    }
}
