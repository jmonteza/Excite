package com.example.chatmatch.Authentication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import org.jetbrains.annotations.NotNull;

public class FragmentAdapter extends FragmentStateAdapter {

    /**
     * @param fragmentManager of {@link ViewPager2}'s host
     * @param lifecycle       of {@link ViewPager2}'s host
     * @see FragmentStateAdapter#FragmentStateAdapter(FragmentActivity)
     * @see FragmentStateAdapter#FragmentStateAdapter(Fragment)
     */
    public FragmentAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    /**
     * Provide a new Fragment associated with the specified position.
     * <p>
     * The adapter will be responsible for the Fragment lifecycle:
     * <ul>
     *     <li>The Fragment will be used to display an item.</li>
     *     <li>The Fragment will be destroyed when it gets too far from the viewport, and its state
     *     will be saved. When the item is close to the viewport again, a new Fragment will be
     *     requested, and a previously saved state will be used to initialize it.
     * </ul>
     *
     * @param position
     * @see ViewPager2#setOffscreenPageLimit
     */
    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        // return null;
        switch (position){
            case 0:
                return new SignupFragment();
            case 1:
                return new LoginFragment();
        }
        return new SignupFragment();
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return 2;
    }
}

