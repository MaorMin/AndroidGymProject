package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);


        EditText height = findViewById(R.id.height);
        EditText age = findViewById(R.id.age);
        EditText weight = findViewById(R.id.weight);
        EditText percentBodyFat = findViewById(R.id.percent_body_fat);
        Button finishNewDetailsBtn = findViewById(R.id.finish_new_details_btn);

        finishNewDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });
    }
}
