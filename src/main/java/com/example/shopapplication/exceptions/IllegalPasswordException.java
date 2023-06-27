package com.example.shopapplication.exceptions;

public class IllegalPasswordException extends Exception {
    @Override
    public String getMessage() {
        return "Password contains illegal characters.";
    }
}
