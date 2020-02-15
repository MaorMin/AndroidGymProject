package com.example.gymproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class DetailsPage extends AppCompatActivity {
    Button signOutGoogle;

    private ImageButton femaleGenderBtn;
    private ImageButton maleGenderBtn;
    private EditText editTextHeight;
    private EditText editTextWeight;
    private EditText editTextAge;
    private EditText editTextFatPercent;
    private DataBase dataBase;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memeber_details_page);

        editTextHeight = findViewById(R.id.height);
        editTextWeight = findViewById(R.id.weight);
        editTextAge = findViewById(R.id.age);
        editTextFatPercent = findViewById(R.id.percent_body_fat);
        femaleGenderBtn = findViewById(R.id.femaleBtn);
        maleGenderBtn = findViewById(R.id.maleBtn);
        dataBase = DataBase.getInstance();
        progressBar = findViewById(R.id.progress_bar_details_page);
        Button finishDetailsBtn = findViewById(R.id.finish_details_btn);
      //  signOutGoogle = findViewById(R.id.sign_out_google_btn);

        progressBar.setVisibility(View.GONE);

        finishDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                updateDetails();

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(DetailsPage.this, DetailsPage.this.getString(R.string.details_save), duration);
                toast.show();
            }
        });


        femaleGenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            toString();
            }
        });

        //Workout workout = new Workout("Workout 2", R.drawable.incline_bench_press);

    //    DataBase dataBase = DataBase.getInstance();

        //  dataBase.addWorkout(workout);

    //    Exercise exercise = new Exercise("Exercise 1", R.drawable.incline_bench_press);

    //    dataBase.addExercise(exercise, "Workout 1");


    }

   public void updateDetails() {
        double height = Double.parseDouble(editTextHeight.getText().toString().trim());
       double weight = Double.parseDouble(editTextWeight.getText().toString().trim());
       double age = Double.parseDouble(editTextAge.getText().toString().trim());
       double fatPercent = Double.parseDouble(editTextFatPercent.getText().toString().trim());

       MyDetails myDetails = new MyDetails(height,weight,age,fatPercent);
       dataBase.updateMyDetails(myDetails,this);
        }

    }



//        signOutGoogle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mGoogleSignInClient.signOut()
//                        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                            }
//                            //FirebaseAuth.getInstance().signOut();
//                            // Intent intent = new Intent(DetailsPage.this,MainActivity.class);
//                            //   startActivity(intent);
//                        });


//        private void revokeAccess() {
//            mGoogleSignInClient.revokeAccess()
//                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            // ...
//                        }
//                    });
//        }



//
//        });
//    }
//}




