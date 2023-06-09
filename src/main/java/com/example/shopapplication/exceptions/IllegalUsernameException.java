package com.example.shopapplication.exceptions;

public class IllegalUsernameException extends Exception {
    @Override
    public String getMessage() {
        return "Username contains illegal characters.";
    }
}
