package com.example.gymproject;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private ImageButton eyeVisionBtn;
    private boolean isVisible;
    private EditText passText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginFbBtn);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        eyeVisionBtn = findViewById(R.id.eye_vision_btn);
        Button regBtn = findViewById(R.id.reg_btn);
        isVisible = false;
        passText = findViewById(R.id.passTxt);

        lngCheck();

        eyeVisionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    eyeVisionBtn.setImageResource(R.drawable.view_eye_closed);
                    passText.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    eyeVisionBtn.setImageResource(R.drawable.view_eye);
                    passText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                isVisible = !isVisible;
            }
        });


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, registerPage.class);
                startActivity(intent);
            }
        });

        Button loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailsPage.class);
                startActivity(intent);
            }
        });

        Button forgotPassBtn = findViewById(R.id.forgot_pass_btn);

        forgotPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPasswordPage.class);
                startActivity(intent);
            }
        });



        }

    public void lngCheck() {
        String lng = Locale.getDefault().getDisplayLanguage();

        if (lng.equals("English")) {
            passText.setGravity(GravityCompat.START);
        } else {
            passText.setGravity(GravityCompat.END);
        }
}
}

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode != 0) {
//            Intent intent = new Intent(MainActivity.this, DetailsPage.class);
//            startActivity(intent);
//        }
//    }
//}








