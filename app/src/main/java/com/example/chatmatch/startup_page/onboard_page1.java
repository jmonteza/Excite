package com.example.chatmatch.startup_page;

import android.app.DatePickerDialog;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.chatmatch.R;
import com.example.chatmatch.Util.FirebaseUtil;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class onboard_page1 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    MaterialButton navigateBtn;
    EditText FirstName;
    ImageButton bdayPicker;
    ImageButton bdayBtn;

    SharedPreferences sp1;

    private Date bday;
    private ArrayList<String> Output;
    private final String TAG = "Onboard Page 1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_screen1);

        // Output = new ArrayList<>();

        // sp1 = getSharedPreferences("userPref1", Context.MODE_PRIVATE);

        // SharedPreferences.Editor editor = sp1.edit();

        bdayBtn = findViewById(R.id.birthdayPicker);
        navigateBtn = findViewById(R.id.uploadDoneBtn);

        FirstName = (EditText)findViewById(R.id.firstName);
        bdayPicker = findViewById(R.id.birthdayPicker);

        // Bundle bundle = getIntent().getExtras();
        // Output = bundle.getStringArrayList("userDetails");
        
        
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

                            Map<String, Object> data = new HashMap<>();
                            data.put("firstName", fName);
                            data.put("birthday", bday);


                            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

                            FirebaseUtil.getFirestore().collection("userProfile").document(id).update(data)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.d(TAG, "First Name and Birthday Added");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "First Name and Birthday Upload Failed");
                                        }
                                    });

                            // Output.add(fName);
                            // Output.add(bday);

                            // editor.putString("firstName", fName);
                            // editor.putString("birthDay", bday);
                            // editor.apply();


                            // intent.putStringArrayListExtra("userDetails", Output);
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
        bday = calendar.getTime();

        TextView textView = (TextView) findViewById(R.id.age_value);
        textView.setText(currentDate);
        Log.d("date", currentDate+"");
    }
}