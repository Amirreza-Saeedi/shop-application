package com.example.shopapplication;

import java.sql.*;

public class DatabaseConnectionJDBC {
    private static Connection connection = null;

    public DatabaseConnectionJDBC() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        DatabaseConnectionJDBC.connection = DriverManager.getConnection("jdbc:sqlite:src/database.db");
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnectionJDBC database = new DatabaseConnectionJDBC();
        Statement statement = database.connection.createStatement();
        String group = "BreakFastCommodities";
        String sql = "SELECT * FROM allcommodities WHERE groupp='BreakFastCommodities'";
//        String sql = "SELECT * FROM " + table + " WHERE username='" + username + "' AND password='" + password + "'";

        ResultSet resultset = statement.executeQuery(sql);
        while (resultset.next()) {
            System.out.println("type = " + resultset.getString("type"));
            System.out.println("brand = " + resultset.getString("brand"));
        }

    }

    public Connection getConnection() {
        return connection;
    }
}
