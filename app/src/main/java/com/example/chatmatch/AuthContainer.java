package com.example.chatmatch;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

public class AuthContainer extends AppCompatActivity {
    /**
     * {@inheritDoc}
     * <p>
     * Perform initialization of all fragments.
     *
     * @param savedInstanceState
     */

    ViewPager2 pager2;
    FragmentManager fm;
    FragmentAdapter adapter;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_container);
        pager2 = findViewById(R.id.auth_viewpager_container);
        fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm,getLifecycle());
        pager2.setAdapter(adapter);
    }

    public void goToFrag1(View v){
        pager2.setCurrentItem(0);
    }

    public void goToFrag2(View v){
        pager2.setCurrentItem(1);
    }

}
