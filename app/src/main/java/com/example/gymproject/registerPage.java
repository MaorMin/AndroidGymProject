package com.example.gymproject;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Locale;



public class registerPage extends MainActivity {

  //  EditText pass;
 //   EditText repPass;
    DataBase dataBase = new DataBase();
    ProgressBar progressBar;

    private EditText editTextfirstNameRegister;
    private EditText editTextlastNameRegister;
    private EditText editTextEmailRegister;
    private EditText editTextPassRegister;
    private EditText editTextRepPasswordRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        editTextfirstNameRegister = findViewById(R.id.first_name_register);
        editTextlastNameRegister = findViewById(R.id.last_name_register);
        editTextEmailRegister = findViewById(R.id.email_register);
        editTextPassRegister = findViewById(R.id.pass_register);
        editTextRepPasswordRegister = findViewById(R.id.rep_pass_register);


        Button registerBtnEnd = findViewById(R.id.reg_btn_end);
        progressBar = findViewById(R.id.progress_bar_reg_btn);

        registerBtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Users");

                final String maor = "m";

                myRef.setValue(maor);
                registerUser();



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

        lngCheck();
    }


    public void lngCheck() {
        String lng = Locale.getDefault().getDisplayLanguage();

        if (lng.equals("English")) {
       //     editTextPassRegister.setGravity(GravityCompat.START);
        //    editTextRepPasswordRegister.setGravity(GravityCompat.START);
        } else {
          //  editTextPassRegister.setGravity(GravityCompat.END);
           // editTextRepPasswordRegister.setGravity(GravityCompat.END);
        }
    }

    boolean registerSuccess;
    public void registerUser() {
        final String email = editTextEmailRegister.getText().toString().trim();
        final String pass = editTextPassRegister.getText().toString().trim();
        final String firstName = editTextfirstNameRegister.getText().toString().trim();
        final String lastName = editTextlastNameRegister.getText().toString().trim();
        final String rePass = editTextRepPasswordRegister.getText().toString().trim();


        if (firstName.isEmpty()) {
            editTextfirstNameRegister.setError("First name required");
            editTextfirstNameRegister.requestFocus();
            return;
        }
        if (lastName.isEmpty()) {
            editTextlastNameRegister.setError("Last name required");
            editTextlastNameRegister.requestFocus();
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
        if (pass.length() < 1) {
            editTextPassRegister.setError("Password should be at least 6 characters long");
            editTextPassRegister.requestFocus();
            return;
        }

        if (rePass.length() < 1) {
            editTextRepPasswordRegister.setError("Password should be at least 6 characters long");
            editTextRepPasswordRegister.requestFocus();
            return;
        }

        if (!rePass.equals(pass)) {
            editTextRepPasswordRegister.setError("The passwords not match! try again");
            editTextPassRegister.requestFocus();
            return;
        }
     //   dataBase.registerUserToDatabase(firstName, lastName, email, pass, rePass);
      //  progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(registerPage.this,"test: " + firstName,Toast.LENGTH_LONG).show();
            registerSuccess =  dataBase.registerUserToDatabase(firstName,lastName,email,pass,rePass);
     //   progressBar.setVisibility(View.GONE);
        if(registerSuccess) {
            Toast.makeText(registerPage.this, getString(R.string.successful_register), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(registerPage.this, DetailsPage.class);
            startActivity(intent);
        }
        else
        //    Toast.makeText(registerPage.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
            Toast.makeText(registerPage.this,"not succeeded" ,Toast.LENGTH_LONG).show();
    }
}
