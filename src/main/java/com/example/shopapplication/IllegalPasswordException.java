package com.example.shopapplication;

public class IllegalPasswordException extends Exception {
    @Override
    public String getMessage() {
        return "Password contains illegal characters.";
    }
}
