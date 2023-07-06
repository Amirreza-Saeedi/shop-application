package com.example.shopapplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class Auction {

    public static void setWinner(int id) {
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()){
            // update commodities
            Statement statement = connection.createStatement();
            String sql = "update allCommodities set number='" + 0 + "', isAuction='" + 0 +
                    "' where isAuction='" + id + "'";
            statement.executeUpdate(sql);


            // delete auction
            sql = "delete from auction where auctionid='" + id + "'";
            statement.executeUpdate(sql);


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private int id;
    String buyerName;
    String buyerType;
    double basePrice;
    double mostPrice;
    String endDateTime;

    public int getId() {
        return id;
    }

    public Auction(int id, String buyerName, String buyerType, double basePrice, double mostPrice, String dateTime) {
        this.id = id;
        this.buyerName = buyerName;
        this.buyerType = buyerType;
        this.basePrice = basePrice;
        this.mostPrice = mostPrice;
        this.endDateTime = dateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getMostPrice() {
        return mostPrice;
    }

    public void setMostPrice(double mostPrice) {
        this.mostPrice = mostPrice;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
}
