package com.example.shopapplication;

import java.sql.*;

public class DatabaseConnectionJDBC {
    Connection connection;

    public DatabaseConnectionJDBC() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new DatabaseConnectionJDBC();

    }

    public Connection getConnection() {
        return connection;
    }
}
