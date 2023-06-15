package com.example.shopapplication;

public class Seller extends User {
    private String company;
    public Seller(String username, String password) {
        super(username, password);
    }

    public Seller(String username, String password, String firstname, String lastname, String email, String company) {
        super(username, password, firstname, lastname, email);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }
}
