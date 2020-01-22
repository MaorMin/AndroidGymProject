package com.example.gymproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import androidx.appcompat.app.AppCompatActivity;


import java.util.Timer;

public class ForgotPasswordPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass_page);

        final EditText editText = findViewById(R.id.edit_text_recover_pass);


        Button recoverPassBtn = findViewById(R.id.recover_pass_btn);

        recoverPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString() != null) {
                    CharSequence text = "Your password sent to the email!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(ForgotPasswordPage.this, text, duration);
                    toast.show();

                    Intent intent = new Intent(ForgotPasswordPage.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void timeSleep() throws InterruptedException {
        try {
            TimeUnit.SECONDS.sleep(6);
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new InterruptedException();
        }
        {



        }
    }
}

