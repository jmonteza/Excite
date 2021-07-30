package com.example.chatmatch.Messages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.example.chatmatch.Menu.MenuController;
import com.example.chatmatch.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ThreadActivity extends AppCompatActivity{
    /**
     * {@inheritDoc}
     * <p>
     * Perform initialization of all fragments.
     *
     * @param savedInstanceState
     */
    private FirebaseFirestore db;
    private CollectionReference threadRef;

    private static final String TAG = "ThreadActivity";

    private RecyclerView threads_list_recycler_view;
    private ThreadAdapter adapter;

    private ThreadCallback threadCallback;

    private Query query;

    private MenuController menuController;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_thread);

        db = FirebaseFirestore.getInstance();
        threadRef = db.collection("threads");

        int id  = R.id.messaging;
        bottomNavigationView = findViewById(R.id.bottomNav);
        menuController = new MenuController(ThreadActivity.this,bottomNavigationView, id);
        menuController.useMenu();

        threads_list_recycler_view = findViewById(R.id.chat_threads_recycler_view);

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        //
        query = threadRef.orderBy("timestamp", Query.Direction.DESCENDING);
        // query = threadRef.orderBy("timestamp", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<ThreadModel> options = new FirestoreRecyclerOptions.Builder<ThreadModel>()
                .setQuery(query,ThreadModel.class).build();

        adapter = new ThreadAdapter(options);

        threads_list_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        threads_list_recycler_view.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteThread(viewHolder.getAdapterPosition());
            }
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }
        }).attachToRecyclerView(threads_list_recycler_view);

        adapter.setOnItemClickListener(new ThreadAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) throws GeneralSecurityException, IOException {
                String thread_id = documentSnapshot.getId();

                Context context = ThreadActivity.this;
                MasterKey masterKey = new MasterKey.Builder(context)
                        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                        .build();

                EncryptedSharedPreferences sharedPreferences = (EncryptedSharedPreferences) EncryptedSharedPreferences
                        .create(
                                context,
                                "ExciteEncryptedSharedPref",
                                masterKey,
                                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                        );
                
                sharedPreferences.edit().putString("thread_id", thread_id).apply();
                
                Intent intent = new Intent(ThreadActivity.this, ChatActivity.class);
                startActivity(intent);
                // threadCallback.displayDrink(id);
                // Intent intent = new Intent(ThreadActivity.this, ChatActivity.class);
                // intent.putExtra("threadID", "ARDs9yqoqajh5O14XNxS");
                // startActivity(intent);
            }
        });
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

    // @Override
    // public void displayDrink(String threadID) {
    //
    //     intent.putExtra("thread_id", threadID);
    //
    // }

    // public interface ThreadCallback {
    //
    // }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.startListening();
        // query = threadRef.whereArrayContains("members", "ax1").orderBy("timestamp", Query.Direction.DESCENDING);
        // FirestoreRecyclerOptions<ThreadModel> options = new FirestoreRecyclerOptions.Builder<ThreadModel>()
        //         .setQuery(query,ThreadModel.class).build();
    }


    @Override
    protected void onPause() {
        super.onPause();
        adapter.stopListening();
    }

    /**
     * Called when the activity has detected the user's press of the back
     * key. The {@link #getOnBackPressedDispatcher() OnBackPressedDispatcher} will be given a
     * chance to handle the back button before the default behavior of
     * {@link Activity#onBackPressed()} is invoked.
     *
     * @see #getOnBackPressedDispatcher()
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
