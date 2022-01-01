package com.example.chatmatch.Discovery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.Authentication.AuthActivity;
import com.example.chatmatch.Menu.MenuController;
import com.example.chatmatch.Model.UserModel;
import com.example.chatmatch.R;
import com.example.chatmatch.Util.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Discovery extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    private FirebaseFirestore db;
    private DiscoveryAdapter adapter;
    private RecyclerView discoveryRecyclerView;
    private MenuController menuController;
    private BottomNavigationView bottomNavigationView;
    private final String TAG = "Discovery Activity";

    // Men or Women
    private String interest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        overridePendingTransition(0, 0);

        db = FirebaseFirestore.getInstance();
        int id = R.id.connect;
        bottomNavigationView = findViewById(R.id.bottomNav);
        menuController = new MenuController(this, bottomNavigationView, id);
        menuController.useMenu();

        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference docRef = db.collection("userProfile").document(user_id);

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.w(TAG, "Listen failed", error);
                }

                if (value != null && value.exists()) {
                    String interest = value.get("interest").toString();
                    setUpRecyclerView(interest);
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });

    }


    private void setUpRecyclerView(String interest) {
        //Query (filter yourself)

        String user_id = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        String interestedGender = (interest.equals("Women")) ? "Female" : "Male";

        Query query = db.collection("userProfile").whereEqualTo("gender", interestedGender).whereNotEqualTo(FieldPath.documentId(), user_id);

        //Recycler options
        FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>()
                .setQuery(query, UserModel.class)
                .build();

        adapter = new DiscoveryAdapter(options);

        adapter.setOnItemClickListener(new DiscoveryAdapter.OnItemClickListener() {
            @Override
            public void onWinkClick(DocumentSnapshot documentSnapshot, int position) throws InterruptedException, GeneralSecurityException, IOException {
                String discovered_user_id = documentSnapshot.getId();
                winkAt(discovered_user_id);
                Toast.makeText(Discovery.this, discovered_user_id, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWaveClick(DocumentSnapshot documentSnapshot, int position) throws InterruptedException, GeneralSecurityException, IOException {
                String discovered_user_id = documentSnapshot.getId();
//                waveAt(discovered_user_id);
                createChatThread(discovered_user_id);
                Toast.makeText(Discovery.this, discovered_user_id, Toast.LENGTH_SHORT).show();
            }
        });


        discoveryRecyclerView = findViewById(R.id.recycler_view);
        discoveryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter.startListening();
        discoveryRecyclerView.setAdapter(adapter);

    }

    private void winkAt(String discovered_user_id) {
        String id = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        Map<String, Object> data = new HashMap<>();
        data.put("winkAt", FieldValue.arrayUnion(discovered_user_id));

        FirebaseUtil.getFirestore().collection("userProfile").document(id).update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Wink At added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Wink At failed");
                    }
                });
    }

    private void waveAt(String discovered_user_id) {
        String id = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        Map<String, Object> data = new HashMap<>();
        data.put("waveAt", FieldValue.arrayUnion(discovered_user_id));

        FirebaseUtil.getFirestore().collection("userProfile").document(id).update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Wave At added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Wave At failed");
                    }
                });
    }

    private void createChatThread(String discovered_user_id) {
        String own_user_id = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        Map<String, Object> data = new HashMap<>();
        data.put("full_name", "Testing");
        data.put("last_message", "Testing new features...");
        data.put("members", Arrays.asList(own_user_id, discovered_user_id));
        data.put("photoURI", "https://firebasestorage.googleapis.com/v0/b/chatmatch-b8ec9.appspot.com/o/images%2Fprofile%2Ffemam1.jpg?alt=media&token=d16c5da5-47a5-4b93-b294-3a4829b865d7");
        data.put("timestamp", FieldValue.serverTimestamp());

        FirebaseUtil.getFirestore().collection("threads").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding document", e);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
        FirebaseAuth.getInstance().removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull @NotNull FirebaseAuth firebaseAuth) {
        if (firebaseAuth.getCurrentUser() == null) {
            goToAuthActivity();
        }
    }


    private void goToAuthActivity() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

}
