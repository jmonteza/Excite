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

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chatmatch.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedbackFragment} factory method to
 * create an instance of this fragment.
 */
public class FeedbackFragment extends DialogFragment  {

    private Button sendFeedback;
    private FragmentActivity myContext;
    public FeedbackFragment() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_feedback, null);


        sendFeedback = view.findViewById(R.id.sndFeedback);


        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragManager = myContext.getSupportFragmentManager();
                new feedback_success_fragment().show(getActivity().getSupportFragmentManager(), "feedbackFragment");
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


    /**
     * When the avatar selector returns with the selected avatar we need to update the views
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}

