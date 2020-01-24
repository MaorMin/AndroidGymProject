package com.example.gymproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;



public class registerPage extends MainActivity{

    EditText pass;
    EditText repPass;


    private EditText editTextfirstNameRegister;
    private EditText editTextlastNameRegister;
    private EditText editTextEmailRegister;
    private EditText editTextPassRegister;
    private EditText editTextRepPasswordRegister;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        editTextfirstNameRegister = findViewById(R.id.first_name_register);
        editTextlastNameRegister = findViewById(R.id.last_name_register);
        editTextEmailRegister = findViewById(R.id.email_register);
        editTextPassRegister =  findViewById(R.id.pass_register);
        editTextRepPasswordRegister = findViewById(R.id.rep_pass_register);

        Button registerBtnEnd = findViewById(R.id.reg_btn_end);


        registerBtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             //   CharSequence text = "Your register has complete successfully!";
              //  int duration = Toast.LENGTH_SHORT;
             //   Toast toast = Toast.makeText(registerPage.this, text, duration);
              //  toast.show();
                registerUser();
                //Intent intent = new Intent(registerPage.this, MainActivity.class);
                //startActivity(intent);
            }
        });

        Button clickHereBtn = findViewById(R.id.clickHereDetails_btn);

        clickHereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registerPage.this, MainActivity.class);
                startActivity(intent);
            }
        });



        pass = findViewById(R.id.pass_register);
        repPass = findViewById(R.id.rep_pass_register);
        lngCheck();
    }



    public void lngCheck(){
        String lng = Locale.getDefault().getDisplayLanguage();

        if (lng.equals("English")){
            pass.setGravity(GravityCompat.START);
            repPass.setGravity(GravityCompat.START);
        }
        else{
            pass.setGravity(GravityCompat.END);
            repPass.setGravity(GravityCompat.END);
        }
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

    public void registerUser() {
        final String email = editTextEmailRegister.getText().toString().trim();
        final String pass = editTextPassRegister.getText().toString().trim();
        final String firstName = editTextfirstNameRegister.getText().toString().trim();
        final String lastName = editTextlastNameRegister.getText().toString().trim();
        final String rePass = editTextRepPasswordRegister.getText().toString().trim();



        if(firstName.isEmpty()){
            editTextEmailRegister.setError("First name required");
            editTextEmailRegister.requestFocus();
            return;
        }
        if(lastName.isEmpty()){
            editTextEmailRegister.setError("Last name required");
            editTextEmailRegister.requestFocus();
            return;
        }


        if (email.isEmpty()) {
            editTextEmailRegister.setError("Email required");
            editTextEmailRegister.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmailRegister.setError("Enter a valid email");
            editTextEmailRegister.requestFocus();
            return;
        }
        if (pass.length() < 6) {
            editTextPassRegister.setError("Password should be at least 6 characters long");
            editTextPassRegister.requestFocus();
            return;
        }

        if (rePass.length() < 6) {
            editTextPassRegister.setError("Password should be at least 6 characters long");
            editTextPassRegister.requestFocus();
            return;
        }

        if(!rePass.equals(pass)){
            editTextRepPasswordRegister.setError("The passwords not match! try again");
            editTextPassRegister.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(firstName,lastName,email,pass,rePass);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){

                                        Toast.makeText(registerPage.this,getString(R.string.successful_register),Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(registerPage.this, DetailsPage.class);
                                        startActivity(intent);
                                    }
                                    else{

                                    }

                                }
                            });

                        }

                        else{
                            Toast.makeText(registerPage.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }



}
