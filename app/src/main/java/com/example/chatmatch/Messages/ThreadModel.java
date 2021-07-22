package com.example.chatmatch.Messages;

import java.util.Date;

public class ThreadModel {
    private String photoURI;
    private String last_message;
    private String full_name;
    private Date timestamp;

    public ThreadModel(){

    }

    public ThreadModel(String photoURI, String last_message, String full_name, Date timestamp) {
        this.photoURI = photoURI;
        this.last_message = last_message;
        this.full_name = full_name;
        this.timestamp = timestamp;
    }

    public String getPhotoURI() {
        return photoURI;
    }

    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
