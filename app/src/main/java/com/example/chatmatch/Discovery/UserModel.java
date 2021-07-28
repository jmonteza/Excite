package com.example.chatmatch.Discovery;

public class UserModel {

    private String firstName;

    public UserModel(){

    }



    public UserModel(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
