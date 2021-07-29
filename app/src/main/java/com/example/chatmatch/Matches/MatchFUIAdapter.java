package com.example.chatmatch.Matches;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;

import org.jetbrains.annotations.NotNull;

public class MatchFUIAdapter extends FirestoreRecyclerAdapter<MatchModel, MatchFUIAdapter.MatchHolder> {

    @Override
    protected void onBindViewHolder(@NonNull @NotNull MatchFUIAdapter.MatchHolder holder, int position, @NonNull @NotNull MatchModel model) {

    }

    @NonNull
    @NotNull
    @Override
    public MatchFUIAdapter.MatchHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }
}
