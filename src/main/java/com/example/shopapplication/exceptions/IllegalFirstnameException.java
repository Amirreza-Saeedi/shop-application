package com.example.shopapplication.exceptions;

public class IllegalFirstnameException extends Exception {
    @Override
    public String getMessage() {
        return "Illegal firstname.";
    }
}
