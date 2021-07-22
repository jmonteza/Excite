package com.example.chatmatch.startup_page;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chatmatch.Database.LocalUser;
import com.example.chatmatch.Discover.discover;
import com.example.chatmatch.R;
import com.example.chatmatch.SplashScreen.SplashScreen;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

public class onboard_page4_pulse extends AppCompatActivity {


    Thread timer;



    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocalUser localUser = new LocalUser();
        sp = getSharedPreferences("userPref1", Context.MODE_PRIVATE);

        String email = sp.getString("email", "-1");
        String password = sp.getString("password", "-1");
        String firstName = sp.getString("firstName", "-1");
        String birthDay = sp.getString("birthDay", "-1");
        String selectedGender = sp.getString("selectedGender", "-1");
        String selectedInterest = sp.getString("selectedInterest", "-1");


        LocalUser lu = LocalUser.getInstance();
        lu.setContext(getApplicationContext());





        Log.d("firstName", firstName+"");
        setContentView(R.layout.onboarding_screen4_pulse);

        timer = new Thread(){
            @Override
            public void run() {
                try{
                    synchronized (this){
                        //wait for 3 seconds before proceeding
                        lu.saveLocalData(email, password, birthDay,firstName, selectedGender, selectedInterest);
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