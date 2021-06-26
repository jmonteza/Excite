package com.example.chatmatch.Messages;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatmatch.Menu.MenuController;
import com.example.chatmatch.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class messageActivity extends AppCompatActivity {

    private MenuController menuController;
    private BottomNavigationView bottomNavigationView;

    Button rateBtn;
    Button chatBtn;
    Button dropInBtn;
//    TextView msgtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        rateBtn = findViewById(R.id.rate);
        dropInBtn = findViewById(R.id.dropInlbl);
        chatBtn = findViewById(R.id.chat_lbl);


        //  get id of the current menu item
        int id = R.id.messaging;
        // Menu Navigation
        bottomNavigationView = findViewById(R.id.bottomNav);
        menuController = new MenuController(messageActivity.this,bottomNavigationView, id);
        menuController.useMenu();


        dropInBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Log.d("drop Click", "i was clicked");
          }
        });

        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("rate Click", "i was clicked");
            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("chat Click", "i was clicked");
            }
        });



    }

}