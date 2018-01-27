package com.strobertchs.finalproject.model;

/**
 * Created by jenny on 2018-01-26.
 */

public class Message {
    private String title;
    private String message;
    private String registrationToken;

    public Message() {
    }

    public Message(String token, String title, String message) {
        this.registrationToken = token;
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }
}
