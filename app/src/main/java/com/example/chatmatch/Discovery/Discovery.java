package com.example.chatmatch.Discovery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.GeneralSecurityException;
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


        setUpRecyclerView();
    }


    private void setUpRecyclerView() {
        //Query
        Query query = db.collection("userProfile");

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
                String discovered_user_id  = documentSnapshot.getId();
                waveAt(discovered_user_id);
                Toast.makeText(Discovery.this, discovered_user_id, Toast.LENGTH_SHORT).show();
            }
        });


        discoveryRecyclerView = findViewById(R.id.recycler_view);
        discoveryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        discoveryRecyclerView.setAdapter(adapter);

    }

    private void winkAt(String discovered_user_id){
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

    private void waveAt(String discovered_user_id){
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

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(this);
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
        FirebaseAuth.getInstance().removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull @NotNull FirebaseAuth firebaseAuth) {
        if (firebaseAuth.getCurrentUser() == null){
            goToAuthActivity();
        }
    }


    private void goToAuthActivity(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

}
