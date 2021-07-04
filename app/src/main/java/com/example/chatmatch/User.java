package com.example.chatmatch;

public class User {
    private String first_name;
    private int age;
    private float foot;
    private float inch;
    private String major;
    private String zodiac;
    private int grad_year;
    private String gender;

    public User(String first_name, int age, float foot, float inch, String major, String zodiac, int grad_year, String gender) {
        this.first_name = first_name;
        this.age = age;
        this.foot = foot;
        this.inch = inch;
        this.major = major;
        this.zodiac = zodiac;
        this.grad_year = grad_year;
        this.gender = gender;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getFoot() {
        return foot;
    }

    public void setFoot(float foot) {
        this.foot = foot;
    }

    public float getInch() {
        return inch;
    }

    public void setInch(float inch) {
        this.inch = inch;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public int getGrad_year() {
        return grad_year;
    }

    public void setGrad_year(int grad_year) {
        this.grad_year = grad_year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
