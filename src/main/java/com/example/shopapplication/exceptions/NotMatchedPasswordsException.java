package com.example.shopapplication.exceptions;

public class NotMatchedPasswordsException extends Exception {
    @Override
    public String getMessage() {
        return "Passwords does not match.";
    }
}
