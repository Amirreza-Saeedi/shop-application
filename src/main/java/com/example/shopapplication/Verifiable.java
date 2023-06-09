package com.example.shopapplication;

import com.example.shopapplication.exceptions.IllegalPasswordException;
import com.example.shopapplication.exceptions.IllegalUsernameException;
import com.example.shopapplication.exceptions.UsernameAlreadyExistsException;

/*declares a method for verifying username and passwordRegex based on database*/
public interface Verifiable {
    boolean validate(String table, User user) throws UsernameAlreadyExistsException, IllegalPasswordException, IllegalUsernameException;

}
