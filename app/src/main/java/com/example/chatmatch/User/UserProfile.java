package com.example.chatmatch.User;

import static com.example.chatmatch.R.layout.activity_profile;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatmatch.Matches.MatchFUIAdapter;
import com.example.chatmatch.Menu.MenuController;
import com.example.chatmatch.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class UserProfile extends AppCompatActivity {


    private ImageView profileImageView;

    private TextView profileUserIdTextView;

    private TextView profileNameTextView;

    private TextView profileAgeTextView;

    private final static String TAG = "UserProfile";

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
        setContentView(activity_profile);
        overridePendingTransition(0, 0);

        db = FirebaseFirestore.getInstance();

        int id  = R.id.userprofile;
        bottomNavigationView = findViewById(R.id.bottomNav);
        menuController = new MenuController(UserProfile.this,bottomNavigationView, id);
        menuController.useMenu();

        profileUserIdTextView = findViewById(R.id.profile_user_id);
        profileNameTextView = findViewById(R.id.profile_name);
        profileAgeTextView = findViewById(R.id.profile_age);

        profileImageView = findViewById(R.id.my_profile_picture_imageView);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null){
            String url = "https://firebasestorage.googleapis.com/v0/b/chatmatch-b8ec9.appspot.com/o/images%2Fprofile%2Ffemam2.jpg?alt=media&token=1150ce58-d38a-4a84-9e1d-2f4f4369915d";
            if (user.getPhotoUrl() != null){
                Glide.with(this).load(user.getPhotoUrl()).into(profileImageView);
            }
            else{
                Glide.with(this).load(url).into(profileImageView);
            }
        }

        assert user != null;
        profileUserIdTextView.setText(user.getUid());
//        profileNameTextView.setText(user.getDisplayName());


        final DocumentReference docRef = db.collection("userProfile").document(user.getUid());

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Log.w(TAG, "Listen failed", error);
                    return;
                }
                if (value != null && value.exists()){

//                    Log.d(TAG, "Current data: " + );
                    profileNameTextView.setText(new StringBuilder().append(Objects.requireNonNull(value.get("firstName")).toString()).append(", ").toString());
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });
    }
}
