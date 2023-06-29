package com.example.shopapplication;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class Commodity {
    private String type;
    private String price , ratio;
    private String brand , title;
    private Image image;
    private Label Properties;

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

    public Label getProperties() {
        return Properties;
    }

    public void setProperties(Label properties) {
        Properties = properties;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int number;
    public Commodity(){

    }
    public Commodity(String type,String brand,String price,String ratio,String title,int number){
        this.type = type;
        this.brand = brand;
        this.price = price;
        this.ratio = ratio;
        this.title = title;
        this.number = number;
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
                ", Properties=" + Properties +
                ", number=" + number +
                '}';
    }
}
