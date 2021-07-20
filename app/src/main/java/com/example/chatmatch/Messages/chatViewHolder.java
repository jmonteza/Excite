package com.example.chatmatch.Messages;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.R;

import org.jetbrains.annotations.NotNull;

public class chatViewHolder extends RecyclerView.ViewHolder {


    public TextView message_tv;
    public TextView receiver_tv;
    public TextView sender_tv;
    public TextView timestamp_tv;

    public chatViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        message_tv = itemView.findViewById(R.id.message_sent_tv);
        receiver_tv = itemView.findViewById(R.id.receiver_uid_tv);
        sender_tv = itemView.findViewById(R.id.sender_uid_tv);
        timestamp_tv = itemView.findViewById(R.id.timestamp_tv);

    }
}
