package com.example.shopapplication;

public class Purchase {
    private int id;
    private int row;
    private int commodityId;
    private String userId;
    private String date;
    private String user;
    private String discountCode;
    private String type;
    private int number;
    private String priceOfOne;
    private String brand;

    public Purchase(int row, int commodityId, String date, String discountCode,
                    String type, int number, String priceOfOne, String brand) {
        this.row = row;
        this.commodityId = commodityId;
        this.date = date;
        this.discountCode = discountCode;
        this.type = type;
        this.number = number;
        this.priceOfOne = priceOfOne;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public String getUserId() {
        return userId;
    }

    public String getDate() {
        return date;
    }

    public String getUser() {
        return user;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public String getPriceOfOne() {
        return priceOfOne;
    }

    public String getBrand() {
        return brand;
    }
}
