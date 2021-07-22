package com.example.chatmatch.Database;

import com.example.chatmatch.User.User;

import java.util.ArrayList;

public interface MyCallback {
    void onCallback(ArrayList<User> user);
}