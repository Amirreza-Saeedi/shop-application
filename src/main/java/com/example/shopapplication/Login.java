package com.example.shopapplication;

import com.example.shopapplication.exceptions.UsernameAlreadyExistsException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login implements Verifiable {
    private final User user;

    public Login(User user) {
        if (user == null) {
            try {
                throw new NullPointerException();
            } catch (NullPointerException e) {
                System.err.println(e);
                e.printStackTrace();
            }
        }
        this.user = user;
    }

    @Override
    public boolean verify() throws UsernameAlreadyExistsException { // todo how to make it private?
        Connection connection;
        String username = user.getUsername();
        String password = user.getPassword();
        String table = null;

        if (user instanceof Customer)
            table = "Customers";
        else if (user instanceof Seller)
            table = "Sellers";
        else if (user instanceof Admin)
            table = "Admins";

        try {
            connection = new com.example.shopapplication.DatabaseConnectionJDBC().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            String sql = "SELECT * FROM " + table + " WHERE username='" + username + "' AND password='" + password + "'";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                if (resultSet.getString("username").equals(username)) {
                    connection.close();
                    return true;
                } else {
                    connection.close();
                    return false;
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e);
            e.printStackTrace();
        }

        return false;
    }
    public boolean validateCustomerLogin(String username, String password) {
        try {
            return verify();
        } catch (UsernameAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateSellerLogin(String username, String password) {
        try {
            return verify();
        } catch (UsernameAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateAdminLogin(String username, String password) {
        try {
            return verify();
        } catch (UsernameAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }
}
