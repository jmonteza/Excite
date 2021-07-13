package com.example.chatmatch.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.chatmatch.R;
import com.example.chatmatch.swipeDiabledViewPager;

import org.jetbrains.annotations.NotNull;

public class onboard1 extends Fragment {



    EditText frstName;
    Button testbtn;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.onboarding_screen1, container, false);

//        LayoutInflater factory = getLayoutInflater();
//        View regisText = factory.inflate(R.layout.onboard_page_container, null);


//
        frstName = rootView.findViewById(R.id.chngFactor);
        String password = frstName.getText().toString().trim();

        testbtn = rootView.findViewById(R.id.testCore);

        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click", password.toString());
            }
        });

        return rootView;
    }



}
