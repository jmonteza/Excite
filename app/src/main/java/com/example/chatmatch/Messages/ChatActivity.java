package com.example.chatmatch.Messages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

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

    private EditText message_et;

    private LinearLayoutManager layout_manager;

    private CollectionReference messagesRef;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messages_list_recycler_view = findViewById(R.id.messages_list_recyclerView);
        db = FirebaseFirestore.getInstance();

        // send_btn = findViewById(R.id.send_message_button);
        message_et = findViewById(R.id.message_edit_text);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        // Query

        Intent intent = getIntent();
        String thread_id = intent.getStringExtra("thread_id");
        Log.d(TAG, "THREAD ID: " + thread_id);
        messagesRef  = db.collection("threads").document("ARDs9yqoqajh5O14XNxS").collection("messages");

        Query query = messagesRef.orderBy("timestamp", Query.Direction.ASCENDING);

        // Recycler Options
        FirestoreRecyclerOptions<ChatModel> options = new FirestoreRecyclerOptions.Builder<ChatModel>()
                .setQuery(query, ChatModel.class).build();

        adapter = new ChatAdapter(options);
        layout_manager = new LinearLayoutManager(this);
        layout_manager.setReverseLayout(false);
        messages_list_recycler_view.setLayoutManager(layout_manager);
        messages_list_recycler_view.setAdapter(adapter);
        scrollToBottom();
    }

    private void scrollToBottom() {
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int messageCount = adapter.getItemCount();
                int lastVisiblePosition = layout_manager.findLastVisibleItemPosition();

                if (lastVisiblePosition == -1 || positionStart >= (messageCount - 1)) {
                    layout_manager.scrollToPosition(positionStart);
                }
            }
        });
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

    public void sendMessage(View v) {
        String message_value = message_et.getText().toString();
        ChatModel message = new ChatModel("Sender UID", "Right", message_value);
        messagesRef.add(message).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "DocumentSnapshot successfully written!");
                message_et.getText().clear();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.w(TAG, "Error writing document");
            }
        });
    }
}
