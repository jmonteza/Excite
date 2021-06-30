package com.example.chatmatch.splashscreen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatmatch.AuthActivity;
import com.example.chatmatch.R;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

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
                    Intent intent = new Intent(splash_screen.this, AuthActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}