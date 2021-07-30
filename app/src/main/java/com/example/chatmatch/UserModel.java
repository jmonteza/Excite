package com.example.chatmatch;

import java.util.Date;

public class UserModel {

    private String firstName;
    private String photoURI;
    private Date birthday;
    private Date created;
    private String interest;
    private String gender;


    public UserModel(){

    }

    public UserModel(String firstName, String photoURI, Date birthday, Date created, String interest, String gender) {
        this.firstName = firstName;
        this.photoURI = photoURI;
        this.birthday = birthday;
        this.created = created;
        this.interest = interest;
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
