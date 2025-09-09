package com.example.finalproject;

public class Users {
    private String fullNameUsers;
    private String emailUsers;
    private String passwordUsers;
    private String birthDateUsers;
    private String phoneNumberUsers;

    public Users() {
    }

    public Users(String fullNameUsers, String emailUsers, String passwordUsers, String birthDateUsers, String phoneNumberUsers) {
        this.fullNameUsers = fullNameUsers;
        this.emailUsers = emailUsers;
        this.passwordUsers = passwordUsers;
        this.birthDateUsers = birthDateUsers;
        this.phoneNumberUsers = phoneNumberUsers;
    }

    public String getFullNameUsers() {
        return fullNameUsers;
    }

    public void setFullNameUsers(String fullNameUsers) {
        this.fullNameUsers = fullNameUsers;
    }

    public String getEmailUsers() {
        return emailUsers;
    }

    public void setEmailUsers(String emailUsers) {
        this.emailUsers = emailUsers;
    }

    public String getPasswordUsers() {
        return passwordUsers;
    }

    public void setPasswordUsers(String passwordUsers) {
        this.passwordUsers = passwordUsers;
    }

    public String getBirthDateUsers() {
        return birthDateUsers;
    }

    public void setBirthDateUsers(String birthDateUsers) {
        this.birthDateUsers = birthDateUsers;
    }

    public String getPhoneNumberUsers() {
        return phoneNumberUsers;
    }

    public void setPhoneNumberUsers(String phoneNumberUsers) {
        this.phoneNumberUsers = phoneNumberUsers;
    }
}
