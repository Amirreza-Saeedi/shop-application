package com.example.shopapplication;

import com.example.shopapplication.exceptions.UsernameAlreadyExistsException;

/*declares a method for verifying username and passwordRegex based on database*/
public interface Verifiable {
    boolean verify() throws UsernameAlreadyExistsException;

}
