package com.example.chatmatch.Discovery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.R;
import com.example.chatmatch.User.User;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

public class discovery extends AppCompatActivity {


    private FirebaseFirestore firebaseFirestore;
    private RecyclerView firestoreRecycler;

    private FirestoreRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        firebaseFirestore = firebaseFirestore.getInstance();
        firestoreRecycler = findViewById(R.id.recycler_view);

        //Query
        Query  query = firebaseFirestore.collection("test");

        //Recyler options
        FirestoreRecyclerOptions<testUSer> options = new FirestoreRecyclerOptions.Builder<testUSer>()
                .setQuery(query, testUSer.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<testUSer, UserViewHolder>(options) {
            @NonNull
            @NotNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_row_item,
                        parent, false);
                return new UserViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull @NotNull discovery.UserViewHolder holder, int position, @NonNull @NotNull testUSer model) {

                Log.d("first name", model.getFirstName()+"");
                Log.d("here", "i got here");
                holder.userName.setText(model.getFirstName());
            }
        };

        firestoreRecycler.setHasFixedSize(true);
        firestoreRecycler.setLayoutManager(new LinearLayoutManager(this));
        firestoreRecycler.setAdapter(adapter);

        //viewholder
    }

    private class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView userName;

        public UserViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.usrCardname);

        }
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
