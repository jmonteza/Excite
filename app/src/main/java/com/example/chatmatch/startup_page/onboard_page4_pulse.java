package com.example.chatmatch.startup_page;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatmatch.Discover.discover;
import com.example.chatmatch.R;

public class onboard_page4_pulse extends AppCompatActivity {

    Thread timer;

    // SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // LocalUser localUser = new LocalUser();
        // sp = getSharedPreferences("userPref1", Context.MODE_PRIVATE);
        //
        //
        // String uri = sp.getString("uri", "-1");
        // String email = sp.getString("email", "-1");
        // String password = sp.getString("password", "-1");
        // String firstName = sp.getString("firstName", "-1");
        // String birthDay = sp.getString("birthDay", "-1");
        // String selectedGender = sp.getString("selectedGender", "-1");
        // String selectedInterest = sp.getString("selectedInterest", "-1");
        //
        // Log.d("uri test", uri);
        //
        // LocalUser lu = LocalUser.getInstance();
        // lu.setContext(getApplicationContext());





        // Log.d("firstName", firstName+"");
        setContentView(R.layout.onboarding_screen4_pulse);

        timer = new Thread(){
            @Override
            public void run() {
                try{
                    synchronized (this){
                        //wait for 3 seconds before proceeding
                        // lu.saveLocalData(uri, email, password, birthDay,firstName, selectedGender, selectedInterest);
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