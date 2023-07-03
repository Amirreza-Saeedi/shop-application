package com.example.shopapplication.exceptions;

public class IllegalNameException extends Exception {
    @Override
    public String getMessage() {
        return "Illegal name.";
    }
}
