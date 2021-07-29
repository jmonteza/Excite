package com.example.chatmatch.startup_page;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chatmatch.R;
import com.example.chatmatch.Util.FirebaseUtil;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class onboard_page3 extends AppCompatActivity {

    Button navigateBtn;
    private ArrayList<String> Output;

    StorageReference storageReference;
    CircleImageView display_picture;
    Uri imageUri;
    ImageButton getPhoto;

    SharedPreferences sp1;

    SharedPreferences sp;

    String userId;
    SharedPreferences sp3;
    // private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);

    private final String TAG = "Onboard Page 3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_screen3);

        display_picture = findViewById(R.id.profile_picture_imageView2);

        getPhoto = findViewById(R.id.get_img);

        // sp = getSharedPreferences("userPref1", Context.MODE_PRIVATE);


        getPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });


        // sp3 = getSharedPreferences("userPref1", Context.MODE_PRIVATE);

        // String test = sp3.getString("selectedGender", "-1");

        // Log.d("test value: ", test+"");


        // Output = new ArrayList<>();
        navigateBtn = findViewById(R.id.testCore);

        // Bundle bundle = getIntent().getExtras();
        // Output = bundle.getStringArrayList("userDetails");


//        for (int i = 0; i < Output.size(); i++)
//        {
//            Log.d("last array values", Output.get(i));
//        }

        sp1 = getSharedPreferences("userPref1", Context.MODE_PRIVATE);
        navigateBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {


                        uploadImage();

                        String url = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();
                        String id = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                        // sp1 = getSharedPreferences("userPref1", Context.MODE_PRIVATE);
                        // String uri = sp1.getString("uri", "-1");
                        // Log.d("uri value", uri);

                        Map<String, Object> data = new HashMap<>();
                        data.put("photoURL", url);

                        FirebaseUtil.getFirestore().collection("userProfile").document(id).update(data)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d(TAG, "PhotoURL Added");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "PhotoURL Upload Failed");
                                    }
                                });

                        Intent intent = new Intent(onboard_page3.this, onboard_page4_pulse.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    }
                });

    }

    private void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }


    public void uploadImage() {

        sp = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        userId = sp.getString("userId", "-1");
        storageReference = FirebaseStorage.getInstance().getReference().child("images").child("profile")
                .child(userId + ".jpeg");


        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        display_picture.setImageURI(null);
//                        Toast.makeText(onboard_page3.this, "success", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

//                Toast.makeText(onboard_page3.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });

        getDownloadUrl(storageReference, new urlCallback() {

            @Override
            public void onCallback(String value) {
                SharedPreferences.Editor editor = sp1.edit();
                Log.d("value", value);
                editor.putString("uri", value);
                editor.apply();
            }


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null) {

            imageUri = data.getData();
            display_picture.setImageURI(imageUri);

        }
    }


    private void getDownloadUrl(StorageReference reference, urlCallback urlCallback) {
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("TAG", "onSuccess: " + uri);


                urlCallback.onCallback(uri.toString());
                Toast.makeText(onboard_page3.this, uri.toString(), Toast.LENGTH_SHORT).show();
                setUserProfilePictureUrl(uri);
            }
        });
    }

    private void setUserProfilePictureUrl(Uri uri) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().setPhotoUri(uri).build();

        if (user != null) {
            user.updateProfile(request)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(onboard_page3.this, "Picture updated successfully.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(onboard_page3.this, "Picture update failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}