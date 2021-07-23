package com.example.chatmatch.Notification;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

public class FirebaseMessagingServices extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token){

        Log.d("TAG", "the token refreshed: "+token+"");
        //`TODO: send registration to database

    }
}
