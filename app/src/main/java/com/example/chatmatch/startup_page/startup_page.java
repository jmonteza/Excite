
package com.example.chatmatch.startup_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chatmatch.Authentication.EmailSign_up;
import com.example.chatmatch.Authentication.phoneNumVerification;
import com.example.chatmatch.Database.FirestoreController;
import com.example.chatmatch.Messages.chatActivity;
import com.example.chatmatch.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Random;

public class startup_page extends AppCompatActivity {

    private Button EmailNavigate;
    private Button phnNumNavigate;
    private MaterialButton fcbRedirectBtn;
    private MaterialButton gglRedirectBtn;

    private FirestoreController fireStoreController;

    private Integer indicator;
    private String idSequence;
    private ArrayList<String> idData;
    private Integer randInt;
    private String randSequence;
    private Random random;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_test);

        fireStoreController = new FirestoreController();

        EmailNavigate = findViewById(R.id.emailMethod);
        phnNumNavigate = findViewById(R.id.sgnupPhoneNm);
        fcbRedirectBtn = findViewById(R.id.fcbkLogobtn);
        gglRedirectBtn = findViewById(R.id.googleLogobtn);


        gglRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        fcbRedirectBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                idGenerator();
//                fireStoreController.uploadData("x", "xp", new FirestoreController.FireStoreUploadCallback() {
//                    @Override
//                    public void onCallback() {
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        }).start();
//                    }
//                }, new FirestoreController.FireStoreUploadFailCallback(){
//                    @Override
//                    public void onCallback() {
//                        Toast.makeText(getApplicationContext(), "The database cannot be accessed at this point, please try again later. Thank you.", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//        });
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