package com.example.mytodoapp;

import java.io.Serializable;
import java.util.ArrayList;

public class UserApp implements Serializable {
    // l'implementation de Serializable ==> pour passer un object user in PUT_EXTRAS

    private String userName;
    private String userEmail;
    private String userPasswd;
    private String phoneNum;
    private String birthDay;
    private ArrayList<Tutorial> userTutorials;
    private String country;
    private String city;
    private int id;


    public UserApp(String name,String email,String passwd){
        this.userName = name;
        this.userEmail = email;
        this.userPasswd = passwd;
    }
    public UserApp(int id,String name,String email,String passwd){
        this.userName = name;
        this.userEmail = email;
        this.userPasswd = passwd;
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserApp{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPasswd='" + userPasswd + '\'' +
                ", id=" + id +
                '}';
    }

    public UserApp() {
    }

    // getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public int getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }


   // le birthDay sous format date "MM/dd/yy"
    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public ArrayList<Tutorial> getUserTutorials() {
        return userTutorials;
    }


    // error !!!!??
    public void addTuto(Tutorial tuto){
        this.userTutorials.add(tuto);
    }
    public void setUserTutorials(ArrayList<Tutorial> tuto){ this.userTutorials = tuto; }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
