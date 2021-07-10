package com.example.chatmatch.User;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.chatmatch.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfile extends AppCompatActivity {


    private ImageView profileImageView;

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
        setContentView(R.layout.activity_profile);

        profileImageView = findViewById(R.id.my_profile_picture_imageView);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null){
            if (user.getPhotoUrl() != null){
                Glide.with(this).load(user.getPhotoUrl()).into(profileImageView);
            }
        }

    }
}
