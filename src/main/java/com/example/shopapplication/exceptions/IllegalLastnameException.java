package com.example.shopapplication.exceptions;

public class IllegalLastnameException extends Exception {
    @Override
    public String getMessage() {
        return "Illegal lastname";
    }
}
