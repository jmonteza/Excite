package com.example.chatmatch.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chatmatch.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button login_btn;
    private Button sign_up_with_email_btn;
    private FirebaseUser currentUser;



    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_login);

        login_btn = findViewById(R.id.log_in_redirect_btn);
        sign_up_with_email_btn = findViewById(R.id.sign_up_redirect2_btn);

        sign_up_with_email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthActivity.this, EmailSignUp.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthActivity.this, Login.class);
                startActivity(intent);
            }
        });


        // mAuth = FirebaseAuth.getInstance();

        // currentUser = mAuth.getCurrentUser();
        //
        // if (currentUser != null){
        //     setContentView(R.layout.activity_signup);
        // } else {
        //     setContentView(R.layout.activity_signup);
        // }


        // email_signup_et = findViewById(R.id.email_signup_editText);
        // password_signup_et = findViewById(R.id.password_signup_editText);
        // verify_password_signup_et = findViewById(R.id.verify_password_signup_editText);
        //
        // email_login_et = findViewById(R.id.email_login_editText);
        // password_login_et = findViewById(R.id.password_login_editText);
        // user_id_tv = findViewById(R.id.user_id_textView);

//        currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        Log.d("User", currentUser.getEmail());


    }

    // private void createAccount(String email, String password){
    //     mAuth.createUserWithEmailAndPassword(email,password)
    //             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
    //                 @Override
    //                 public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
    //                     if (task.isSuccessful()) {
    //                         currentUser = mAuth.getCurrentUser();
    //                         if (currentUser != null) {
    //                             Intent intent = new Intent(AuthActivity.this, EmailVerification.class);
    //                             startActivity(intent);
    //                         }
    //                     } else {
    //                         Toast.makeText(AuthActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
    //                     }
    //                 }
    //             });
    // }



    // private void signInUser(String email, String password) {
    //     mAuth.signInWithEmailAndPassword(email, password)
    //             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
    //                 @Override
    //                 public void onComplete(@NonNull Task<AuthResult> task) {
    //                     if (task.isSuccessful()) {
    //                         // Sign in success, update UI with the signed-in user's information
    //                         currentUser = mAuth.getCurrentUser();
    //                         user_id_tv.setText(String.valueOf(currentUser.isEmailVerified()));
    //                     } else {
    //                         // If sign in fails, display a message to the user.
    //                         Toast.makeText(AuthActivity.this, "Authentication failed.",
    //                                 Toast.LENGTH_SHORT).show();
    //                     }
    //                 }
    //             });
    // }


    // public void signUp(View v){
    //     String email = email_signup_et.getText().toString().trim();
    //     String password = password_signup_et.getText().toString().trim();
    //     String verify_password = verify_password_signup_et.getText().toString().trim();
    //
    //     if (email.length() <= 0 || password.length() <= 0 || verify_password.length() <= 0){
    //         return;
    //     }
    //
    //     if (!password.equals(verify_password)){
    //         Toast.makeText(this, "Your passwords do not match", Toast.LENGTH_LONG).show();
    //         password_signup_et.getText().clear();
    //         verify_password_signup_et.getText().clear();
    //         return;
    //     }
    //
    //     createAccount(email, password);
    // }

    // public void logIn(View v){
    //     String email = email_login_et.getText().toString().trim();
    //     String password = password_login_et.getText().toString().trim();
    //
    //     if (email.length() <= 0 || password.length() <= 0){
    //         return;
    //     }
    //
    //     signInUser(email, password);
    // }

    // public void signUpRedirect(View v){
    //     setContentView(R.layout.activity_login);
    // }

}
