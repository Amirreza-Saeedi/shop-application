package com.example.shopapplication;

import java.time.LocalDateTime;

public class Message {
    private final String from;
    private final String message;
    private final LocalDateTime dateTime;
    private final Integer row;

    public Message(String from, String message, LocalDateTime dateTime, Integer row) {
        this.from = from;
        this.message = message;
        this.dateTime = dateTime;
        this.row = row;
    }

    public String getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Integer getRow() {
        return row;
    }
}
