package com.example.chatmatch.Matches;

public class MatchModel {
    private String firstName;

    public MatchModel(){

    }

    public MatchModel(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
