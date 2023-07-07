package com.example.shopapplication;

import java.sql.*;

public class DatabaseConnectionJDBC {
    private Connection connection = null;

//    public static final Connection CONNECTION;
//    static {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            CONNECTION = DriverManager.getConnection("jdbc:sqlite:src/database.db");
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public DatabaseConnectionJDBC() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:src/database.db");
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