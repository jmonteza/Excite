package com.example.chatmatch;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OnboardingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        Spinner zodiac_spinner = (Spinner) findViewById(R.id.zodiac_spinner);
        Spinner grad_year_spinner = (Spinner) findViewById(R.id.grad_year_spinner);

        // Create an ArrayAdapter using the string array and a default zodiac_spinner layout
        ArrayAdapter<CharSequence> zodiac_adapter = ArrayAdapter.createFromResource(this,
                R.array.zodiac_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> grad_year_adapter = ArrayAdapter.createFromResource(this,
                R.array.grad_year_array, android.R.layout.simple_spinner_item);


        // Specify the layout to use when the list of choices appears
        zodiac_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grad_year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the zodiac_adapter to the zodiac_spinner
        zodiac_spinner.setAdapter(zodiac_adapter);
        grad_year_spinner.setAdapter(grad_year_adapter);

    }
}
