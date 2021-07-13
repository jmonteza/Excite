package com.example.chatmatch.Discover;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.Matches.MatchAdapter;
import com.example.chatmatch.Matches.MatchCardModel;
import com.example.chatmatch.Matches.matches;
import com.example.chatmatch.Menu.MenuController;
import com.example.chatmatch.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class discover  extends AppCompatActivity {

    RecyclerView recyclerView;

    private MenuController menuController;
    private BottomNavigationView bottomNavigationView;


    ImageButton filterBtn;
    ArrayList<MatchCardModel> matchCardModels;
    discoverAdapter discoverAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        int id  = R.id.connect;
        // Menu Navigation
        bottomNavigationView = findViewById(R.id.bottomNav);

        menuController = new MenuController(discover.this,bottomNavigationView, id);
        menuController.useMenu();

        recyclerView = findViewById(R.id.recycler_view);

        //array to store pictures
        Integer[] matches = {R.drawable.femam1
                ,R.drawable.femam2, R.drawable.femam3,
                R.drawable.femam4, R.drawable.femam5, R.drawable.femam6};
        matchCardModels = new ArrayList<>();
        for (int y = 0; y < matches.length; y++){
            MatchCardModel matchModels = new MatchCardModel(matches[y]);
            matchCardModels.add(matchModels);
        }

        //      design for horizontal layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(discover.this,
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //intialize matchcardAdapter
        discoverAdapter = new discoverAdapter(discover.this, matchCardModels);

        //Ste matchadapter to recyclerview
        recyclerView.setAdapter(discoverAdapter);


        filterBtn = findViewById(R.id.filter_btn);

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new discover_filter().show(getSupportFragmentManager(), "EDIT_TEXT2");
            }
        });

    }

}
