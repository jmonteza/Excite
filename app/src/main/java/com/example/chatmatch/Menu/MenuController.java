package com.example.chatmatch.Menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.chatmatch.Matches.matches;
import com.example.chatmatch.Messages.messageActivity;
import com.example.chatmatch.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.discovery:
                        intent = new Intent(context, matches.class);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                        return true;
                    case R.id.messaging:
                        intent = new Intent(context, messageActivity.class);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                        return true;

                }


                return false;
            }



        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }





}








