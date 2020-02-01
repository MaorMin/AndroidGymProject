//package com.example.gymproject;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.Toolbar;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.android.material.textfield.TextInputLayout;
//
//public class Test extends AppCompatActivity {
//
//
//    EditText ed_email,ed_password;
//    TextInputLayout email_layout,password_layout;
//    Button btn_login;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("TextInputLayout");
//        ed_email = (EditText) findViewById(R.id.ed_email);
//        ed_password = (EditText) findViewById(R.id.ed_password);
//        email_layout = (TextInputLayout) findViewById(R.id.email_layout);
//        password_layout = (TextInputLayout) findViewById(R.id.password_layout);
//
//        btn_login = (Button) findViewById(R.id.btn_login);
//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(ed_email.getText().toString().equals("")){
//
//                    email_layout.setError("Email should not be empty");
//
//                }else if(ed_password.getText().toString().equals("")) {
//
//                    password_layout.setError("Password should not be empty");
//
//                }else{
//
////here you can write the code for login success
//                }
//            }
//        });
//    }
//
//}