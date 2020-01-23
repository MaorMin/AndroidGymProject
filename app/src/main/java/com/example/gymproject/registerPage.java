package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class registerPage extends AppCompatActivity{

    EditText pass;
    EditText repPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Button registerBtnEnd = findViewById(R.id.reg_btn_end);

        registerBtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CharSequence text = "Your register has complete successfully!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(registerPage.this, text, duration);
                toast.show();

                Intent intent = new Intent(registerPage.this, MainActivity.class);
                startActivity(intent);
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



}
