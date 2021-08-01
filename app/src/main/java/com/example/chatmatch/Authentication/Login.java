package com.example.chatmatch.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chatmatch.Discovery.Discovery;
import com.example.chatmatch.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    /**
     * {@inheritDoc}
     * <p>
     * Perform initialization of all fragments.
     *
     * @param savedInstanceState
     */

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText email_login_et;
    private EditText password_login_et;
    private Button login_btn;
    private final String TAG = "Login Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_test);
        email_login_et = findViewById(R.id.email_login_editText);
        password_login_et = findViewById(R.id.password_login_editText);
        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoginDetails();
            }
        });

        mAuth = FirebaseAuth.getInstance();

    }

    private void signInUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            currentUser = mAuth.getCurrentUser();
                            if (currentUser != null){
                                goToDiscoveryScreen();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void goToDiscoveryScreen(){
        Intent intent = new Intent(Login.this, Discovery.class);
        startActivity(intent);
    }
    private void getLoginDetails(){
        String email = email_login_et.getText().toString().trim();
        String password = password_login_et.getText().toString().trim();

        if (email.length() <= 0 || password.length() <= 0){
            return;
        }
        signInUser(email, password);
    }


}
