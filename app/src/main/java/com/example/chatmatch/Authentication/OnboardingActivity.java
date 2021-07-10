package com.example.chatmatch.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chatmatch.R;
import com.example.chatmatch.User.ProfilePhotoActivity;
import com.example.chatmatch.User.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class OnboardingActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {
    private EditText fname_et;
    private EditText age_et;
    private EditText foot_et;
    private EditText inch_et;
    private EditText major_et;
    private Spinner zodiac_sp;
    private Spinner grad_year_sp;
    private RadioGroup gender_rg;
    private RadioButton gender_rb;

    private FirebaseFirestore db;
    private FirebaseUser currentUser;
    private final String TAG = "Firestore";

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        fname_et = findViewById(R.id.fname_signup_editText);
        age_et = findViewById(R.id.age_signup_editText);
        foot_et = findViewById(R.id.foot_signup_editText);
        inch_et = findViewById(R.id.inches_signup_editText);
        major_et = findViewById(R.id.major_signup_editText);
        zodiac_sp = findViewById(R.id.zodiac_spinner);
        grad_year_sp = findViewById(R.id.grad_year_spinner);
        gender_rg = findViewById(R.id.gender_radioGroup);

        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        // Create an ArrayAdapter using the string array and a default zodiac_spinner layout
        ArrayAdapter<CharSequence> zodiac_adapter = ArrayAdapter.createFromResource(this,
                R.array.zodiac_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> grad_year_adapter = ArrayAdapter.createFromResource(this,
                R.array.grad_year_array, android.R.layout.simple_spinner_item);


        // Specify the layout to use when the list of choices appears
        zodiac_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grad_year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the zodiac_adapter to the zodiac_spinner
        zodiac_sp.setAdapter(zodiac_adapter);
        grad_year_sp.setAdapter(grad_year_adapter);


        gender_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                gender_rb = (RadioButton) findViewById(checkedId);
            }
        });

    }

    public void formUpload(View v) {
        String first_name = fname_et.getText().toString();
        int age = Integer.parseInt(age_et.getText().toString());
        float foot = Float.parseFloat(foot_et.getText().toString());
        float inch = Float.parseFloat(inch_et.getText().toString());
        String major = major_et.getText().toString();
        String zodiac = zodiac_sp.getSelectedItem().toString().split("\\s")[1];
        int grad_year = Integer.parseInt(grad_year_sp.getSelectedItem().toString());
        String gender = gender_rb.getText().toString();
        User user = new User(first_name, age, foot, inch, major, zodiac, grad_year, gender);

        setUserDisplayName(first_name);

        db.collection("users").document(currentUser.getUid()).set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG,"DocumentSnapshot successfully written");
                        Intent intent = new Intent(OnboardingActivity.this, ProfilePhotoActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    private void setUserDisplayName(String name){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();


        if (user != null) {
            user.updateProfile(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(OnboardingActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(OnboardingActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    @Override
    public void onAuthStateChanged(@NonNull @NotNull FirebaseAuth firebaseAuth) {
        if (firebaseAuth.getCurrentUser() == null){
            goToAuthActivity();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(this);
    }


    private void goToAuthActivity(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

}
