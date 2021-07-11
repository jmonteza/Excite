package com.example.chatmatch.Authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class LoginFragment extends Fragment implements View.OnClickListener {

    private Button sign_up_redirect_btn;
    private Button login_btn;
    private EditText email_login_et;
    private EditText password_login_et;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private TextView forgot_password;

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);

        mAuth = FirebaseAuth.getInstance();
        email_login_et = view.findViewById(R.id.email_login_editText);
        password_login_et = view.findViewById(R.id.password_login_editText);
        sign_up_redirect_btn = view.findViewById(R.id.sign_up_redirect_btn);
        sign_up_redirect_btn.setOnClickListener(this);
        login_btn = view.findViewById(R.id.log_in_btn);
        login_btn.setOnClickListener(this);

        forgot_password = view.findViewById(R.id.forgot_password);
        forgot_password.setOnClickListener(this);

        return view;
    }


    private void signInUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            currentUser = mAuth.getCurrentUser();
                            Toast.makeText(getActivity(), "Authentication successful" + mAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void forgotPassword(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Password reset email has been sent.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void login() {
        String email = email_login_et.getText().toString().trim();
        String password = password_login_et.getText().toString().trim();
        if (email.length() <= 0 || password.length() <= 0) {
            return;
        }
        signInUser(email, password);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.sign_up_redirect_btn) {
            ((AuthContainer) requireActivity()).setCurrentPage(0);
        } else if (id == R.id.log_in_btn) {
            login();
        } else if (id == R.id.forgot_password) {
            String email = email_login_et.getText().toString().trim();
            forgotPassword(email);
        }
    }
}
