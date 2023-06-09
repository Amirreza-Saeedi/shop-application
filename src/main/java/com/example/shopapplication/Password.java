package com.example.shopapplication;

/* defines a standard form for passwords
 * to be validated based on it*/
public class Password implements Validatable {
    @Override
    public boolean validate(User user) {
        return false;
    }
}
