package com.example.shopapplication;

/*declares a method for verifying username and password based on database*/
public interface Verifiable {
    boolean validate(String table, User user);

}
