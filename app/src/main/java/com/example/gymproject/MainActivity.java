package com.example.gymproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.annotations.Nullable;

import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = null;
    private LoginButton loginFbButton;
    private CallbackManager mCallbackManager;
    private ImageButton eyeVisionBtn;
    private boolean isVisible;
    private EditText passTextMain;
    private EditText emailTextMain;
    private SignInButton googleSignInBtn;
    DataBase dataBase;
    ProgressBar progressBar;
    ProgressBar progressBarGoogle;
    GoogleSignInClient mGoogleSignInClient;
    final int CONTACT_REQUEST = 1;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCallbackManager = CallbackManager.Factory.create();
        loginFbButton = findViewById(R.id.login_Fb_Btn);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        loginFbButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        eyeVisionBtn = findViewById(R.id.eye_vision_btn);
        Button regBtn = findViewById(R.id.reg_btn);
        isVisible = false;
        emailTextMain = findViewById(R.id.mailTxt);
        passTextMain = findViewById(R.id.passTxt);
        dataBase = DataBase.getInstance();
        progressBar = findViewById(R.id.progress_bar_login_btn);
        progressBar.setVisibility(View.GONE);
        progressBarGoogle = findViewById(R.id.progress_bar_login_google_btn);
        progressBarGoogle.setVisibility(View.GONE);
        googleSignInBtn = findViewById(R.id.google_login_btn);
        mAuth = FirebaseAuth.getInstance();


        lngCheck();

        //-----sign in with facebook----//
        loginFbButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });


//      @Override
//        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
//            super.onActivityResult(requestCode, resultCode, data);
//
//            // Pass the activity result back to the Facebook SDK
//            mCallbackManager.onActivityResult(requestCode, resultCode, data);
//        }


        eyeVisionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    eyeVisionBtn.setImageResource(R.drawable.view_eye_closed);
                    passTextMain.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    eyeVisionBtn.setImageResource(R.drawable.view_eye);
                    passTextMain.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                isVisible = !isVisible;
            }
        });


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterPage.class);
                startActivity(intent);
            }
        });

        Button loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailTextMain.getText().toString().trim();
                final String pass = passTextMain.getText().toString().trim();


                if (email.isEmpty()) {
                    emailTextMain.setError(MainActivity.this.getString(R.string.email_required));
                    emailTextMain.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailTextMain.setError(MainActivity.this.getString(R.string.valid_email));
                    emailTextMain.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (pass.length() < 6) {
                    passTextMain.setError(MainActivity.this.getString(R.string.password_length));
                    passTextMain.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                SignUser(email, pass);
                progressBar.setVisibility(View.VISIBLE);
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




        googleSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarGoogle.setVisibility(View.VISIBLE);
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();
                mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);
                    //mGoogleSignInClient.revokeAccess();
                       // mGoogleSignInClient.signOut();
                switch (v.getId()) {
                    case R.id.google_login_btn:
                        signIn();
                        break;
                    // ...
                }
            }

            private void signIn() {

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, CONTACT_REQUEST);

            }
        });




    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == CONTACT_REQUEST) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }

    }



    //Google sign in OnStart method
    @Override
    protected void onStart() {
        super.onStart();
        //GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
        //updateUI(account);

    }
    //sign in user with email and password(no fb or google)

    public void SignUser(String email, String password) {
        dataBase.signInUser(email, password, this);
    }

    //check the language if hebrew or english
    public void lngCheck() {
        String lng = Locale.getDefault().getDisplayLanguage();

        if (lng.equals("English")) {
            passTextMain.setGravity(GravityCompat.START);
        } else {
            passTextMain.setGravity(GravityCompat.END);
        }
    }

    public void updateUI(FirebaseUser user) {
        if (user == null) {

        } else {

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            progressBarGoogle.setVisibility(View.GONE);
                            Intent intent = new Intent(MainActivity.this, DetailsPage.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            progressBarGoogle.setVisibility(View.GONE);
                            //    Snackbar.make(findViewById(R.id), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
//---login with facebook


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        mCallbackManager.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode != 0) {
//            Intent intent = new Intent(MainActivity.this, DetailsPage.class);
//            startActivity(intent);
//        }
//    }
    //-----sign out google accounts




}








