package com.example.chatmatch.startup_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chatmatch.R;
import com.example.chatmatch.User.User;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class onboard_page1 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    MaterialButton navigateBtn;
    EditText FirstName;
    ImageButton bdayPicker;
    ImageButton bdayBtn;

    SharedPreferences sp1;

    private String bday;
    private ArrayList<String> Output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_screen1);

        Output = new ArrayList<>();

        sp1 = getSharedPreferences("userPref1", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp1.edit();

        bdayBtn = findViewById(R.id.birthdayPicker);
        navigateBtn = findViewById(R.id.testCore);

        FirstName = (EditText)findViewById(R.id.firstName);
        bdayPicker = findViewById(R.id.birthdayPicker);

        Bundle bundle = getIntent().getExtras();
        Output = bundle.getStringArrayList("userDetails");
        
        
//        for (int i = 0; i < Output.size (); i++)
//        {
//            Log.d("array values", Output.get(i));
//        }

        bdayPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        navigateBtn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {

                        Log.d("birthdate", bday+"");

                        if (bday == null){
                            Animation shake = AnimationUtils.loadAnimation(onboard_page1.this, R.anim.shake);
                            bdayBtn.startAnimation(shake);
                        }
                        else if (FirstName.getText().toString().matches("")){
                            Animation shake = AnimationUtils.loadAnimation(onboard_page1.this, R.anim.shake);
                            FirstName.startAnimation(shake);
                        }
                        else{

                            Intent intent = new Intent(onboard_page1.this, onboard_page2.class);
                            String fName = FirstName.getText().toString().trim();

                            Output.add(fName);
                            Output.add(bday);

                            editor.putString("firstName", fName);
                            editor.putString("birthDay", bday);
                            editor.apply();


                            intent.putStringArrayListExtra("userDetails", Output);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    }
                });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


        Calendar calendar =  Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textView = (TextView) findViewById(R.id.age_value);
        textView.setText(currentDate);
        bday = currentDate;
        Log.d("date", currentDate+"");
    }
}