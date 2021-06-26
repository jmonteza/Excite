package com.example.chatmatch.Matches;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.Menu.MenuController;
import com.example.chatmatch.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class matches extends AppCompatActivity  {

    RecyclerView recyclerView;

    private MenuController menuController;
    private BottomNavigationView bottomNavigationView;

    ArrayList<MatchCardModel> matchCardModels;
    MatchAdapter matchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        int id  = R.id.discovery;
        // Menu Navigation
        bottomNavigationView = findViewById(R.id.bottomNav);

        menuController = new MenuController(matches.this,bottomNavigationView, id);
        menuController.useMenu();

        recyclerView = findViewById(R.id.recycler_view);

        //array to store pictures
        Integer[] matches = {R.drawable.femam1
                ,R.drawable.femam2, R.drawable.femam3,
                R.drawable.femam4, R.drawable.femam5};
        matchCardModels = new ArrayList<>();
        for (int y = 0; y < matches.length; y++){
            MatchCardModel matchModels = new MatchCardModel(matches[y]);
            matchCardModels.add(matchModels);
        }

        //      design for horizontal layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(matches.this,
                LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //intialize matchcardAdapter
        matchAdapter = new MatchAdapter(matches.this, matchCardModels);

        //Ste matchadapter to recyclerview
        recyclerView.setAdapter(matchAdapter);


    }




}
