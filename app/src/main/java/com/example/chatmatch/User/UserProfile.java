package com.example.chatmatch.User;

import static com.example.chatmatch.R.layout.activity_profile;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatmatch.Matches.MatchFUIAdapter;
import com.example.chatmatch.Matches.MatchesActivity;
import com.example.chatmatch.Menu.MenuController;
import com.example.chatmatch.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfile extends AppCompatActivity {


    private ImageView profileImageView;
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

//        profileImageView = findViewById(R.id.my_profile_picture_imageView);
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

//        if (user != null){
//            if (user.getPhotoUrl() != null){
//                Glide.with(this).load(user.getPhotoUrl()).into(profileImageView);
//            }
//        }

    }
}
