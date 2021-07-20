
package com.example.chatmatch.startup_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatmatch.Authentication.EmailSign_up;
import com.example.chatmatch.Messages.chatActivity;
import com.example.chatmatch.R;

public class startup_page extends AppCompatActivity {

    private Button EmailNavigate;
    private Button phnNumNavigate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_test);


        EmailNavigate = findViewById(R.id.emailMethod);
        phnNumNavigate = findViewById(R.id.sgnupPhoneNm);

        phnNumNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startup_page.this, chatActivity.class);
                startActivity(intent);

            }
        });

        EmailNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startup_page.this, EmailSign_up.class);
                startActivity(intent);
            }
        });
    }
}