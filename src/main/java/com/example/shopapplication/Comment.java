package com.example.shopapplication;

import java.time.LocalDateTime;

public class Comment {
    private String fullName;
    private String  message;
    private String username;
    private String userType;
    private LocalDateTime localDateTime;
    private boolean bought;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int number;

    public Comment(String fullName, String message, String username, String userType,
                   LocalDateTime localDateTime, int number, boolean bought) {
        this.fullName = fullName;
        this.message = message;
        this.username = username;
        this.userType = userType;
        this.localDateTime = localDateTime;
        this.number = number;
        this.bought = bought;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
