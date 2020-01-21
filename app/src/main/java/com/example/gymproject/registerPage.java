package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class registerPage extends AppCompatActivity {

    EditText pass;
    EditText repPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        pass = findViewById(R.id.pass);
        repPass = findViewById(R.id.rep_pass);
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
