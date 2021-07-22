package com.example.chatmatch.Messages;

import java.util.Date;

public class ChatModel {
    private String sender_uid;
    private String receiver_uid;
    private String message;
    private Date timestamp;

    public ChatModel(){
        // Empty constructor is needed
    }

    public ChatModel(String sender_uid, String receiver_uid, String message, Date timestamp) {
        this.sender_uid = sender_uid;
        this.receiver_uid = receiver_uid;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getSender_uid() {
        return sender_uid;
    }

    public void setSender_uid(String sender_uid) {
        this.sender_uid = sender_uid;
    }

    public String getReceiver_uid() {
        return receiver_uid;
    }

    public void setReceiver_uid(String receiver_uid) {
        this.receiver_uid = receiver_uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
