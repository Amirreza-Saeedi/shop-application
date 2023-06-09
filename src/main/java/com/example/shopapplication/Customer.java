package com.example.shopapplication;

public class Customer extends User {
    public Customer(String username, String password) {
        super(username, password);
    }

    public Customer(String username) {
        super(username);
    }
}
