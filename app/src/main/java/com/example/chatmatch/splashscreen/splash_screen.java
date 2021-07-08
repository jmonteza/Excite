package com.example.chatmatch.splashscreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatmatch.ProfilePhotoActivity;
import com.example.chatmatch.R;

public class splash_screen extends AppCompatActivity {

    
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
                        //wait for 5 seconds before proceeding
                        wait(5000);
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                } finally {
                    //launch new activity / page after spash screen
                    Intent intent = new Intent(splash_screen.this, ProfilePhotoActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}