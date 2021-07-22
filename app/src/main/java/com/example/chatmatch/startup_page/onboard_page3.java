package com.example.chatmatch.startup_page;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.chatmatch.Database.LocalUser;
import com.example.chatmatch.R;
import com.example.chatmatch.User.ProfilePhotoActivity;
import com.example.chatmatch.User.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class onboard_page3 extends AppCompatActivity {

    Button navigateBtn;
    private ArrayList<String> Output;

    StorageReference storageReference;
    CircleImageView display_picture;
    Uri imageUri;
    ImageButton getPhoto;


    SharedPreferences sp;

    String userId;
    SharedPreferences sp3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_screen3);

        display_picture = findViewById(R.id.profile_picture_imageView2);

        getPhoto = findViewById(R.id.get_img);

        sp = getSharedPreferences("userPref1", Context.MODE_PRIVATE);



        getPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });






        sp3 = getSharedPreferences("userPref1", Context.MODE_PRIVATE);

        String test = sp3.getString("selectedGender", "-1");

        Log.d("test value: ", test+"");



        Output = new ArrayList<>();
        navigateBtn = findViewById(R.id.testCore);

        Bundle bundle = getIntent().getExtras();
        Output = bundle.getStringArrayList("userDetails");


//        for (int i = 0; i < Output.size(); i++)
//        {
//            Log.d("last array values", Output.get(i));
//        }

        navigateBtn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {


                        uploadImage();
                        Intent intent = new Intent(onboard_page3.this, onboard_page4_pulse.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    }
                });

    }

    private void pickImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }


    public void uploadImage(){

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
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null){

            imageUri = data.getData();
            display_picture.setImageURI(imageUri);

        }
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}