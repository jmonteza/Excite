package com.example.chatmatch.Discovery;

public class UserModel {

    private String firstName;
    private String photoURI;

    public UserModel(){

    }



    public UserModel(String photoURI, String firstName){
        this.firstName = firstName;
        this.photoURI = photoURI;
    }


    public String getPhotoURI() {
        return photoURI;
    }

    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
