package com.example.shopapplication.exceptions;

public class NotReceivedAdminPermissionException extends Exception {
    @Override
    public String getMessage() {
        return "Admin permission didn't received.";
    }

}
