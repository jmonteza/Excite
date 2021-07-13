package com.example.chatmatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

public class swipeDiabledViewPager extends ViewPager {


    private boolean enabled;

    public swipeDiabledViewPager(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.enabled){
            return super.onTouchEvent(event);

        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
        if (this.enabled){
            return super.onInterceptTouchEvent(event);

        }

        return false;
    }

    public void setPagingEnabled(boolean enabled){this.enabled = enabled;}

}