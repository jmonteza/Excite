package com.example.chatmatch.Discovery;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.Menu.MenuController;
import com.example.chatmatch.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Discovery extends AppCompatActivity {

    private FirebaseFirestore db;
    private DiscoveryAdapter adapter;
    private RecyclerView discoveryRecyclerView;
    private MenuController menuController;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        db = FirebaseFirestore.getInstance();
        int id = R.id.connect;
        bottomNavigationView = findViewById(R.id.bottomNav);
        menuController = new MenuController(this, bottomNavigationView, id);
        menuController.useMenu();


        setUpRecyclerView();
    }


    private void setUpRecyclerView() {
        //Query
        Query query = db.collection("test");

        //Recycler options
        FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>()
                .setQuery(query, UserModel.class)
                .build();

        adapter = new DiscoveryAdapter(options);
        discoveryRecyclerView = findViewById(R.id.recycler_view);
        discoveryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        discoveryRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
