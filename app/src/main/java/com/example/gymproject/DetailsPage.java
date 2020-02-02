package com.example.gymproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class DetailsPage extends AppCompatActivity {
    Button signOutGoogle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memeber_details_page);

        Button finishDetailsBtn = findViewById(R.id.finish_details_btn);
        signOutGoogle = findViewById(R.id.sign_out_google_btn);

        finishDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(DetailsPage.this, DetailsPage.this.getString(R.string.details_save), duration);
                toast.show();

                Intent intent = new Intent(DetailsPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Workout workout = new Workout("Workout 2", R.drawable.incline_bench_press);

        DataBase dataBase = DataBase.getInstance();

      //  dataBase.addWorkout(workout);

        Exercise exercise = new Exercise("Exercise 1",R.drawable.incline_bench_press);

        dataBase.addExercise(exercise,"Workout 1");
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




