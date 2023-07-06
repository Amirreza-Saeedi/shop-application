package com.example.shopapplication;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class Commodity {
    private String type;
    private String price , ratio;
    private String brand , title;
    private Image image;
    private String properties;
    private String date;
    private String sellerId;
    private int number;
    private int commodityId;
    public int isAuction;

    public int getAuctionId() {
        return auctionId;
    }

    public Commodity(String type, String price, String ratio, String brand, String title, String properties, String date, int number, int commodityId, int auctionId) {
        this.type = type;
        this.price = price;
        this.ratio = ratio;
        this.brand = brand;
        this.title = title;
        this.properties = properties;
        this.date = date;
        this.number = number;
        this.commodityId = commodityId;
        this.auctionId = auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    private int auctionId;


    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setDate(String date){
        this.date= date;
    }
    public String getDate(){
        return date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Commodity(){

    }
    public Commodity(String type,String brand,String price,String ratio,String title,int number,int commodityId){
        this.type = type;
        this.brand = brand;
        this.price = price;
        this.ratio = ratio;
        this.title = title;
        this.number = number;
        this.commodityId = commodityId;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", ratio=" + ratio +
                ", brand='" + brand + '\'' +
                ", title='" + title + '\'' +
                ", image=" + image +
                ", Properties=" + properties +
                ", number=" + number +
                '}';
    }
}
