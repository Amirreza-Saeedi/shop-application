package com.example.shopapplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login implements Validatable {
    @Override
    public boolean validate(String table, User user) { // todo how to make it private?
        Connection connection;
        String username = user.getUsername();
        String password = user.getPassword();

        try {
            connection = new com.example.shopapplication.DatabaseConnectionJDBC().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet;

//            { // temp
//                resultSet = statement.executeQuery("SELECT * FROM Users");
//                while (resultSet.next()) {
//                    System.out.println("username = " + resultSet.getString("username"));
//                    System.out.println("password = " + resultSet.getString("password"));
//                }
//            }

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
        }

        return false;
    }
    public boolean validateCustomerLogin(String username, String password) {
        return validate("CustomerSre", new Customer(username, password));
    }

    public boolean validateSellerLogin(String username, String password) {
        return validate("Sellers", new Seller(username, password));
    }

    public boolean validateAdminLogin(String username, String password) {
        return validate("Admins", new Admin(username, password));
    }
}
