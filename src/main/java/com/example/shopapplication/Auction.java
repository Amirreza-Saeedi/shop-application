package com.example.shopapplication;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class Auction {

    public void setWinner(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from allcommodities where isAuction='" + id + "'";
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) { // if exists
            // logs
            int storageId = resultSet.getInt("storageId");
            int amount = resultSet.getInt("number");
            double price = resultSet.getDouble("price");
            BigDecimal value = new BigDecimal(price).multiply(new BigDecimal(amount));
            String seller = resultSet.getString("userName");
            String title = resultSet.getString("title");

            StorageLog.logAuction(storageId, amount, value.doubleValue(), seller, title, connection);

            // update commodities
            sql = "update allCommodities set number='" + 0 + "', isAuction='" + 0 +
                    "' where isAuction='" + id + "'";
            statement.executeUpdate(sql);

            // delete auction
            sql = "delete from auction where auctionid='" + id + "'";
            statement.executeUpdate(sql);

//            // todo insert into purchases
//            sql = "insert into purchases () values ()";
//            statement.executeQuery(sql);

        } else {
            System.out.println("Auction.setWinner");
            System.err.println("not exist!");
        }



    }

    private int id;
    String buyerName;
    String buyerType;
    int basePrice;
    int mostPrice;
    String endDateTime;

    public int getId() {
        return id;
    }

    public Auction(int id, String buyerName, String buyerType, int basePrice, int mostPrice, String dateTime) {
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

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getMostPrice() {
        return mostPrice;
    }

    public void setMostPrice(int mostPrice) {
        this.mostPrice = mostPrice;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
}
