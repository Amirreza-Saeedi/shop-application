package com.example.shopapplication.exceptions;

public class InsertionFailedException extends Exception {
    @Override
    public String getMessage() {
        return "Insertion failed.";
    }
}
