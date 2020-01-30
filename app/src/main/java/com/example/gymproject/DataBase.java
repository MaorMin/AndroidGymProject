package com.example.gymproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.facebook.AccessTokenManager.TAG;


public class DataBase {

    private FirebaseAuth mAuth;
    private static DataBase instance;
    private boolean flag;

    public DataBase() {
        mAuth = FirebaseAuth.getInstance();
    }

    private static DataBase getInstance() {

        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }



    public void successRegisterSet(boolean flag) {
        String b = "0";
        if(flag) {
            b = "1";
            Log.d("Flag in Set is:", b);
        }
        this.flag = flag;
    }

    public boolean successRegisterGet() {

        String a = "0";
        if(flag) {
            a = "1";
            Log.d("Flag in Get is:", a);
        }
        else
        {
            a = "0";
            Log.d("Flag in Get is:", a);
        }
        return flag;
    }

    public boolean registerUserToDatabase(final String firstNameRegister, final String lastNameRegister, final String emailRegister, final String passRegister, final String rePassRegister) {
        instance = getInstance();
        instance.mAuth.createUserWithEmailAndPassword(emailRegister, passRegister)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            User user = new User(firstNameRegister, lastNameRegister, emailRegister, passRegister, rePassRegister);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //  Toast.makeText(DataBase.this, getString(R.string.successful_register), Toast.LENGTH_LONG).show();
                                        //  Intent intent = new Intent(DataBase.this, DetailsPage.class);
                                        //  startActivity(intent);
                                        successRegisterSet(true);
                                    }
                                }

                            });

                        } else if (!task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail Failed:onComplete: " + task.getException().getMessage());
                            // Toast toast = Toast.makeText(DetailsPage.this, s, Toast.LENGTH_LONG).show();
                        }
                    }


              });
        if(!flag) {
            Log.d("******flag is:", "0");
        }
            else {
            Log.d("******flag is:", "1");
        }

        return successRegisterGet();
    }
}



