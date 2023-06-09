package com.example.shopapplication;

public class UsernameAlreadyExistsException extends Exception{
    @Override
    public String getMessage() {
        return "Username already exists.";
    }
}
