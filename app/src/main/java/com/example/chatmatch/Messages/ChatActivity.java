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

public class ChatActivity extends AppCompatActivity {
    /**
     * {@inheritDoc}
     * <p>
     * Perform initialization of all fragments.
     *
     * @param savedInstanceState
     */

    private RecyclerView messages_list_recycler_view;

    private FirebaseFirestore db;

    private ChatAdapter adapter;

    private static final String TAG = "ChatActivity";

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messages_list_recycler_view = findViewById(R.id.messages_list_recyclerView);
        db = FirebaseFirestore.getInstance();
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        // Query
        CollectionReference messagesRef = db.collection("messages");

        Query query = messagesRef.orderBy("timestamp", Query.Direction.ASCENDING);

        // Recycler Options
        FirestoreRecyclerOptions<ChatModel> options = new FirestoreRecyclerOptions.Builder<ChatModel>()
                .setQuery(query, ChatModel.class).build();

        adapter = new ChatAdapter(options);
        messages_list_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        messages_list_recycler_view.setAdapter(adapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
