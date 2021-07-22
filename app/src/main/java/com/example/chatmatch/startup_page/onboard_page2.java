package com.example.chatmatch.startup_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.chatmatch.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class onboard_page2 extends AppCompatActivity {

    Button navigateBtn;
    RadioGroup radioGenderGroup;
    RadioGroup radioInterestGroup;
    RadioButton gender_select;
    RadioButton interest_select;
    private ArrayList<String> Output;

    private String selected_gender;
    private String selected_interest;

    SharedPreferences sp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_screen2);

        Output = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        Output = bundle.getStringArrayList("userDetails");
        radioGenderGroup = findViewById(R.id.RadiogrpGender);
        radioInterestGroup = findViewById(R.id.RadioGroupInterest);

        sp2 = getSharedPreferences("userPref1", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp2.edit();

        navigateBtn = findViewById(R.id.testCore);

//        navigateBtn.setEnabled(false);


        navigateBtn.setOnClickListener(
                view -> {


                    Log.d("Value of radio btns", selected_gender+"");
                    if (selected_gender == null || selected_interest == null){

                        Toast.makeText(onboard_page2.this, "Please Complete all fields", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.d("selected gender", selected_gender+"");
                        Output.add(selected_gender);
                        Output.add(selected_interest);

                        editor.putString("selectedGender", selected_gender);
                        editor.putString("selectedInterest", selected_interest);
                        editor.apply();
//                        for (int i = 0; i < Output.size(); i++)
//                        {
//                            Log.d("array values", Output.get(i));
//                        }
                        navigateBtn.setEnabled(true);
                        Intent intent = new Intent(onboard_page2.this, onboard_page3.class);
                        intent.putStringArrayListExtra("userDetails", Output);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }


                });




    }



    public void checkButton(View v){
        int radioGenderId = radioGenderGroup.getCheckedRadioButtonId();


        Log.d("radio Gender", radioGenderId+"");

        gender_select = findViewById(radioGenderId);
//        interest_select = findViewById(radioInterestId);
//        Toast.makeText(this, "Selected Radio Button: " + interest_select.getText(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(onboard_page2.this, "Selected Radio Button: " + gender_select.getText(), Toast.LENGTH_SHORT).show();

        selected_gender = gender_select.getText().toString();

    }

    public void checkButton2(View v){

        int radioInterestId = radioInterestGroup.getCheckedRadioButtonId();


        Log.d("radio Interest", radioInterestId+"");

        interest_select = findViewById(radioInterestId);
//        Toast.makeText(this, "Selected Radio Button: " + interest_select.getText(), Toast.LENGTH_SHORT).show();

        selected_interest = interest_select.getText().toString();

    }


}