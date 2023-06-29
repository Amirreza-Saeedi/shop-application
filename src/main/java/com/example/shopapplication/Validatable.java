package com.example.shopapplication;

import com.example.shopapplication.exceptions.IllegalFirstnameException;
import com.example.shopapplication.exceptions.IllegalLastnameException;
import com.example.shopapplication.exceptions.IllegalPasswordException;
import com.example.shopapplication.exceptions.IllegalUsernameException;
import jakarta.mail.internet.AddressException;


/*declare a method for validating username and passwordRegex based on each defined standards*/
public interface Validatable {
    boolean validate() throws IllegalUsernameException, IllegalPasswordException, IllegalFirstnameException, AddressException, IllegalLastnameException, jakarta.mail.internet.AddressException;
}
