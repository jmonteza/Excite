package com.example.chatmatch.startup_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.chatmatch.R;

public class onboard_page2 extends AppCompatActivity {

    Button navigateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_screen2);

        navigateBtn = findViewById(R.id.testCore);

        navigateBtn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {


                        Intent intent = new Intent(onboard_page2.this, onboard_page3.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    }
                });

    }


}