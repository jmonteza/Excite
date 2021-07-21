package com.example.chatmatch.Messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class ChatAdapter extends FirestoreRecyclerAdapter<ChatModel, ChatAdapter.ChatHolder> {


    public ChatAdapter(@NonNull @NotNull FirestoreRecyclerOptions<ChatModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull ChatAdapter.ChatHolder holder, int position, @NonNull @NotNull ChatModel model) {
        holder.message_tv.setText(model.getMessage());
        // holder.receiver_tv.setText(model.getReceiver_uid());
        // holder.sender_tv.setText(model.getSender_uid());
        // holder.timestamp_tv.setText(String.valueOf(model.getTimestamp()));
    }

    @Override
    public @NotNull ChatHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sender_layout_single, parent, false);
        return new ChatHolder(view);
    }


    public static class ChatHolder extends RecyclerView.ViewHolder {
        TextView message_tv;
        TextView receiver_tv;
        TextView sender_tv;
        TextView timestamp_tv;

        public ChatHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            message_tv = itemView.findViewById(R.id.message_sent_tv);
            // receiver_tv = itemView.findViewById(R.id.receiver_uid_tv);
            // sender_tv = itemView.findViewById(R.id.sender_uid_tv);
            // timestamp_tv = itemView.findViewById(R.id.timestamp_tv);
        }
    }
}
