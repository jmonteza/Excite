package com.example.chatmatch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SlidePagerAdapter  extends FragmentStatePagerAdapter {


    private List<Fragment> fragList;
    public SlidePagerAdapter(@NonNull @NotNull FragmentManager fm, List<Fragment> fragList) {
        super(fm);
        this.fragList = fragList;
    }


    @Override
    public Fragment getItem(int position){
        return fragList.get(position);
    }


    @Override
    public int getCount() {
        return fragList.size();
    }



}
