package com.example.shopapplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUp implements Validatable {
    @Override
    public boolean validate(String table, User user) { // todo how to make it private?
        boolean validation = false; // don't let user sign up by default
        String username = user.getUsername();
        String sql = "SELECT * FROM " + table + " WHERE username='" + username + "'";
        Connection connection = null;

        try {
            connection = new DatabaseConnectionJDBC().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                // if username is unique set true
                String str = resultSet.getString("username");
                System.out.println("str = " + str);
                validation = !username.equals(str);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e);
        }

        return validation;
    }

    public boolean validateCustomerSignUp(String username) { // check if username exists already
        return validate("Customers", new Customer(username));
    }

    public boolean validateSellerSignUp(String username) { // check if username exists already
        return validate("Sellers", new Customer(username));
    }

}