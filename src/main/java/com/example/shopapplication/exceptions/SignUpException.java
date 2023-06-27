package com.example.shopapplication.exceptions;

public class SignUpException extends Exception {
    @Override
    public String getMessage() {
        return "One of steps is false.";
    }
}
