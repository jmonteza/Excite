package com.example.chatmatch.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatmatch.R;
import com.example.chatmatch.startup_page.startup_page;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class phoneNumVerification extends AppCompatActivity {

    ImageButton navBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonenum_verification);

        navBack = findViewById(R.id.bckarrow);

        navBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(phoneNumVerification.this, startup_page.class);
                startActivity(intent);

            }
        });
    }


}