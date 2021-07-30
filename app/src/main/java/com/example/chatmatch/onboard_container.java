package com.example.chatmatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chatmatch.fragments.onboard1;
import com.example.chatmatch.fragments.onboard2;

import java.util.ArrayList;
import java.util.List;

public class onboard_container extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    swipeDiabledViewPager viewPager;

    private Button testbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboard_page_container);

        List<Fragment> list = new ArrayList<>();
        list.add(new onboard1());
        list.add(new onboard2());

        LayoutInflater factory = getLayoutInflater();
        View regisText = factory.inflate(R.layout.onboarding_screen1, null);

        EditText test = (EditText) regisText.findViewById(R.id.firstName);

//        String password = test.getText().toString().trim();
//        Log.d("correct", password.toString());
        testbtn = regisText.findViewById(R.id.uploadDoneBtn);

        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click", "clicked");
            }
        });
//        String val = test.getText().toString();
//
//        Log.d("val", val);
//        if (val.matches("")){
//            viewPager = findViewById(R.id.pager);
//            viewPager.setPagingEnabled(false);
//        }

        pager = findViewById(R.id.pager);
        viewPager = (swipeDiabledViewPager) findViewById(R.id.pager);
        viewPager.setPagingEnabled(true);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);

        pager.setAdapter(pagerAdapter);
    }
}