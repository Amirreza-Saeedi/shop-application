package com.example.shopapplication;

public class NotReceivedAdminConfirmationException extends Exception {
    @Override
    public String getMessage() {
        return "Admin confirmation didn't received.";
    }

}
