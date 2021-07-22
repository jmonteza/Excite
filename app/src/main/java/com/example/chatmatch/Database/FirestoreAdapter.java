package com.example.chatmatch.Database;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chatmatch.ObjectManager.DatabaseListener;
import com.example.chatmatch.User.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class FirestoreAdapter extends DatabaseAdapter{


    protected HashMap<String, ArrayList<DatabaseListener>> listeners;


    //TODO: migrate fully to using firestore adapter
    private FirestoreController firestoreController;
    public final static String COLLECTION_PROFILE = "userProfile";
    private ListenerRegistration profileSnapshotListener = null;
    private FirebaseFirestore dbInstance = null;

    private ArrayList<User> profiles = new ArrayList<>();
    public FirestoreAdapter(){
        listeners = new HashMap<>();
        dbInstance = FirebaseFirestore.getInstance();
    }



    /**
     * Load all profiles in the database (At app startup)
     * Triggers DB_EVENT_PROFILE_LOADED_ON_START and DB_EVENT_PROFILE_ON_START_LOAD_FAILURE
     */
    @Override
    public void loadProfiles(MyCallback myCallback) {
        CollectionReference ref = dbInstance.collection(COLLECTION_PROFILE);
        ref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for( QueryDocumentSnapshot doc : queryDocumentSnapshots ) {
                    User user = processProfileSnapshot(doc);
                    profiles.add(user);
                }
                myCallback.onCallback(profiles);
                sendListenerNotification(COLLECTION_PROFILE, DatabaseListener.DB_EVENT_PROFILE_LOADED_ON_START, profiles);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                sendListenerNotification(COLLECTION_PROFILE, DatabaseListener.DB_EVENT_PROFILE_ON_START_LOAD_FAILURE, null);
            }
        });

    }


    /**
     * Function that will break down the document from userProfile database
     * @param
     */
    public User processProfileSnapshot(QueryDocumentSnapshot doc) {
        User userProfile = doc.toObject(User.class);
        return userProfile;
    }

    /**
     * notify Listeners of a specific database collection/table about certain events as indicated by the eventCode
     * @param collectionName
     *      String - name of the collection/table associated with the event
     * @param eventCode
     *      int - code associated with the event that happened
     * @param data
     *      data - any relevant data to update the listener with
     */
    public void sendListenerNotification(String collectionName, int eventCode, Object data) {
        if( listeners.get(collectionName) == null ) {
            return;
        }
        for( DatabaseListener listener : listeners.get(collectionName) ) {
            listener.onDatabaseEvent(eventCode, data);
        }
    }

    public void addListener(String collectionName, DatabaseListener databaseListener) {

        ArrayList<DatabaseListener> collectionListener = listeners.get(collectionName);
        if(collectionListener == null) {
            collectionListener = new ArrayList<>();
            listeners.put(collectionName, collectionListener);
        }

        collectionListener.add(databaseListener);

        switch (collectionName) {
            case COLLECTION_PROFILE:
                if (profileSnapshotListener == null) {
                    startListeningForProfileChanges();
                }
                break;
            default:
                break;
        }

    }

    /**
     * start listening to Profile collection for changes
     */
    public void startListeningForProfileChanges() {
        CollectionReference ref = dbInstance.collection(COLLECTION_PROFILE);
        profileSnapshotListener = ref.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                ArrayList<User> profiles = new ArrayList<>();
                for( QueryDocumentSnapshot doc : value ) {
                    User user = processProfileSnapshot(doc);
                    profiles.add(user);
                }
                sendListenerNotification(COLLECTION_PROFILE, DatabaseListener.DB_EVENT_PROFILE_CHECK_DATA_CHANGED, profiles);
            }
        });
    }

    /**
     * Save a new profile in the database
     * @param userProfile
     *      Profile - profile object
     */
    @Override
    public void saveNewUserProfile(User userProfile) {
        CollectionReference ref = dbInstance.collection(COLLECTION_PROFILE);
        ref.document(userProfile.getUserId()).set(userProfile).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                sendListenerNotification(COLLECTION_PROFILE,  DatabaseListener.DB_EVENT_PROFILE_SAVE_NEWPROFILE_SUCCESS, userProfile);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                sendListenerNotification(COLLECTION_PROFILE,  DatabaseListener.DB_EVENT_PROFILE_SAVE_NEWPROFILE_FAILURE, userProfile);
            }
        });
    }

    /**
     * Update a single profile in the database
     * Triggers DB_EVENT_UPDATE_PROFILE_SUCCESS and DB_EVENT_UPDATE_PROFILE_FAILURE
     * @param profile
     *      @see User - updates current user profile
     */
    @Override
    public void updateProfile(User profile) {
        CollectionReference ref = dbInstance.collection(COLLECTION_PROFILE);
        ref.document(profile.getUserId()).set(profile).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                sendListenerNotification(COLLECTION_PROFILE, DatabaseListener.DB_EVENT_UPDATE_PROFILE_SUCCESS, profile);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                sendListenerNotification(COLLECTION_PROFILE, DatabaseListener.DB_EVENT_UPDATE_PROFILE_FAILURE, profile);
            }
        });
    }


}
