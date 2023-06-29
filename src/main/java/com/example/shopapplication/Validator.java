package com.example.shopapplication;

import com.example.shopapplication.exceptions.*;
import com.example.shopapplication.regex.MyRegEx;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public boolean validateUserSignUp(User user) throws IllegalUsernameException, IllegalFirstnameException,
            IllegalLastnameException, IllegalPasswordException, AddressException {
        return validateUsername(user.getUsername()) && validateFirstname(user.getFirstname()) &&
                validateLastname(user.getLastname()) && validatePassword(user.getPassword()) &&
                validateEmail(user.getEmail());
    }
    public boolean validateCustomer(Customer customer) throws IllegalUsernameException, IllegalFirstnameException,
            IllegalLastnameException, IllegalPasswordException, AddressException {
        return validateUserSignUp(customer);
    }

    public boolean validateSeller(Seller seller) throws IllegalUsernameException, IllegalPasswordException,
            IllegalFirstnameException, IllegalLastnameException, AddressException {
        return validateUserSignUp(seller);
    }

    
    public boolean validatePassword(String pass1, String pass2) throws NotMatchedPasswordsException {
        if (pass1.equals(pass2))
            return true;
        throw new NotMatchedPasswordsException();
    }

    public boolean validatePassword(String password) throws IllegalPasswordException {
        Pattern pattern = Pattern.compile(MyRegEx.passwordRegex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find())
            throw new IllegalPasswordException();
        else
            return true;
    }

    public boolean validateUsername(String username) throws IllegalUsernameException {
        Pattern pattern = Pattern.compile(MyRegEx.usernameRegex);
        Matcher matcher = pattern.matcher(username);
        if (!matcher.find())
            throw new IllegalUsernameException();
        else
            return true;
    }

    public boolean validateEmail(String email) throws AddressException {
        InternetAddress internetAddress = new InternetAddress(email);
        internetAddress.validate();
        return true;
    }

    private boolean validateName(String name) {
        Pattern pattern = Pattern.compile(MyRegEx.usernameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
    
    public boolean validateFirstname(String firstname) throws IllegalFirstnameException {
        if (!validateName(firstname)) {
            throw new IllegalFirstnameException();
        } else 
            return true;
    }
    
    public boolean validateLastname(String lastname) throws IllegalLastnameException {
        if (!validateName(lastname)) {
            throw new IllegalLastnameException();
        } else 
            return true;
    }
}
