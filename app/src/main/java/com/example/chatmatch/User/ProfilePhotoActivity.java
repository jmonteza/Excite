package com.example.chatmatch.User;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.chatmatch.Discover.discover_filter;
import com.example.chatmatch.Menu.MenuController;
import com.example.chatmatch.R;
import com.example.chatmatch.Util.FirebaseUtil;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfilePhotoActivity extends AppCompatActivity {

    private int SELECT_IMAGE_CODE = 1;
    private ImageView profile_photo_iv;
    private FirebaseStorage storage;
    private static final String TAG = "FirebaseStorage";
    private Bitmap bitmap = null;
    private MenuController menuController;
    private BottomNavigationView bottomNavigationView;

    private ImageButton reportIssue;
    private ImageButton nvgateFilter;

    private TextView displayName;

    ImageButton nvgteFeedback;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        overridePendingTransition(0, 0);
        profile_photo_iv = findViewById(R.id.profile_picture_imageView);

        String photoURL = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();
        Glide.with(this).load(photoURL).into(profile_photo_iv);

        displayName = findViewById(R.id.userName);
        displayName.setText(FirebaseUtil.getAuth().getCurrentUser().getDisplayName());

        storage = FirebaseStorage.getInstance();
        int id  = R.id.userprofile;



        // Menu Navigation
        bottomNavigationView = findViewById(R.id.bottomNav);

        menuController = new MenuController(ProfilePhotoActivity.this,bottomNavigationView, id);
        menuController.useMenu();

        nvgateFilter = findViewById(R.id.nvgateFilter);
        nvgateFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new discover_filter().show(getSupportFragmentManager(), "filterFragment");
            }
        });
        nvgteFeedback = findViewById(R.id.feedbackFilter);

        nvgteFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FeedbackFragment().show(getSupportFragmentManager(), "feedbackFragment");
            }
        });

        reportIssue = findViewById(R.id.reportFilter);
        reportIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new report_issue().show(getSupportFragmentManager(), "issueFragment");
            }
        });
    }

    public void chooseFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_IMAGE_CODE);

        // Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // if (intent.resolveActivity(getPackageManager()) != null){
        //     startActivityForResult(intent, SELECT_IMAGE_CODE);
        // }

    }

     private void uploadPhotoToFirebaseStorage(Bitmap bitmap){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            User user = new User();
            String curr_userId = user.getUserId();
//            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            StorageReference reference = storage.getReference().child("images").child("profile")
                    .child(curr_userId + ".jpeg");

            reference.putBytes(baos.toByteArray()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.e(TAG,"onSuccess");
                    getDownloadUrl(reference);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Log.e(TAG, "onFailure", e.getCause());
                }
            });
     }


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_IMAGE_CODE) {
               Uri selectedImageURI = data.getData();
               // File myFile = new File(String.valueOf(selectedImageURI));
               // String selectedImagePath = myFile.getPath();
               // String selectedImagePath = getRealPathFromURI(selectedImageURI);

               // Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath);

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImageURI);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Glide.with(this).load(selectedImageURI).into(profile_photo_iv);


            }

        }

    }

    // public String getRealPathFromURI(Uri uri){
    //     Cursor cursor = getContentResolver().query(uri, null, null, null, null);
    //     cursor.moveToFirst();
    //     int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
    //     return cursor.getString(idx);
    // }

    private void getDownloadUrl(StorageReference reference){
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d(TAG,"onSuccess: " + uri);
                Toast.makeText(ProfilePhotoActivity.this, uri.toString(), Toast.LENGTH_SHORT).show();
                setUserProfilePictureUrl(uri);
            }
        });
    }


    public void savePhoto(View v){
        uploadPhotoToFirebaseStorage(bitmap);
    }

    private void setUserProfilePictureUrl(Uri uri){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().setPhotoUri(uri).build();

        if (user != null) {
            user.updateProfile(request)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(ProfilePhotoActivity.this, "Picture updated successfully.", Toast.LENGTH_SHORT).show();
                            goToProfile();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(ProfilePhotoActivity.this, "Picture update failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void goToProfile(){
        Intent intent = new Intent(ProfilePhotoActivity.this, UserProfile.class);
        startActivity(intent);
    }

    public void chooseFromGallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_IMAGE_CODE);
    }
}
