package com.example.shopapplication;

import java.sql.*;

public class DatabaseConnectionJDBC {
    private static Connection connection = null;

    public DatabaseConnectionJDBC() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        DatabaseConnectionJDBC.connection = DriverManager.getConnection("jdbc:sqlite:src/database.db");
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnectionJDBC database = new DatabaseConnectionJDBC();
        Statement statement = database.connection.createStatement();
        ResultSet resultset = statement.executeQuery("SELECT * From Users");
        while (resultset.next()) {
            System.out.println(resultset.getString("username"));
            System.out.println(resultset.getString("password"));
        }

    }

    public static Connection getConnection() {
        return connection;
    }
}
