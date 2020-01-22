package com.example.gymproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private LoginButton loginButton;
    private CircleImageView circleImageView;
    private TextView txtName, txtEmail;

    private LinearLayout Prof_Section;
    private Button Signout;
    private SignInButton Signin;
    private TextView Name, Email;
    private ImageView Prof_pic;

    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;


    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-------------Google--------------

        Signin = (SignInButton)findViewById(R.id.google_login_btn);
        Signin.setOnClickListener(this);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();


//--------------Facebook---------------
        loginButton = findViewById(R.id.loginFbBtn);
        //circleImageView = findViewById(R.id.profile_picFb);
        callbackManager = CallbackManager.Factory.create();

        //txtEmail = findViewById(R.id.profile_email);
        // txtName = findViewById(R.id.profile_name);

        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        Button regBtn = findViewById(R.id.reg_btn);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != 0) {
            Intent intent = new Intent(MainActivity.this, DetailsPage.class);
            startActivity(intent);

//        if (requestCode == REQ_CODE) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleResult(result);
//        }


        }
    }



    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null) {
                txtName.setText("");
                txtEmail.setText("");
                Toast.makeText(MainActivity.this, "User logged out", Toast.LENGTH_LONG).show();
            } else {

                loadUserProfile(currentAccessToken);

            }
        }
    };

    private void loadUserProfile(AccessToken newAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String email = object.getString("email");


                    //txtEmail.setText(email);
                    //txtName.setText(first_name);
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "firstName,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    //---------------Google implementation sign in
    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.google_login_btn:
                signIn();
                break;
        }

    }

    private void signIn() {


        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);

    }

//    private void handleResult(GoogleSignInResult result) {
//
//        if (result.isSuccess()) {
//            GoogleSignInAccount account = result.getSignInAccount();
//            //  String firstName = account.getDisplayName();
//            // String lastName = account.getFamilyName();
//            //  String email = account.getEmail();
//            // String img_url = account.getPhotoUrl().toString();
//            //  Name.setText(firstName);
//            // Email.setText(email);
//            //   Glide.with(this).load(img_url).into(prof_pic);
////              updateUI(true);
////        }
////        else
////             updateUI(false);
//        }

   private void updateUI(boolean isLogin) {
        if (isLogin) {
            Prof_Section.setVisibility(View.VISIBLE);
            Signin.setVisibility(View.GONE);
        }
        else
            Prof_Section.setVisibility(View.GONE);
        Signin.setVisibility(View.VISIBLE);
    }


    }

