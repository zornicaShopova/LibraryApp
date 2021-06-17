package book.api.libplovediv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emailLogin, passwordLogin;
    TextView  forgotPassTV;
    //regular btn for login
    Button loginB;

    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;
    private static final String TAG = "FacebookAuthentication";
    //use facebook button
    private LoginButton loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginB = findViewById(R.id.loginButton);
        loginB.setOnClickListener(this);

        //facebook login bnt
        loginButton = findViewById(R.id.login_button);

        emailLogin = findViewById(R.id.emailLoginET);
        passwordLogin = findViewById(R.id.passLoginET);

        //firebase auth
        mAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());


        // loginButton.setReadPermissions("email", "public_profile");

        //handle login responses by callback manager
        mCallbackManager = CallbackManager.Factory.create();
        //register a callback
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            // called when the user successfully logs in.
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onSuccess" + loginResult);
                handleFacebookToken(loginResult.getAccessToken());
                //new activity
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));

            }
            // called when the login is cancelled
            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel");
            }
            // called when there are errors in login.
            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "onError");
            }
        });
    }

    //check  for  credentials
    public void handleFacebookToken(AccessToken token) {
        Log.d(TAG, "handleFacebookToken" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //login successful
                if (task.isSuccessful()) {
                    Log.d(TAG, "sign in with credential: successful");
                    FirebaseUser user = mAuth.getCurrentUser();
                    //change ui
                     //updateUI(user);

                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //this open the Home Activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // set fields for user information
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // open registration activity
            case R.id.loginButton:
                userLogin();
                break;

        }
    }


    //take email  and pass from firebase to log in
    private void userLogin() {
        String email = emailLogin.getText().toString();
        String pass = passwordLogin.getText().toString();

        if (email.isEmpty()) {
            emailLogin.setError("Email is required!");
            emailLogin.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLogin.setError("Please enter a valid email!");
            emailLogin.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            passwordLogin.setError("Password is required!");
            passwordLogin.requestFocus();
            return;
        }

        if (pass.length() < 6) {
            passwordLogin.setError("Min password length is 6 chars!");
            passwordLogin.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Failed to  login! Try again!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}