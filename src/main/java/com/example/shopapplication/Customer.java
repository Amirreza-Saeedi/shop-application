package com.example.shopapplication;

public class Customer extends User {
    public Customer(String username, String password) {
        super(username, password);
    }

    public Customer(String username) {
        super(username);
    }

    public Customer(String username, String password, String firstname, String lastname, String email) {
        super(username, password, firstname, lastname, email);
    }
}
