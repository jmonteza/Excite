package com.example.chatmatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerification extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private TextView verified_user_status;
    private TextView current_user_status;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        verified_user_status = findViewById(R.id.verified_status);
        current_user_status = findViewById(R.id.current_user_status);
    }

    public void verified(View v){
        currentUser.reload();
        verified_user_status.setText(currentUser.getUid());
        current_user_status.setText(String.valueOf(currentUser.isEmailVerified()));
        if (currentUser != null && currentUser.isEmailVerified()){
            Intent intent = new Intent(this, OnboardingActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Your email is not verified.", Toast.LENGTH_SHORT).show();
        }
    }
}
