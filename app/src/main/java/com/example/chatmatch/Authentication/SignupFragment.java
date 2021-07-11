package com.example.chatmatch.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chatmatch.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class SignupFragment extends Fragment implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private EditText email_signup_et;
    private EditText password_signup_et;
    private EditText verify_password_signup_et;
    private FirebaseUser currentUser;
    private Button sign_up_btn;
    private Button login_redirect_btn;


    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_signup, container,false);

        mAuth = FirebaseAuth.getInstance();
        email_signup_et = view.findViewById(R.id.email_signup_editText);
        password_signup_et = view.findViewById(R.id.password_signup_editText);
        verify_password_signup_et = view.findViewById(R.id.verify_password_signup_editText);
        sign_up_btn = view.findViewById(R.id.sign_up_btn);
        sign_up_btn.setOnClickListener(this);

        login_redirect_btn = view.findViewById(R.id.login_redirect_btn);
        login_redirect_btn.setOnClickListener(this);

        return view;
    }

    private void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            currentUser = mAuth.getCurrentUser();
                            if (currentUser != null) {
                                Intent intent = new Intent(getActivity(), EmailVerification.class);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signUp(){
        String email = email_signup_et.getText().toString().trim();
        String password = password_signup_et.getText().toString().trim();
        String verify_password = verify_password_signup_et.getText().toString().trim();

        if (email.length() <= 0 || password.length() <= 0 || verify_password.length() <= 0){
            return;
        }

        if (!password.equals(verify_password)){
            Toast.makeText(getActivity(), "Your passwords do not match", Toast.LENGTH_LONG).show();
            password_signup_et.getText().clear();
            verify_password_signup_et.getText().clear();
            return;
        }

        createAccount(email, password);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id ==R.id.sign_up_btn){
            signUp();
        } else if (id ==R.id.login_redirect_btn){
            ((AuthContainer)getActivity()).setCurrentPage(1);
        }
    }
}
