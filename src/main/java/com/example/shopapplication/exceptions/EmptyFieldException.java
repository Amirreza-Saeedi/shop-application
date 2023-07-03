package com.example.shopapplication.exceptions;

public class EmptyFieldException extends Exception {
    @Override
    public String getMessage() {
        return "Empty field.";
    }
}
