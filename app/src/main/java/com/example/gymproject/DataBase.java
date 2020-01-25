package com.example.gymproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class DataBase extends MainActivity {

    private static FirebaseAuth mAuth;


    String firstNameRegister;
    String lastNameRegister;
    String emailRegister;
    String passRegister;
    String rePassRegister;

    public DataBase(String firstNameRegister, String lastNameRegister, String emailRegister, String passRegister, String rePassRegister) {
        this.firstNameRegister = firstNameRegister;
        this.lastNameRegister = lastNameRegister;
        this.emailRegister = emailRegister;
        this.passRegister = passRegister;
        this.rePassRegister = rePassRegister;
    }

    private static FirebaseAuth getInstance() {

        if (mAuth == null) {
            mAuth = FirebaseAuth.getInstance();
        }
        return mAuth;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

        public void registerUserToDatabase() {
        mAuth=getInstance();

        mAuth.createUserWithEmailAndPassword(emailRegister, passRegister)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    User user = new User(firstNameRegister, lastNameRegister, emailRegister, passRegister, rePassRegister);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(DataBase.this, getString(R.string.successful_register), Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(DataBase.this, DetailsPage.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });

                                } else {

                                    Toast.makeText(DataBase.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                }
                            }
                        });
    }
    }
