package com.example.chatmatch.Messages;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

public class chatActivity extends AppCompatActivity {
    /**
     * {@inheritDoc}
     * <p>
     * Perform initialization of all fragments.
     *
     * @param savedInstanceState
     */

    private RecyclerView messages_list;
    private FirebaseFirestore db;
    private  FirestoreRecyclerAdapter adapter;
    private static final String TAG = "chatActivity";
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messages_list = findViewById(R.id.messages_list_recyclerView);
        db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("messages").document("7k13Msy1Hjgjo5gMUb7q");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


        // Query
        Query query = db.collection("messages");
        Log.d(TAG, String.valueOf(query));

        // Recycler Options
        FirestoreRecyclerOptions<chatModel> options = new FirestoreRecyclerOptions.Builder<chatModel>()
                .setQuery(query, chatModel.class).build();

        adapter = new FirestoreRecyclerAdapter<chatModel, chatViewHolder>(options) {

            public @NotNull chatViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sender_layout_single, parent, false);
                return new chatViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull @NotNull chatViewHolder holder, int position, @NonNull @NotNull chatModel model) {
                holder.message_tv.setText(model.getMessage());
            }
        };
        // View holder
        messages_list.setHasFixedSize(true);
        messages_list.setLayoutManager(new LinearLayoutManager(this));
        messages_list.setAdapter(adapter);
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
