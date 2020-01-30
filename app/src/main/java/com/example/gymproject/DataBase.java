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


    private DataBase() {
        mAuth = FirebaseAuth.getInstance();
    }

    public static DataBase getInstance() {

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



    public void registerUserToDatabase(final String firstNameRegister, final String lastNameRegister, final String emailRegister, final String passRegister, final String rePassRegister,final registerPage page) {

        this.mAuth.createUserWithEmailAndPassword(emailRegister, passRegister)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            User user = new User(firstNameRegister, lastNameRegister, emailRegister, passRegister, rePassRegister);
                            DatabaseReference r = FirebaseDatabase.getInstance().getReference("Users");
                            r.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                page.progressBar.setVisibility(View.GONE);
                                                Toast.makeText(page, page.getString(R.string.successful_register), Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(page, DetailsPage.class);
                                                page.startActivity(intent);
                                            }
                                            else{
                                                Toast.makeText(page ,"not succeeded" ,Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
//                            successRegisterSet(true);
//                            if (!flag) {
//                                Log.d("******flag is:", "0");
//                            } else {
//                                Log.d("******flag is:", "1");
//                            }
                        }
                    }
                });

//                        return successRegisterGet();

    }
}




