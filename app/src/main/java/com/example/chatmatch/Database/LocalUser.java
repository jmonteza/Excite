package com.example.chatmatch.Database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.chatmatch.User.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import static com.squareup.okhttp.internal.Internal.instance;

public class LocalUser {

    public final static String COLLECTION_USERPROFILE = "userProfile";
    //TODO: will need to delegate firestore controller to another class
    private FirestoreController fireStoreController;

    private FirebaseFirestore dbInstance = null;
    private static Context applicationContext;
    private static LocalUser instance = null;
    private ArrayList<String> curr_data;
    SharedPreferences sp3;
    String userId;

    public LocalUser() {

    }


    /**
     * Get instance of the singleton
     * @return
     * LocalUser - instance of singleton
     */
    public static LocalUser getInstance(){
        if (instance == null){
            instance = new LocalUser();
        }
        return instance;
    }


    public static void setContext(Context context) {
        applicationContext = context;
    }


    public void saveLocalData(String uri, String email, String password, String birthDay, String firstName, String gender, String interest_1){
        fireStoreController = new FirestoreController();

        if (applicationContext == null){
            Log.d("applicationContext", "appContext needs to be set to save data");
            return;
        }

        SharedPreferences prefs = applicationContext.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("email", email);
        edit.putString("password", password);
        edit.putString("birthday", birthDay);
        edit.putString("firstName", firstName);
        edit.putString("gender", gender);
        edit.putString("interest", interest_1);
        edit.putString("uri", uri);
        //no boolean returned - applied asynchronously



        //#TODO: use dbadapter - to in
        dbInstance = FirebaseFirestore.getInstance();

        String userId = getuserProfileId();

        edit.putString("userId", userId);
        edit.apply();
        //TODO: will need to delegate uploading user data to another class

        //creating new user
        User user = new User(uri, userId, email, password, birthDay, firstName, gender, interest_1);
        user.setUserId(userId);
        DatabaseAdapter dbAdapter;
        dbAdapter = new FirestoreAdapter();

        dbAdapter.saveNewUserProfile(user);
//        fireStoreController.uploadData(userId, email, password, birthDay, firstName, gender, interest_1, new FirestoreController.FireStoreUploadCallback() {
//            @Override
//            public void onCallback() {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                }).start();
//            }
//        }, new FirestoreController.FireStoreUploadFailCallback(){
//            @Override
//            public void onCallback() {
//                Toast.makeText(applicationContext, "The database cannot be accessed at this point, please try again later. Thank you.", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public static void saveLocalData(String email, String password){
        if (applicationContext == null){
            Log.d("applicationContext", "appContext needs to be set to save data");
            return;
        }

        SharedPreferences prefs = applicationContext.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("email", email);
        edit.putString("password", password);
        //no boolean returned - applied asynchronously
        edit.apply();
    }



    /**
     * Generate a new profile Id from firebase (avoids collisions, always unique - based on timestamp)
     * @return
     *      String - the Id which can be used for a new profile
     */
    public String getuserProfileId() {


        sp3 = applicationContext.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        String userIds = sp3.getString("userId", "-1");
        Log.d("real user id", userIds+"");


        //ensure same userId
        if (userIds != null)
        {
            return userIds;
        }
        else{
            CollectionReference ref = dbInstance.collection(COLLECTION_USERPROFILE);
            return ref.document().getId();
        }





    }
}
