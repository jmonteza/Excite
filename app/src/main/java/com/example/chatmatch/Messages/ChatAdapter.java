package com.example.chatmatch.Messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatmatch.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.imageview.ShapeableImageView;

import org.jetbrains.annotations.NotNull;

public class ChatAdapter extends FirestoreRecyclerAdapter<ChatModel, ChatAdapter.ChatHolder> {

    private final int MSG_TYPE_LEFT = 0;
    private final int MSG_TYPE_RIGHT = 1;

    public ChatAdapter(@NonNull @NotNull FirestoreRecyclerOptions<ChatModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull ChatAdapter.ChatHolder holder, int position, @NonNull @NotNull ChatModel model) {
        if (holder.getItemViewType() == MSG_TYPE_LEFT){
            holder.left_message_tv.setText(model.getMessage());
            Glide.with(holder.left_message_siv.getContext()).load("https://firebasestorage.googleapis.com/v0/b/chatmatch-b8ec9.appspot.com/o/images%2Fprofile%2FNxMtj8hOD2TgMVKOqCmT.jpeg?alt=media&token=38031706-1f61-419a-b90f-519915f97efc").into(holder.left_message_siv);
        } else {
            holder.right_message_tv.setText(model.getMessage());
        }
    }


    @Override
    public @NotNull ChatHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == MSG_TYPE_LEFT){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_message_single, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_message_single, parent, false);
        }
        return new ChatHolder(view);

    }


    public static class ChatHolder extends RecyclerView.ViewHolder {
        TextView left_message_tv;
        TextView right_message_tv;
        ShapeableImageView left_message_siv;

        public ChatHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            left_message_tv = itemView.findViewById(R.id.left_message_text_view);
            left_message_siv = itemView.findViewById(R.id.sender_picture);
            right_message_tv = itemView.findViewById(R.id.right_message_text_view);
        }

    }


    @Override
    public int getItemViewType(int position) {
        // return super.getItemViewType(position);
        if (super.getItem(position).getReceiver_uid().equals("left")){
            // 0: left
            return MSG_TYPE_LEFT;
        } else if (super.getItem(position).getReceiver_uid().equals("right")) {
            // 1: right
            return MSG_TYPE_RIGHT;
        } else {
            return 2;
        }
    }

    @Override
    public int getItemCount(){
        return super.getItemCount();
    }
}
