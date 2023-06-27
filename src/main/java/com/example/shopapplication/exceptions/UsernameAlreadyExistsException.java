package com.example.shopapplication.exceptions;

public class UsernameAlreadyExistsException extends Exception{
    @Override
    public String getMessage() {
        return "Username already exists.";
    }
}
