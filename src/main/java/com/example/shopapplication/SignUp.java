package com.example.shopapplication;

import com.example.shopapplication.exceptions.IllegalPasswordException;
import com.example.shopapplication.exceptions.IllegalUsernameException;
import com.example.shopapplication.exceptions.NotReceivedAdminPermissionException;
import com.example.shopapplication.exceptions.UsernameAlreadyExistsException;
import com.example.shopapplication.regex.MyRegEx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp implements Verifiable {
    @Override
    public boolean validate(String table, User user) throws UsernameAlreadyExistsException,
            IllegalPasswordException, IllegalUsernameException { // todo how to make it private?

        boolean validation = false; // don't let user sign up by default
        String username = user.getUsername();
        String password = user.getPassword();
        String sql = "SELECT * FROM " + table + " WHERE username='" + username + "'";

        /*checks if username and password are legal*/
        validateUsername(username);
        validatePassword(password);

        /*checks if username exists already*/
        Connection connection = null;
        try {
            connection = new DatabaseConnectionJDBC().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                // if username is unique set true
                String str = resultSet.getString("username");
                System.out.println("str = " + str);
                if (username.equals(str))
                    throw new UsernameAlreadyExistsException();
                else
                    validation = true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e);
        }

        return validation;
    }

    public boolean validateCustomerSignUp(String username, String password) throws UsernameAlreadyExistsException,
            IllegalPasswordException, IllegalUsernameException { // check if username exists already

        return validate("Customers", new Customer(username));
    }

    public boolean validateSellerSignUp(String username, String password) throws IllegalUsernameException,
            IllegalPasswordException, NotReceivedAdminPermissionException, UsernameAlreadyExistsException {
        boolean validation = false;
        validation = validate("Sellers", new Customer(username)) && requestForAdminPermission();

        return validation;
    }

    private boolean requestForAdminPermission() {
        boolean permission = false;
        // todo socket server
        return permission;
    }

    private void validatePassword(String password) throws IllegalPasswordException {
        Pattern pattern = Pattern.compile(MyRegEx.passwordRegex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find())
            throw new IllegalPasswordException();

    }

    private void validateUsername(String username) throws IllegalUsernameException {
        Pattern pattern = Pattern.compile(MyRegEx.usernameRegex);
        Matcher matcher = pattern.matcher(username);
        if (!matcher.find())
            throw new IllegalUsernameException();
    }
}