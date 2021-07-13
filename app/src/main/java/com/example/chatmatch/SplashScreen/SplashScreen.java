package com.example.chatmatch.SplashScreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatmatch.Authentication.AuthContainer;
import com.example.chatmatch.R;
import com.example.chatmatch.startup_page.startup_page;

public class SplashScreen extends AppCompatActivity {

    
    Thread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        timer = new Thread(){
            @Override
            public void run() {
                try{
                    synchronized (this){
                        //wait for 2 seconds before proceeding
                        wait(1000);
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                } finally {
                    //launch new activity / page after splash screen
                    Intent intent = new Intent(SplashScreen.this, startup_page.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}