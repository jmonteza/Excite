package com.example.chatmatch.Discover;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.Database.FirestoreAdapter;

import com.example.chatmatch.Database.MyCallback;
import com.example.chatmatch.Matches.MatchCardModel;
import com.example.chatmatch.Menu.MenuController;
import com.example.chatmatch.ObjectManager.ProfileManager;
import com.example.chatmatch.R;
import com.example.chatmatch.User.ProfilePhotoActivity;
import com.example.chatmatch.User.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class discover  extends AppCompatActivity {

    RecyclerView recyclerView;

    private MenuController menuController;
    private BottomNavigationView bottomNavigationView;
    private ArrayList<User> userProfileLst;
    private ProfileManager ProfileMgr = ProfileManager.getInstance();

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


        revealProfiles();


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

    public void revealProfiles(){
        FirestoreAdapter firestoreAdapter = new FirestoreAdapter();
        firestoreAdapter.loadProfiles(new MyCallback() {
            @Override
            public void onCallback(ArrayList<User> user) {
                for (int i = 0; i < user.size(); i++){
                    Log.d("First name of users", user.get(i).getFirstName());
                }
            }

        });
//        Log.d("here", "i got here");
//        userProfileLst = firestoreAdapter.loadProfiles();
//        Log.d("size", userProfileLst.size()+"");
//        for (int i = 0; i < userProfileLst.size(); i++){
//            Log.d("user 1", userProfileLst.get(i)+"");
//        }
    }

}
