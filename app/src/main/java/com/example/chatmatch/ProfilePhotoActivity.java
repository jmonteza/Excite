package com.example.chatmatch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.File;

public class ProfilePhotoActivity extends AppCompatActivity {

    private int SELECT_IMAGE_CODE = 1;
    private ImageView profile_photo_iv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture_signup);
        // profile_photo_iv = findViewById(R.id.profile_picture_imageView);
    }

    public void chooseFromGallery(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_IMAGE_CODE);

        // Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // if (intent.resolveActivity(getPackageManager()) != null){
        //     startActivityForResult(intent, SELECT_IMAGE_CODE);
        // }

    }

    // public void uploadPhotoToFirebaseStorage(View v){
    //
    // }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_IMAGE_CODE) {
               Uri selectedImageURI = data.getData();
               File myFile = new File(String.valueOf(selectedImageURI));
               String selectedImagePath = myFile.getPath();
                Glide.with(this).load(selectedImagePath).into(profile_photo_iv);
            }

        }

    }
}
