package com.example.chatmatch.Database;


import com.example.chatmatch.ObjectManager.DatabaseListener;
import com.example.chatmatch.User.User;

import java.util.ArrayList;

public abstract class DatabaseAdapter {

    public abstract void addListener(String CollectionName, DatabaseListener listener);
    public abstract void saveNewUserProfile(User user);
    public abstract void updateProfile(User profile);
    public abstract void loadProfiles(MyCallback myCallback);
}
