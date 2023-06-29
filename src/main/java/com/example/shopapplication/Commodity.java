package com.example.shopapplication;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class Commodity {
    public String type;
    public String price , ratio;
    public String brand , title;
    public Image image;
    public Label Properties;
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
