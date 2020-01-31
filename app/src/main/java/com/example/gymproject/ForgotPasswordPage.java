package com.example.gymproject;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.textfield.TextInputLayout;

import java.util.Timer;

public class ForgotPasswordPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass_page);

        final EditText editText = findViewById(R.id.edit_text_recover_pass);

    //    final TextInputLayout til = (TextInputLayout) findViewById(R.id.tilEmail);
        Button recoverPassBtn = findViewById(R.id.recover_pass_btn);

        recoverPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            //    til.setError("You need to enter a name");

                final String email = editText.getText().toString().trim();
                if (email.isEmpty()) {
                    editText.setError(ForgotPasswordPage.this.getString(R.string.email_required));
                    editText.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editText.setError(ForgotPasswordPage.this.getString(R.string.valid_email));
                    editText.requestFocus();
                    return;
                }
                Toast toast = Toast.makeText(ForgotPasswordPage.this, ForgotPasswordPage.this.getString(R.string.password_recovery), Toast.LENGTH_LONG);
                toast.show();
                // timeSleep();
                Intent intent = new Intent(ForgotPasswordPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

//
//    public void timeSleep()  {
//        try {
////            TimeUnit.SECONDS.sleep(6);
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.fillInStackTrace();
//        }
//        {



//        }
//    }
//}

