package com.example.chatmatch.Discovery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatmatch.R;
import com.example.chatmatch.Model.UserModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class DiscoveryAdapter extends FirestoreRecyclerAdapter<UserModel, DiscoveryAdapter.DiscoveryHolder> {


    public DiscoveryAdapter(@NonNull @NotNull FirestoreRecyclerOptions<UserModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull DiscoveryAdapter.DiscoveryHolder holder, int position, @NonNull @NotNull UserModel model) {
        holder.userName.setText(model.getFirstName());
        Glide.with(holder.itemView.getContext()).load(model.getPhotoURI()).into(holder.userDisplay);
    }

    @NonNull
    @NotNull
    @Override
    public DiscoveryHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_row_item,
                parent, false);
        return new DiscoveryHolder(view);
    }

    static class DiscoveryHolder extends RecyclerView.ViewHolder{
        private final TextView userName;
        private ImageView userDisplay;
        public DiscoveryHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.usrCardname);
            userDisplay = itemView.findViewById(R.id.icon);
        }
    }


}
