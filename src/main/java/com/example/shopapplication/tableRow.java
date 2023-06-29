package com.example.shopapplication;

public class tableRow {

    public String info;
    public String value;

    public tableRow(String info, String value) {
        this.info = info;
        this.value = value;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getInfo() {
        return info;
    }

    public String getValue() {
        return value;
    }
}
