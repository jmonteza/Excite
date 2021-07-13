package com.example.chatmatch.startup_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.util.Calendar;

public class onboard_page1 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    MaterialButton navigateBtn;
    EditText mEdit;
    ImageButton bdayPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_screen1);

        navigateBtn = findViewById(R.id.testCore);

        mEdit   = (EditText)findViewById(R.id.chngFactor);
        bdayPicker = findViewById(R.id.birthdayPicker);

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

                        if (mEdit.getText().toString().matches("")){
                            Animation shake = AnimationUtils.loadAnimation(onboard_page1.this, R.anim.shake);
                            mEdit.startAnimation(shake);
                        }
                        else{
                            Intent intent = new Intent(onboard_page1.this, onboard_page2.class);
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
        Log.d("date", currentDate+"");
    }
}