package com.example.shopapplication;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class StorageLog {
    private int row;

    private int id;

    private int storageId;
    private int amount;
    private double value;
    private String date;
    private String time;
    private String type;
    private String descriptions;
    public StorageLog(int row, int id, int storageId, int amount, double value, String date, String time, String type, String descriptions) {
        this.row = row;
        this.storageId = storageId;
        this.amount = amount;
        this.value = value;
        this.date = date;
        this.time = time;
        this.type = type;
        this.id = id;
        this.descriptions = descriptions;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getStorageId() {
        return storageId;
    }

    public int getAmount() {
        return amount;
    }

    public double getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getDescriptions() {
        return descriptions;
    }

    private static final String IN = "in";
    private static final String OUT = "out";

    private StorageLog(int storageId, int amount, double value, String descriptions, String type) {
        this.storageId = storageId;
        this.amount = amount;
        this.value = value;
        this.descriptions = descriptions;
        this.type = type;
    }

    public static void logPurchase(int storageId, int amount, double value, String seller, String buyer, String buyerType, Connection connection) throws SQLException {
        String descriptions = "'" + seller + "'" + " sold to '" + buyer + "'(" + buyerType + ").";
        StorageLog log = new StorageLog(storageId, amount, value, descriptions, OUT);
        log(log, connection);
    }

    public static void logCommodityRegistration(int storageId, int amount, double value, String seller, String commodityTitle, Connection connection) throws SQLException {
        String descriptions = "'" + seller + "' registered new commodity '" + commodityTitle + "'.";
        StorageLog log = new StorageLog(storageId, amount, value, descriptions, IN);
        log(log, connection);
    }

    public static void logCommodityAddition(int storageId, int amount, double value, String seller, String commodityTitle, Connection connection) throws SQLException {
        String descriptions = "'" + seller + "' charged the commodity '" + commodityTitle + "'.";
        StorageLog log = new StorageLog(storageId, amount, value, descriptions, IN);
        log(log, connection);
    }

    public static void logCommodityReduction(int storageId, int amount, double value, String seller, String commodityTitle, Connection connection) throws SQLException {
        String descriptions = "'" + seller + "' reduced the commodity '" + commodityTitle + "'.";
        StorageLog log = new StorageLog(storageId, amount, value, descriptions, OUT);
        log(log, connection);
    }

    public static void logAuction(int storageId, int amount, double value, String seller, String commodityTitle, Connection connection) throws SQLException {
        String descriptions = "'" + seller + "' sold the commodity '" + commodityTitle + "' on auction.";
        StorageLog log = new StorageLog(storageId, amount, value, descriptions, OUT);
        log(log, connection);
    }

    public static void logStorageDeletion(int storageId, int amount, double value, int toStorageId, Connection connection) throws SQLException {
        String descriptions = "Storage properties transported to storage '" + toStorageId + "'.";
        StorageLog log = new StorageLog(storageId, amount, value, descriptions, OUT);
        log(log, connection);
    }

    public static void logStorageMerger(int storageId, int amount, double value, String fromStorage, Connection connection) throws SQLException {
        String descriptions = "Storage '" + fromStorage + "' merged.";
        StorageLog log = new StorageLog(storageId, amount, value, descriptions, IN);
        log(log, connection);
    }


    public static void logCommodityDeletion(int storageId, int number, double price, String commodity, Connection connection) throws SQLException {
        String descriptions = "Commodity '" + commodity + "' deleted.";
        StorageLog log = new StorageLog(storageId, number, price, descriptions, OUT);
        log(log, connection);
    }

    public static void logCommodityExportation(int storageId, int number, double price, String commodity, Connection connection) throws SQLException {
        String descriptions = "Commodity '" + commodity + "' exported.";
        StorageLog log = new StorageLog(storageId, number, price, descriptions, OUT);
        log(log, connection);
    }

    public static void logCommodityImportation(int storageId, int number, double price, String commodity, Connection connection) throws SQLException {
        String descriptions = "Commodity '" + commodity + "' imported.";
        StorageLog log = new StorageLog(storageId, number, price, descriptions, IN);
        log(log, connection);
    }

    private static void log(StorageLog log, Connection connection) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        log.time = LocalTime.now().format(formatter).toString();
        log.date = LocalDate.now().toString();

        String sql = "insert into StorageLogs (type,descriptions,amount,value,date,time,storageId) values (?,?,?,?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, log.type);
        statement.setString(2, log.descriptions);
        statement.setInt(3, log.amount);
        statement.setDouble(4, log.value);
        statement.setString(5, log.date);
        statement.setString(6, log.time);
        statement.setInt(7, log.storageId);
        int resultSet = statement.executeUpdate();

        // todo register total properties
        logProperties(log, connection);

    }

    private static void logProperties(StorageLog log, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String daily = LocalDate.now().format(formatter);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String monthly = LocalDate.now().format(formatter);
        formatter = DateTimeFormatter.ofPattern("yyyy");
        String yearly = LocalDate.now().format(formatter);

        // read amount & value
        String sql = "SELECT sum(number) as amount, sum(price*number) as value from allCommodities " +
                "where storageId='" + log.getStorageId() + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        int amount = 0;
        double value = 0;
        if (resultSet.next()) {
            amount = resultSet.getInt("amount");
            value = resultSet.getDouble("value");
        }

        // first try to update
        sql = "update StorageProperties set value='" + value + "',amount='" + amount + "' " +
                "where daily='" + daily + "' and storageId='" + log.getStorageId() + "'";
        int result = statement.executeUpdate(sql);

        if (result == 0) { // if not exist
            // insert
            sql = "insert into StorageProperties " +
                    "(storageId, amount, value, daily, monthly, yearly) " +
                    "values ('" + log.getStorageId() + "','" + amount + "','" + value
                    + "','" + daily + "','" + monthly + "','" + yearly + "')";
            statement.executeUpdate(sql);
        }

    }




}
