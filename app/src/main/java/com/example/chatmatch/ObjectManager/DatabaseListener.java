package com.example.chatmatch.ObjectManager;

public interface DatabaseListener {

    int DB_EVENT_UPDATE_PROFILE_SUCCESS = -1;
    int DB_EVENT_UPDATE_PROFILE_FAILURE = 0;

    int DB_EVENT_PROFILE_SAVE_NEWPROFILE_SUCCESS = 2;
    int DB_EVENT_PROFILE_SAVE_NEWPROFILE_FAILURE = 3;

    int DB_EVENT_PROFILE_LOADED_ON_START = 4;
    int DB_EVENT_PROFILE_ON_START_LOAD_FAILURE = 5;
    int DB_EVENT_PROFILE_CHECK_DATA_CHANGED = 6;
    int DB_EVENT_PROFILE_SAVE_NEW_SUCCESS = 7;
    int DB_EVENT_PROFILE_SAVE_NEW_FAILURE = 9;

    void onDatabaseEvent(int eventCode, Object data);
}
