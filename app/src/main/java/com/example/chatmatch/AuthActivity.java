package com.example.chatmatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class AuthActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email_signup_et;
    private EditText password_signup_et;
    private EditText email_login_et;
    private EditText password_login_et;
    private TextView user_id_tv;
    private FirebaseUser currentUser;



    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        // currentUser = mAuth.getCurrentUser();
        //
        // if (currentUser != null){
        //     setContentView(R.layout.activity_signup);
        // } else {
        //     setContentView(R.layout.activity_signup);
        // }


        email_signup_et = findViewById(R.id.email_signup_editText);
        password_signup_et = findViewById(R.id.password_signup_editText);

        email_login_et = findViewById(R.id.email_login_editText);
        password_login_et = findViewById(R.id.password_login_editText);
        user_id_tv = findViewById(R.id.user_id_textView);

//        currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        Log.d("User", currentUser.getEmail());


    }

    private void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            currentUser = mAuth.getCurrentUser();
                            if (currentUser != null) {
                                Intent intent = new Intent(AuthActivity.this, OnboardingActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(AuthActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    private void signInUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            currentUser = mAuth.getCurrentUser();
                            user_id_tv.setText(String.valueOf(currentUser.isEmailVerified()));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(AuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void signUp(View v){
        String email = email_signup_et.getText().toString();
        String password = password_signup_et.getText().toString();

        if (email.length() <= 0 || password.length() <= 0){
            return;
        }

        createAccount(email, password);
    }

    public void logIn(View v){
        String email = email_login_et.getText().toString();
        String password = password_login_et.getText().toString();

        if (email.length() <= 0 || password.length() <= 0){
            return;
        }

        signInUser(email, password);
    }

    public void signUpRedirect(View v){
        setContentView(R.layout.activity_login);
    }

    public void profilePicture(View v){
        Intent intent = new Intent(AuthActivity.this, ProfilePhotoActivity.class);
        startActivity(intent);
    }

}
