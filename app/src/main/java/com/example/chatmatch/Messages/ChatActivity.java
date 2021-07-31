package com.example.chatmatch.Messages;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.example.chatmatch.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.GeneralSecurityException;

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

        try {
            setUpRecyclerView();
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpRecyclerView() throws GeneralSecurityException, IOException {
        // Query

        // Intent intent = getIntent();
        //
        // Log.d(TAG, "THREAD ID: " + thread_id);

        // SharedPreferences sharedPref = getSharedPreferences("ExciteEncryptedSharedPref", MODE_PRIVATE);

        Context context = getApplicationContext();
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


        String thread_id = sharedPreferences.getString("thread_id", "");


        assert thread_id != null;
        messagesRef  = db.collection("threads").document(thread_id).collection("messages");

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
        String sender_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ChatModel message = new ChatModel(sender_uid, "Right", message_value);
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
