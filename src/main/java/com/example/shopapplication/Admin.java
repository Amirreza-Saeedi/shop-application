package com.example.shopapplication;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin(String username, String password, String firstname, String lastname, String email) {
        super(username, password, firstname, lastname, email);
    }
}
