package com.example.shopapplication.exceptions;

public class IllegalEmailAddressException extends Exception {
    @Override
    public String getMessage() {
        return "Illegal email address.";
    }
}
