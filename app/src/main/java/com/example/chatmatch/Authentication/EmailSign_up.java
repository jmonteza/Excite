package com.example.chatmatch.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.chatmatch.Database.LocalUser;
import com.example.chatmatch.Discover.discover;
import com.example.chatmatch.Discovery.discovery;
import com.example.chatmatch.R;
import com.example.chatmatch.startup_page.onboard_page1;
import com.example.chatmatch.startup_page.startup_page;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EmailSign_up extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email_signup_et;
    private EditText password_signup_et;
    private EditText verify_password_signup_et;
    private FirebaseUser currentUser;
    private Button sign_up_btn;
    private ImageButton login_redirect_btn;
    private ArrayList<String> curr_lst;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_sign_up);


        sp = getSharedPreferences("userPref1", Context.MODE_PRIVATE);




        mAuth = FirebaseAuth.getInstance();
        email_signup_et = findViewById(R.id.email_signup_editText);
        password_signup_et = findViewById(R.id.password_signup_editText);
        verify_password_signup_et = findViewById(R.id.verify_password_signup_editText);
        sign_up_btn = findViewById(R.id.sign_up_btn);
        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        SharedPreferences preferences = getSharedPreferences("remember_user", MODE_PRIVATE);
        String remembered = preferences.getString("remember", "");

        if (remembered.equals("true")){
            Intent intent = new Intent(EmailSign_up.this, discover.class);
            startActivity(intent);
        }
        else if (remembered.equals("false")){
            Log.d("not logged in", "please log in ");
        }
        login_redirect_btn = findViewById(R.id.login_redirect_btn);
        login_redirect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_signup_et.getText().clear();
                password_signup_et.getText().clear();
                verify_password_signup_et.getText().clear();
                Intent intent = new Intent(EmailSign_up.this, startup_page.class);
                startActivity(intent);
            }
        });
    }

    private void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(EmailSign_up.this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            curr_lst = new ArrayList<>();

                            currentUser = mAuth.getCurrentUser();
                            if (currentUser != null) {
                                curr_lst.add(email);
                                curr_lst.add(password);

                                SharedPreferences preferences = getSharedPreferences("remember_user", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("remember", "true");
                                editor.apply();




                                Intent intent = new Intent(EmailSign_up.this, onboard_page1.class);
                                intent.putStringArrayListExtra("userDetails",  curr_lst);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(EmailSign_up.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signUp(){


        String email = email_signup_et.getText().toString().trim();
        String password = password_signup_et.getText().toString().trim();
        String verify_password = verify_password_signup_et.getText().toString().trim();

        SharedPreferences.Editor editor = sp.edit();

        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();

//        LocalUser.saveLocalData(email, password);
//        Toast.makeText(EmailSign_up.this, "info saved", Toast.LENGTH_LONG).show();
        if (email.length() <= 0 || password.length() <= 0 || verify_password.length() <= 0){
            Animation shake = AnimationUtils.loadAnimation(EmailSign_up.this, R.anim.shake);
            email_signup_et.startAnimation(shake);
            password_signup_et.startAnimation(shake);
            verify_password_signup_et.startAnimation(shake);
            return;
        }

        if (!password.equals(verify_password)){
            Toast.makeText(getApplicationContext(), "Your passwords do not match", Toast.LENGTH_LONG).show();
            password_signup_et.getText().clear();
            verify_password_signup_et.getText().clear();
            return;
        }

        createAccount(email, password);
    }


}