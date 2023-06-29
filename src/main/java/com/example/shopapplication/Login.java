package com.example.shopapplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {

    private static boolean validateLogin(String table, String username, String password) {
        Connection connection;
        try {
            connection = new com.example.shopapplication.DatabaseConnectionJDBC().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet;

            { // temp
                resultSet = statement.executeQuery("SELECT * FROM Users");
                while (resultSet.next()) {
                    System.out.println("username = " + resultSet.getString("username"));
                    System.out.println("password = " + resultSet.getString("password"));
                }
            }

            String sql = "SELECT * FROM " + table + " WHERE username='" + username + "' AND password='" + password + "'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (resultSet.getString("username").equals(username)) {
                    AppWorkflow.username = username;
//                    connection.close();
                    return true;
                } else {
//                    connection.close();
                    return false;
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return false;
    }
    public static boolean validateUserLogin(String username, String password) {
        return validateLogin("Users", username, password);
    }

    public static boolean validateSellerLogin(String username, String password) {
        return validateLogin("Sellers",username, password);
    }

    public static boolean validateAdminLogin(String username, String password) {
        return validateLogin("Admins", username, password);
    }
}
