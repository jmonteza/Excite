package com.example.chatmatch.Matches;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.Menu.MenuController;
import com.example.chatmatch.R;
import com.example.chatmatch.Model.UserModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MatchesActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private MatchFUIAdapter adapter;
    private RecyclerView matchRecyclerView;
    private MenuController menuController;
    private BottomNavigationView bottomNavigationView;
    /**
     * {@inheritDoc}
     * <p>
     * Perform initialization of all fragments.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        db = FirebaseFirestore.getInstance();

        int id  = R.id.discovery;
        bottomNavigationView = findViewById(R.id.bottomNav);
        menuController = new MenuController(MatchesActivity.this,bottomNavigationView, id);
        menuController.useMenu();

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = db.collection("userProfile");

        FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>()
                .setQuery(query, UserModel.class)
                .build();

        adapter = new MatchFUIAdapter(options);

        matchRecyclerView = findViewById(R.id.recycler_view);
        matchRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        matchRecyclerView.setAdapter(adapter);
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
