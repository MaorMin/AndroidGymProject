package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memeber_details_page);

        Button finishDetailsBtn = findViewById(R.id.finish_details_btn);

        finishDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = "Saved successfully!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(DetailsPage.this, text, duration);
                toast.show();

                Intent intent = new Intent(DetailsPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


}

