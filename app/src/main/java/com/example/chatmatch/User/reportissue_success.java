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

import com.example.chatmatch.R;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reportissue_success} factory method to
 * create an instance of this fragment.
 */
public class reportissue_success extends DialogFragment {
    private FragmentActivity myContext;

    MaterialButton exit;
    public reportissue_success() {

    }

    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_reportissue_success, null);


        exit = view.findViewById(R.id.exitFeedback);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragManager = myContext.getSupportFragmentManager();
                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("feedbackFragment");
                if(fragment != null)
                    getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
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

//    public void spawnBugReport(View v){
//        FragmentManager fragManager = myContext.getSupportFragmentManager();
//        new FeedbackFragment().show(getActivity().getSupportFragmentManager(), "feedbackFragment");
//    }


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

