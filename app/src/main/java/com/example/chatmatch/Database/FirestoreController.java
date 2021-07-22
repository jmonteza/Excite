package com.example.chatmatch.Database;

import android.util.Pair;

import androidx.annotation.NonNull;

import com.example.chatmatch.User.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class FirestoreController {

    private static final String TAG = "FireStoreController";
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference collectionReference;
    private String testIndicator = "users";
    private HashMap<String, Object> profileData;
    private String existed;

    private void accessor(String indicator){
        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection(indicator);
    }

//    public void readData(ArrayList<String> itemList, String verifier, FireStoreReadCallback fireStoreReadCallback, FireStoreReadFailCallback fireStoreReadFailCallback){
//        itemList.clear();
//        accessor(testIndicator);
//        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()){
//                    for (DocumentSnapshot doc : task.getResult()){
//                        if (verifier.equals("Id")){
//                            existed = doc.getId();
//                        }
//                        else{
//                            existed = doc.getString(verifier);
//                        }
//                        itemList.add(existed);
//                    }
//                    fireStoreReadCallback.onCallback(itemList);
//                }
//                else{
//                    fireStoreReadFailCallback.onCallback();
//                }
//            }
//        });
//    }

//    public void getCertainData(ArrayList<String> itemList, String field, String value, String wanted, FireStoreCertainCallback fireStoreCertainCallback, FireStoreCertainFailCallback fireStoreCertainFailCallback){
//        accessor(testIndicator);
//        collectionReference.whereEqualTo(field, value).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()){
//                    for (QueryDocumentSnapshot doc : task.getResult()){
//                        if (wanted.equals("Id")){
//                            itemList.add(doc.getId());
//                        }
//                        else{
//                            itemList.add(doc.getString(wanted));
//                        }
//                    }
//                    fireStoreCertainCallback.onCallback(itemList);
//                }
//                else{
//                    fireStoreCertainFailCallback.onCallback();
//                }
//            }
//        });
//    }



    public void uploadData(String id, String email, String password, String birthday, String firstName, String gender, String interest, FireStoreUploadCallback fireStoreUploadCallback, FireStoreUploadFailCallback fireStoreUploadFailCallback){
        //TODO: password needs to be encrypted
        accessor(testIndicator);
        profileData = new HashMap<>();
        profileData.put("Name", firstName);
        profileData.put("Password", password);
        profileData.put("Email", email);
        profileData.put("birthDate", birthday);
        profileData.put("gender", gender);
        profileData.put("interest1", interest);
        collectionReference
                .document(id)
                .set(profileData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        fireStoreUploadCallback.onCallback();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        fireStoreUploadFailCallback.onCallback();
                    }
                });
    }

    public void readData(ArrayList<String> itemList, String verifier, FireStoreReadCallback fireStoreReadCallback, FireStoreReadFailCallback fireStoreReadFailCallback){
        itemList.clear();
        accessor(testIndicator);
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot doc : task.getResult()){
                        if (verifier.equals("Id")){
                            existed = doc.getId();
                        }
                        else{
                            existed = doc.getString(verifier);
                        }
                        itemList.add(existed);
                    }
                    fireStoreReadCallback.onCallback(itemList);
                }
                else{
                    fireStoreReadFailCallback.onCallback();
                }
            }
        });
    }
    public interface FireStoreReadCallback{
        void onCallback(ArrayList<String> list);
    }
    public interface FireStoreUploadCallback{
        void onCallback();
    }
    public interface FireStoreReadFailCallback{
        void onCallback();
    }
    public interface FireStoreUploadFailCallback{
        void onCallback();
    }
    public interface FireStoreCheckCallback{
        void onCallback(ArrayList<Pair> list);
    }
    public interface FireStoreCheckFailCallback{
        void onCallback();
    }
    public interface FireStoreCertainCallback{
        void onCallback(ArrayList<String> list);
    }
    public interface FireStoreCertainFailCallback{
        void onCallback();
    }
    public interface FireStoreUpdateCallback{
        void onCallback();
    }
    public interface FireStoreUpdateFailCallback{
        void onCallback();
    }
}
