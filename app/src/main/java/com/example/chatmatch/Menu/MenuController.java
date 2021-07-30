package com.example.chatmatch.Menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.chatmatch.Discovery.Discovery;
import com.example.chatmatch.Matches.MatchesActivity;
import com.example.chatmatch.Messages.messageActivity;
import com.example.chatmatch.R;
import com.example.chatmatch.User.ProfilePhotoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MenuController implements NavigationView.OnNavigationItemSelectedListener {

    private Intent intent;
    private Context context;

    private BottomNavigationView bottomNavigationView;

    private Menu menu;

    private int id;

    public MenuController(Context context, BottomNavigationView bottomNavigationView, int id) {
        this.context = context;

        this.bottomNavigationView = bottomNavigationView;


        this.id = id;
    }


    public void useMenu(){

        bottomNavigationView.setSelectedItemId(id);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.discovery){
                    intent = new Intent(context, MatchesActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    return true;
                } else if (id == R.id.messaging) {
                    intent = new Intent(context, messageActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    return true;
                } else if (id == R.id.connect){
                    intent = new Intent(context, Discovery.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    return true;
                } else if (id == R.id.userprofile){
                    intent = new Intent(context, ProfilePhotoActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    return true;
                } else {
                    return false;
                }

                // switch(item.getItemId()){
                //     case R.id.discovery:
                //         intent = new Intent(context, matches.class);
                //         context.startActivity(intent);
                //         ((Activity)context).finish();
                //         return true;
                //     case R.id.messaging:
                //         intent = new Intent(context, messageActivity.class);
                //         context.startActivity(intent);
                //         ((Activity)context).finish();
                //         return true;
                //     case R.id.connect:
                //         intent = new Intent(context, Discovery.class);
                //         context.startActivity(intent);
                //         ((Activity)context).finish();
                //         return true;
                //     case R.id.userprofile:
                //         intent = new Intent(context, ProfilePhotoActivity.class);
                //         context.startActivity(intent);
                //         ((Activity)context).finish();
                //         return true;
                //
                // }
                // return false;
            }



        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }





}








