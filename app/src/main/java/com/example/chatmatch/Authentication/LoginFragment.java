package com.example.chatmatch.Authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chatmatch.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LoginFragment extends Fragment implements View.OnClickListener{

    private Button sign_up_redirect_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_login, container, false);
        sign_up_redirect_btn = view.findViewById(R.id.sign_up_redirect_btn);
        sign_up_redirect_btn.setOnClickListener(this);
        return view;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id ==R.id.sign_up_redirect_btn){
            ((AuthContainer) requireActivity()).setCurrentPage(0);
        }
    }
}
