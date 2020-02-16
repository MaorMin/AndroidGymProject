package com.example.gymproject;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Timer;



public class ForgotPasswordPage extends AppCompatActivity {

   private ProgressBar progressBar;
   private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass_page);

       progressBar = findViewById(R.id.forgot_pass_progress_bar);

        final EditText editText = findViewById(R.id.recover_pass_edit_text);

        Button recoverPassBtn = findViewById(R.id.recover_pass_btn);

        dataBase = DataBase.getInstance();

        progressBar.setVisibility(View.GONE);
        recoverPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }

            private void resetPassword() {
                final String email = editText.getText().toString().trim();
                if (email.isEmpty()) {
                    editText.setError(ForgotPasswordPage.this.getString(R.string.email_required));
                    editText.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (email.length() > 25 ) {
                    editText.setError(ForgotPasswordPage.this.getString(R.string.string_length));
                    editText.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editText.setError(ForgotPasswordPage.this.getString(R.string.valid_email));
                    editText.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                dataBase.passwordResetMail(email,ForgotPasswordPage.this);
                Toast toast = Toast.makeText(ForgotPasswordPage.this, ForgotPasswordPage.this.getString(R.string.password_recovery), Toast.LENGTH_LONG);
                toast.show();
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

