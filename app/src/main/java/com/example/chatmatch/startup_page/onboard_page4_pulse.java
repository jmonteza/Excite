package com.example.chatmatch.startup_page;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatmatch.Discover.discover;
import com.example.chatmatch.R;
import com.example.chatmatch.SplashScreen.SplashScreen;

public class onboard_page4_pulse extends AppCompatActivity {


    Thread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_screen4_pulse);

        timer = new Thread(){
            @Override
            public void run() {
                try{
                    synchronized (this){
                        //wait for 3 seconds before proceeding
                        wait(3000);
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                } finally {
                    //launch new activity / page after splash screen
                    Intent intent = new Intent(onboard_page4_pulse.this, discover.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}