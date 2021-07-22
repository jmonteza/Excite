package com.example.chatmatch.ObjectManager;


import com.example.chatmatch.Database.DatabaseAdapter;
import com.example.chatmatch.User.User;

import java.util.ArrayList;

/*
    - Singleton class that Manages all profiles
 */
public class ProfileManager implements DatabaseListener {

    private static ProfileManager instance = null;
    private static DatabaseAdapter databaseAdapter = null;
    private static ArrayList<ProfileCreateEventListener> listeningViewsOnCreate;
    private static ArrayList<ProfileDataLoadedEventListener> listeningViewsOnLoad;
    private static ArrayList<ProfileOnChangeEventListener> listeningViewsOnChange;

    private static ArrayList<User> profileList;

    private ProfileManager(){
        profileList = new ArrayList<>();
    }

    public static ProfileManager getInstance() {
        if( instance == null ) {
            instance = new ProfileManager();
        }
        return instance;
    }


    public ArrayList<User> getProfileList() {
        return profileList;
    }

    public void setDatabaseAdapter(DatabaseAdapter adapter) {
        databaseAdapter = adapter;
        adapter.addListener("userProfile", instance);
    }


    /**
     * notiify listeners that changes as occured
     */
    private void notifyListenerProfilesLoaded() {
        for(ProfileDataLoadedEventListener listener : listeningViewsOnLoad) {
            listener.onProfileDataLoaded();
        }
    }

    public interface ProfileCreateEventListener {
        void onCreateProfileFailed(User failedToCreate);
        void onCreateProfileSuccess(User createdProfile);
    }


    /*


        Check for profile changes and updates as needed
     */
    private void checkProfileDataChange(ArrayList<User> newData) {
        /*
         * Might just be easier to load everything again instead of checking for changes
         */
        profileList = newData;
        notifyListenerProfileDataChanged();
    }

    /*

        loads profiles in
     */
    public void loadInitialProfileData(ArrayList<User> data) {
        profileList = data;
        notifyListenerProfilesLoaded();
    }

    /**
     * notiify listeners of profile creation success
     */
    private void notifyListenerCreateProfileSuccess(User createdProfile) {
        for(ProfileCreateEventListener listener : listeningViewsOnCreate) {
            listener.onCreateProfileSuccess(createdProfile);
        }
    }

    /**
     * notiify listeners of create profile failure
     */
    private void notifyListenerCreateProfileFailure(User failedProfile) {
        for(ProfileCreateEventListener listener : listeningViewsOnCreate) {
            listener.onCreateProfileFailed(failedProfile);
        }
    }

    /**
     * Triggered when a database event occurs (Indicating we should alter our data)
     * @param eventCode
     *      int - Database event code (found in DatabaseListener interface)
     * @param data
     *      Object - data associated with event (must Cast to appropriate data type)
     */
    @Override
    public void onDatabaseEvent(int eventCode, Object data) {
        switch(eventCode) {
            case DB_EVENT_PROFILE_CHECK_DATA_CHANGED:
                checkProfileDataChange((ArrayList<User>) data);
                break;
            case DB_EVENT_PROFILE_LOADED_ON_START:
                loadInitialProfileData((ArrayList<User>)data);
                break;
            case DB_EVENT_PROFILE_ON_START_LOAD_FAILURE:
                break;
            case DB_EVENT_PROFILE_SAVE_NEW_SUCCESS:
                notifyListenerCreateProfileSuccess((User)data);
                break;
            case DB_EVENT_PROFILE_SAVE_NEW_FAILURE:
                notifyListenerCreateProfileFailure((User)data);
                break;
            case DB_EVENT_UPDATE_PROFILE_SUCCESS:
                notifyListenerProfileDataChanged();
                break;
            case DB_EVENT_UPDATE_PROFILE_FAILURE:
                break;
        }
    }

    /**
     * Listen for initial profile load succes event
     */
    public interface ProfileDataLoadedEventListener {
        void onProfileDataLoaded();
    }

    /**
     * Listen for profile changes in the database
     */
    public interface ProfileOnChangeEventListener {
        void onProfileDataChanged();
    }

    /**
     * notiify listeners of data change
     */
    private void notifyListenerProfileDataChanged() {
        for(ProfileOnChangeEventListener listener : listeningViewsOnChange) {
            listener.onProfileDataChanged();
        }
    }
}
