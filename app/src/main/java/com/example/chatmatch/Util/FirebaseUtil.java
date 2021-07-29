package com.example.chatmatch.Util;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FirebaseUtil {
    private static FirebaseFirestore FIRESTORE;
    private static FirebaseAuth AUTH;
    private static String USER_ID;

    public static FirebaseFirestore getFirestore() {
        if (FIRESTORE == null) {
            FIRESTORE = FirebaseFirestore.getInstance();
        }
        return FIRESTORE;
    }

    public static FirebaseAuth getAuth() {
        if (AUTH == null) {
            AUTH = FirebaseAuth.getInstance();
        }
        return AUTH;
    }

    public static String getUserID(){
        if (AUTH.getCurrentUser() != null){
            USER_ID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        }
        return USER_ID;
    }

    // public static void uploadUserInfo(String key, Object value){
    //
    // }

    public Map<String, Object> data(Array array){
        Map<String, Object> data = new HashMap<>();
        return data;
    }

    public static void uploadUserData(String id, Map<String, Object> data, String TAG){
        FirebaseUtil.getFirestore().collection("userProfile").document(id).update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Successful User Data Upload");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "User Data Upload Failed");
                    }
                });
    }


}
