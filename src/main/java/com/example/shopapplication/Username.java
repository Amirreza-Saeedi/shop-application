package com.example.shopapplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* defines a standard form for usernames
 * to be validated based on it*/
public class Username implements Validatable {
    @Override
    public boolean validate(User user) {
        return false;
    }

}
