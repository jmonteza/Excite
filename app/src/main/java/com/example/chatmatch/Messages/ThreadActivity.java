package com.example.chatmatch.Messages;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ThreadActivity extends AppCompatActivity {
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
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_thread);

        db = FirebaseFirestore.getInstance();
        threadRef = db.collection("threads");

        threads_list_recycler_view = findViewById(R.id.chat_threads_recycler_view);

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        Query query = threadRef.orderBy("timestamp", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<ThreadModel> options = new FirestoreRecyclerOptions.Builder<ThreadModel>()
                .setQuery(query,ThreadModel.class).build();

        adapter = new ThreadAdapter(options);

        threads_list_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        threads_list_recycler_view.setAdapter(adapter);
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
