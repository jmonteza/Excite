package com.example.chatmatch.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.chatmatch.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link report_issue} factory method to
 * create an instance of this fragment.
 */
public class report_issue extends DialogFragment {

    private Button sendFeedback;
    private FragmentActivity myContext;
    private EditText reporttitle;
    public report_issue() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_report_issue, null);


        reporttitle = (EditText)view.findViewById(R.id.reportIssue);



        sendFeedback = view.findViewById(R.id.sndFeedback);


        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String sReport = reporttitle.getText().toString();
                if (sReport.matches("")) {
                    FragmentManager fragManager = myContext.getSupportFragmentManager();
                    new reportissue_failure().show(getActivity().getSupportFragmentManager(), "feedbackFragment");
                }
                else{
                    FragmentManager fragManager = myContext.getSupportFragmentManager();
                    new reportissue_success().show(getActivity().getSupportFragmentManager(), "feedbackFragment");
                }

            }
        });

        // we create the actual dialog here
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setView(view)
                .setPositiveButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // nothing to really do with this I guess

                    }
                })
                .create();


    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }


    public void iscompleted(View v){

    }

    /**
     * When the avatar selector returns with the selected avatar we need to update the views
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}

