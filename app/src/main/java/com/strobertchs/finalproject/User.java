package com.strobertchs.finalproject;

public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String fullName;

    public User(String uEmail, String uFirstName, String uLastName, String uPassword) {
        email = uEmail;
        firstName = uFirstName;
        lastName = uLastName;
        password = uPassword;
        fullName = lastName + "," + firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String nEmail) {
        email = nEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String nFirstName) {
        firstName = nFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String nLastName) {
        lastName = nLastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String nPassword) {
        password = nPassword;
    }

    public String getFullName() {
        return fullName;
    }

}
