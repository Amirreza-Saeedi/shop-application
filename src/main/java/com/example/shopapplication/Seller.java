package com.example.shopapplication;

public class Seller extends User {
    public Seller(String username, String password) {
        super(username, password);
    }

    public Seller(String username, String password, String firstname, String lastname, String email) {
        super(username, password, firstname, lastname, email);
    }
}
